package aivlemini.domain;

import lombok.Data;

@Data
public class AuthorRequestDto {
    private String authorName;
    private String email;
    private String phone;
}