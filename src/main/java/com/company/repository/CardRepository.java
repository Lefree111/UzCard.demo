package com.company.repository;

import com.company.CardStatus;
import com.company.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface CardRepository extends JpaRepository<CardEntity,Integer> {

    Optional<CardEntity> findByNumber(String number);

    Optional<CardEntity> findByClientId(String clientId);

    Optional<CardEntity> findById(String id);


    List<CardEntity> findAllByStatusAndClientPhone(CardStatus status, String profileName);

    List<CardEntity> findAllByStatusAndClientId(CardStatus status, String clientId);

    @Transactional
    @Modifying
    @Query("update CardEntity set status = :status where number = :number")
    int updateStatus(@Param("status") CardStatus status, @Param("number") String number);


}
