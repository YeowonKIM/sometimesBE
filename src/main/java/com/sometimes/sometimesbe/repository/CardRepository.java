package com.sometimes.sometimesbe.repository;

import com.sometimes.sometimesbe.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findAllByOrderByCreatedAtDesc();
}
