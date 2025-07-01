package aivlemini.domain;

import java.util.Date;
import lombok.Data;

@Data
public class GetAllPointQuery {

    private Long id;
    private Long point;
    private Long userId;
}
