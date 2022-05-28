package com.company.repository.custom;

import com.company.dto.CardDTO;
import com.company.dto.CardFilterDTO;
import com.company.entity.CardEntity;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class CardCustomRepository {

    @Autowired
    private EntityManager entityManager;


    public List<CardDTO> filter(CardFilterDTO dto){

        String sql = "SELECT c FROM CardEntity as c ";
        Query query = entityManager.createQuery(sql, CardEntity.class);
        List<CardEntity> cardEntityList = query.getResultList();

        return null;
    }







}