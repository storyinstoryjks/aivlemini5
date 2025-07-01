package aivlemini.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public Author registerAuthor(AuthorRequestDto dto) {
        Author author = new Author();
        author.setAuthorName(dto.getAuthorName());
        author.setEmail(dto.getEmail());
        author.setPhone(dto.getPhone());
        author.setIsApproved(null);

        return authorRepository.save(author);
    }
}