package aivlemini.domain;

import lombok.Data;

@Data
public class BuyPointCommand {
    private Long amount; // 구매할 포인트 양
}