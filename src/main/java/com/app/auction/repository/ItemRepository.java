package com.app.auction.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.auction.entity.AuctionItem;
import com.app.auction.enums.AuctionStatus;

public interface ItemRepository extends JpaRepository<AuctionItem, Long> {

	@Query(value = """
			select
				id,
				"name",
				description,
				starting_price,
				current_highest_bid,
				end_time,
				created_at,
				updated_at,
				status
			from tb_item ti
			where id = :id
			for update
			            """, nativeQuery = true)
	Optional<AuctionItem> findByIdForUpdate(@Param("id") Long id);

	@Query(value = """
			select
				id,
				"name",
				description,
				starting_price,
				current_highest_bid,
				end_time,
				created_at,
				updated_at,
				status
			from tb_item ti
			where status = :#{#status?.name()}
			            """, nativeQuery = true)
	List<AuctionItem> findAllByStatus(@Param("status") AuctionStatus status);

}
