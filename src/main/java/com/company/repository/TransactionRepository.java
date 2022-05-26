package com.company.repository;

import com.company.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransactionRepository extends JpaRepository<TransactionEntity,Integer> {

    Optional<TransactionEntity> findByFromCard(String fromCard);

    Optional<TransactionEntity> findByToCard(String toCard);



}
