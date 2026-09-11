package com.app.auction.service;

import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Service;

import com.app.auction.dto.AuctionItemDto;
import com.app.auction.entity.AuctionItem;
import com.app.auction.enums.AuctionStatus;
import com.app.auction.repository.ItemRepository;
import com.app.auction.utility.GeneralHelper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public List<AuctionItemDto.ItemData> getItems(Long id) {
        List<AuctionItem> items;
        if (id == null) {   
            items = itemRepository.findAllByStatus(AuctionStatus.ACTIVE);
        } else {
            items = Arrays.asList(itemRepository.findById(id).orElseThrow(() -> new IllegalStateException(GeneralHelper.ERR_ITEM_NOT_FOUND)));
        }

        if (items == null) {
            throw new IllegalStateException(GeneralHelper.ERR_ITEM_NOT_FOUND);
        }
        
        return items.stream().map(i -> new AuctionItemDto.ItemData(i)).toList();
    }

}
