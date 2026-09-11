package com.app.auction.sao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.app.auction.dto.AuctionItemDto;
import com.app.auction.dto.BidDto;
import com.app.auction.entity.Bid;
import com.app.auction.service.BiddingService;
import com.app.auction.service.ItemService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class Middleware {

    private final BiddingService bidService;
    private final ItemService itemService;

    public AuctionItemDto.Response getItems(Long id) {
        List<AuctionItemDto.ItemData> itemDatas = itemService.getItems(id);
        AuctionItemDto.Response out = new AuctionItemDto.Response(itemDatas);
        out.setSuccessResponse();
        return out;
    }

    public BidDto.Response bid(BidDto.Request in) {
        Bid bid = bidService.bid(in);
        BidDto.Response out = new BidDto.Response();
        out.setData(new BidDto.BidData(bid.getId()));
        out.setSuccessResponse();
        return out;
    }

}
