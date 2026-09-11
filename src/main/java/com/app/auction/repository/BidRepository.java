package com.app.auction.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.auction.entity.Bid;

public interface BidRepository extends JpaRepository<Bid, Long> {


}
