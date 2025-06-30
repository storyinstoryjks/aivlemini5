package aivlemini.domain;

import aivlemini.AiserviceApplication;
// import aivlemini.domain.PublishPrepared;
import aivlemini.service.AIService;
import aivlemini.service.PDFService;

// import com.fasterxml.jackson.databind.ObjectMapper;
// import java.time.LocalDate;
// import java.util.Collections;
// import java.util.Date;
import java.util.List;
// import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.persistence.*;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.client.RestTemplate;

@Entity
@Table(name = "Publishing_table")
@Data
//<<< DDD / Aggregate Root
public class Publishing {
    private static final Logger logger = LoggerFactory.getLogger(Publishing.class);
    private static final AtomicBoolean isProcessing = new AtomicBoolean(false); // 동시 출간 요청 방지를 위한 지역변수 상태값    

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private Long authorId;

    private String authorName;

    private String category;

    private String content;

    private String summaryContent;

    private String coverImagePath;

    private String pdfPath;

    private Long price;

    private Boolean notifyStatus;

    private Long manuscriptId;

    public static PublishingRepository repository() {
        PublishingRepository publishingRepository = AiserviceApplication.applicationContext.getBean(
            PublishingRepository.class
        );
        return publishingRepository;
    }

    //<<< Clean Arch / Port Method
    public static void publish(PublicationRequested publicationRequested) {
        // 동시 여러 요청 처리 방지
        if (!isProcessing.compareAndSet(false, true)) {
            logger.warn("이미 출판 처리가 진행 중입니다. 요청이 무시됩니다.");
            return;
        }        
        
        try {
            logger.info("\n===== AI 출판 처리 시작 =====");

            // 출판 정보 객체 생성
            Publishing publishing = new Publishing();
            
            // 출간요청됨(PublicationRequested) Event를 통해 그대로 값을 이용하는 속성들 설정
            publishing.setTitle(publicationRequested.getTitle()); // 책제목
            publishing.setAuthorId(publicationRequested.getAuthorId()); // 작가id
            publishing.setNotifyStatus(false); // notifyStatus 기본값
            publishing.setManuscriptId(publicationRequested.getId()); // 원고id
            
            logger.info("책 제목: {}", publicationRequested.getTitle());
            logger.info("작가id: {}", publicationRequested.getAuthorId().toString());
            logger.info("작가명: {}", publicationRequested.getAuthorName());
            logger.info("원고id: {}", publicationRequested.getId().toString());

            // 서비스 인스턴스 가져오기
            AIService aiService = AiserviceApplication.applicationContext.getBean(AIService.class);
            PDFService pdfService = AiserviceApplication.applicationContext.getBean(PDFService.class);
            
            // 책 내용 가져오기
            String content = publicationRequested.getContent();
            if (content == null || content.trim().isEmpty()) {
                throw new IllegalArgumentException("책 내용이 비어 있습니다.");
            }
            publishing.setContent(content); // 책내용 저장
            logger.info("책 내용 길이: {}자", content.length());
            
            // 1. 표지 이미지 생성을 위한 프롬프트 생성
            logger.info("1단계: 이미지 생성 프롬프트 생성 시작");
            String coverImagePrompt = aiService.generateCoverImagePrompt(content);
            logger.info("1단계 완료: 이미지 생성 프롬프트 - {}", coverImagePrompt);
            
            // 2. DALL-E API를 사용하여 실제 이미지 생성 및 URL 저장
            logger.info("2단계: 이미지 생성 API 호출 시작");
            String imageUrl = null;
            String pdfImageUrl = null;
            try {
                //imageUrl = aiService.generateImage(coverImagePrompt); // gpt 생성 이미지 URL
                List<String> imageLists = aiService.generateImageAndDownload(coverImagePrompt, publishing.getTitle()); // 이미지 파일경로
                imageUrl = imageLists.get(1); // 다운로드한 이미지 파일명
                pdfImageUrl = imageLists.get(0); // gpt가 생성한 url
                publishing.setCoverImagePath(imageUrl);
                logger.info("2단계 완료: 이미지 URL 생성됨 - {}", imageUrl);
            } catch (Exception e) {
                // API 호출 실패 시 기본 이미지 사용
                logger.error("이미지 생성 API 호출 실패: {}", e.getMessage());
                publishing.setCoverImagePath("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSczdFZZc2BhiuLpRhm8qQ4ZTev3tBafeHg-Q&s");
            }

            // 3. 장르 분류 및 저장
            logger.info("3단계: 카테고리 분류 시작");
            String category = aiService.categorizeContent(content);
            publishing.setCategory(category);
            logger.info("3단계 완료: 분류된 카테고리 - {}", category);
            
            // 4. 줄거리 요약 및 저장
            logger.info("4단계: 내용 요약 시작");
            String summary = aiService.summarizeContent(content);
            publishing.setSummaryContent(summary);
            logger.info("4단계 완료: 요약 완료 ({}자)", summary.length());        

            // 5. 저자 정보 처리
            logger.info("5단계: 저자 정보 처리 시작");
            try {
                publishing.setAuthorName(publicationRequested.getAuthorName());
                logger.info("5단계 완료: 저자 이름 - {}", publishing.getAuthorName());
            } catch (Exception e) {
                logger.error("저자 정보 조회 실패: {}", e.getMessage());
                publishing.setAuthorName("알 수 없는 저자");
            }

            // 6. 모든 정보가 준비된 후 PDF 생성 (PDFService 직접 호출)
            logger.info("6단계: PDF 생성 시작");
            String fileName = pdfService.generatePdf(
                content, 
                pdfImageUrl, // gpt 생성 url
                publishing.getSummaryContent(),
                publishing.getTitle());
            publishing.setPdfPath(fileName);
            logger.info("6단계 완료: PDF 생성됨 - {}", fileName);
            
            // 7. PDF 파일명을 웹에서 접근 가능한 URL로 변환
            logger.info("7단계: 웹 URL 생성 시작");
            try {
                String webUrl = pdfService.generateWebUrl(fileName);
                publishing.setPdfPath(webUrl);
                logger.info("7단계 완료: 웹 URL 생성됨 - {}", webUrl);
            } catch (Exception e) {
                logger.error("웹 URL 생성 실패: {}", e.getMessage(), e);
                // 오류 발생 시에도 동적으로 URL 생성 시도
                try {
                    String fallbackUrl = "http://localhost:8084/pdfs/" + fileName;
                    publishing.setPdfPath(fallbackUrl);
                    logger.warn("폴백 URL 사용: {}", fallbackUrl);
                } catch (Exception fallbackError) {
                    logger.error("폴백 URL 생성도 실패: {}", fallbackError.getMessage());
                    publishing.setPdfPath("/pdfs/" + fileName); // 상대 경로로 설정
                }
            }

            // 8. 오류없이 여기까지 잘 진행되었을 경우, notifyStatus 갱신
            publishing.setNotifyStatus(true);
            logger.info("8단계 : notifyStatus : {}", publishing.getNotifyStatus());
            
            // 9. 도서 가격 산정
            /*
             * GPT로 산정하는 부분은 제외하는 것으로 임의 결정.
             * 기본 가격은 500원이고, 베스트셀러 도서인 경우 "서재플랫폼"의 "베스트셀러 등록" Policy에서 1000원으로 설정 (설명 예정)
             */
            publishing.setPrice(500L);
            logger.info("9단계 가격 산정 완료 : 기본가격 500원");

            // 10. 출판 정보 저장
            logger.info("10단계: 출판 정보 저장 시작");
            repository().save(publishing); // record insert
            logger.info("10단계 완료: 출판 정보 저장됨");
            
            // 11. 이벤트 발행
            logger.info("11단계: 출판 이벤트 발행 시작");
            PublishPrepared published = new PublishPrepared(publishing); // Publishing과 PublishPrepared 속성값 모두 동일
                                                                         // 이대로 이벤트 발행 진행
            published.publishAfterCommit();
            logger.info("11단계 완료: 출판 이벤트 발행됨"); 


        } catch(Exception e) {
            logger.error("출판 처리 중 오류 발생 : {}", e.getMessage(), e);
        } finally {
            // 처리 상태 초기화 : 다음 출간 요청 작업 이루어질 수 있도록
            isProcessing.set(false);            
        }
    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
