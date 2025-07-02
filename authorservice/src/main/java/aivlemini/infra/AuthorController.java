package aivlemini.infra;

import aivlemini.domain.*;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//<<< Clean Arch / Inbound Adaptor

@RestController
// @RequestMapping(value="/authors")
@Transactional
public class AuthorController {

    @Autowired
    AuthorRepository authorRepository;

    // 작가승인 Command (httpie)
    @RequestMapping(
        value = "/authors/{id}/approveauthor",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Author approveAuthor(
        @PathVariable(value = "id") Long id,
        @RequestBody ApproveAuthorCommand approveAuthorCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /author/approveAuthor  called #####");
        Optional<Author> optionalAuthor = authorRepository.findById(id);

        optionalAuthor.orElseThrow(() -> new Exception("No Entity Found"));
        Author author = optionalAuthor.get();
        author.approveAuthor(approveAuthorCommand); // 이곳에서 isApproved 값 변경

        authorRepository.save(author);
        return author;
    }

    // 작가비승인 Command (httpie)
    @RequestMapping(
        value = "/authors/{id}/disapproveauthor",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Author disapproveAuthor(
        @PathVariable(value = "id") Long id,
        @RequestBody DisapproveAuthorCommand disapproveAuthorCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /author/disapproveAuthor  called #####");
        Optional<Author> optionalAuthor = authorRepository.findById(id);

        optionalAuthor.orElseThrow(() -> new Exception("No Entity Found"));
        Author author = optionalAuthor.get();
        author.disapproveAuthor(disapproveAuthorCommand); // 이곳에서 isApproved 값 변경

        authorRepository.save(author);
        return author;
    }
}
//>>> Clean Arch / Inbound Adaptor
