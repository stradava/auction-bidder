package com.app.auction.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

import com.app.auction.dto.BidDto;
import com.app.auction.entity.AuctionItem;
import com.app.auction.entity.Bid;
import com.app.auction.enums.AuctionStatus;
import com.app.auction.repository.BidRepository;
import com.app.auction.repository.ItemRepository;
import com.app.auction.utility.GeneralHelper;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BiddingService {

    private final ItemRepository itemRepository;
    private final BidRepository bidRepository;

    @Transactional
    public Bid bid(BidDto.Request in) {
        AuctionItem item = itemRepository.findByIdForUpdate(in.getItemId()).orElse(null);

        if (item == null) {
            throw new IllegalStateException(GeneralHelper.ERR_ITEM_NOT_FOUND);
        }

        if (item.getStatus() != AuctionStatus.ACTIVE || LocalDateTime.now().isAfter(item.getEndTime())) {
            throw new IllegalStateException(GeneralHelper.ERR_BID_ENDED_OR_INACTIVE);
        }

        BigDecimal minimumRequired = (item.getCurrentHighestBid() != null) ? item.getCurrentHighestBid()
                : item.getStartingPrice();
        if (in.getAmount().compareTo(minimumRequired) <= 0) {
            throw new IllegalArgumentException(GeneralHelper.ERR_BID_AMOUNT_TOO_LOW);
        }

        item.setCurrentHighestBid(in.getAmount());
        itemRepository.save(item);

        Bid bid = new Bid();
        bid.setAmount(in.getAmount());
        bid.setBidderName(in.getBidderName());
        bid.setItem(item);
        return bidRepository.save(bid);
    }

}
