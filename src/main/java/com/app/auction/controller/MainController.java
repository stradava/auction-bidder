package com.app.auction.controller;

import org.springframework.web.bind.annotation.RestController;

import com.app.auction.dto.BidDto;
import com.app.auction.sao.Middleware;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RestController
@RequestMapping("v1/auction")
@RequiredArgsConstructor
@Validated
public class MainController {

    private final Middleware middleware;

    @GetMapping({"items", "items/{id}"})
    public ResponseEntity<Object> getItems(@PathVariable(required = false) Long id) {
        return ResponseEntity.ok().body(middleware.getItems(id));
    }

    @PostMapping("bid")
    public ResponseEntity<Object> bid(@RequestBody @Valid BidDto.Request req) {
        return ResponseEntity.ok().body(middleware.bid(req));
    }

}
