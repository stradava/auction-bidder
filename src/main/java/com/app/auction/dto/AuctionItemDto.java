package com.app.auction.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.app.auction.entity.AuctionItem;
import com.app.auction.enums.AuctionStatus;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

public class AuctionItemDto {

    private AuctionItemDto() {
    }

    @Data
    @EqualsAndHashCode(callSuper = false)
    @JsonPropertyOrder(alphabetic = true)
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response extends BaseResponse {

        private List<ItemData> data;

    }

    @Data
    @JsonPropertyOrder(alphabetic = true)
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ItemData {

        private Long id;
        private String name;
        private String description;
        private BigDecimal startingPrice;
        private BigDecimal currentHighestBid;
        private LocalDateTime endTime;
        private AuctionStatus status;

        public ItemData(AuctionItem item) {
            this.id = item.getId();
            this.name = item.getName();
            this.description = item.getDescription();
            this.startingPrice = item.getStartingPrice();
            this.currentHighestBid = item.getCurrentHighestBid();
            this.endTime = item.getEndTime();
            this.status = item.getStatus();
        }

    }

}
