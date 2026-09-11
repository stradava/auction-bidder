package com.app.auction.dto;

import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

public class BidDto {

    private BidDto() {
    }

    @Data
    @JsonPropertyOrder(alphabetic = true)
    public static class Request {

        @NotNull
        private Long itemId;
        @NotBlank
        private String bidderName;
        @DecimalMin(value = "0.1")
        private BigDecimal amount;

    }

    @Data
    @EqualsAndHashCode(callSuper = false)
    @JsonPropertyOrder(alphabetic = true)
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response extends BaseResponse {

        private BidData data;

    }

    @Data
    @JsonPropertyOrder(alphabetic = true)
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BidData {

        private Long bidId;

    }

}
