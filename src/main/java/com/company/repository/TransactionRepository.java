package com.company.repository;

import com.company.ClientStatus;
import com.company.dto.CardDTO;
import com.company.entity.CardEntity;
import com.company.entity.TransactionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Integer> {

    Optional<TransactionEntity> findByFromCard(String fromCard);

    Optional<TransactionEntity> findByToCard(String toCard);

    Page<TransactionEntity> findAllByFromCard(CardDTO cardId, Pageable pageable);

    Page<TransactionEntity> findAllByToCard(CardDTO ardId, Pageable pageable);

    Page<TransactionEntity> findAllByFromCard(String cardId, Pageable pageable);

    Page<TransactionEntity> findAllByToCard(String cardId, Pageable pageable);

    Page<TransactionEntity> findAllByProfileName(String profileName, Pageable pageable);



    @Query(value = "select te from TransactionEntity as te inner join te.card as c where c.clientId =:clientId")
    Page<TransactionEntity> findAllByClientId(@Param("clientId") String clientId, Pageable pageable);


}
