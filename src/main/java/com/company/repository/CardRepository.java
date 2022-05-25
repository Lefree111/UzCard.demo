package com.company.repository;

import com.company.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardRepository extends JpaRepository<CardEntity,Integer> {

    Optional<CardEntity> findByNumber(String number);


}
