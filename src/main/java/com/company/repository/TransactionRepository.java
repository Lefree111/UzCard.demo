package com.company.repository;

import com.company.ClientStatus;
import com.company.TransactionStatus;
import com.company.entity.ClientEntity;
import com.company.entity.TransactionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Integer> {

    Optional<TransactionEntity> findByFromCard(String fromCard);

    Optional<TransactionEntity> findByToCard(String toCard);

    Page<TransactionEntity> findAllByFromCard(String cardId, Pageable pageable);

    Page<TransactionEntity> findAllByToCard(String cardId, Pageable pageable);

    Page<TransactionEntity> findAllByProfileName(String profileName, Pageable pageable);


}
