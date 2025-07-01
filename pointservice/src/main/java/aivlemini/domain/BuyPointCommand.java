package aivlemini.domain;

import lombok.Data;

@Data
public class BuyPointCommand {
    private Long point; // 프론트에서 넘겨주는 point값
                        // ex) 1000원, 5000원 목록 중, 1000원 버튼 클릭시 결제가 이루어지고, 
                        // httpie에 point=1000으로 동적 삽입
}