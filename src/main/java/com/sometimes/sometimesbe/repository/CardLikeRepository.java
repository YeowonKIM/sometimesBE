package com.sometimes.sometimesbe.repository;

import com.sometimes.sometimesbe.entity.Card;
import com.sometimes.sometimesbe.entity.CardLike;
import com.sometimes.sometimesbe.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardLikeRepository extends JpaRepository<CardLike,Long> {
    Optional<CardLike> findByCardIdAndUserId(Long cardId, Long userId);
    Integer countCardLikeByCardId(Long cardId);
    void deleteByCardId(Long cardId);
}
