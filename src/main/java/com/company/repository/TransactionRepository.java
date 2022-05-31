package com.company.repository;

import com.company.entity.TransactionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Integer> {


    Page<TransactionEntity> findByToCard(String number, Pageable pageable);

    Page<TransactionEntity> findByFromCard(String number, Pageable pageable);

    Page<TransactionEntity> findAllByFromCard(String cardId, Pageable pageable);

    Page<TransactionEntity> findAllByToCard(String cardId, Pageable pageable);

    Page<TransactionEntity> findAllByProfileName(String profileName, Pageable pageable);

}
