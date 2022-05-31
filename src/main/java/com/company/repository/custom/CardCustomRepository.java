package com.company.repository.custom;

import com.company.dto.CardDTO;
import com.company.dto.CardFilterDTO;
import com.company.entity.CardEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CardCustomRepository {

    
    private final EntityManager entityManager;



    public List<CardDTO> filter(CardFilterDTO filter) {

        StringBuilder sql = new StringBuilder("SELECT  c FROM  CardEntity as c ");
        if (filter != null) {
            sql.append(" WHERE c.status = '" + filter.getStatus().name() + "'");
        } else {
            sql.append(" WHERE c.status = 'ACTIVE'");
        }

        if (filter.getCardId() != null) {
            sql.append(" AND  c.uuid = '" + filter.getCardId() + "'");
        }
        if (filter.getCardNumber() != null) {
            sql.append(" AND  c.number = " + filter.getCardId());
        }

        if (filter.getFromAmount() != null && filter.getToAmount() != null) {
            sql.append(" AND  c.balance between " + filter.getFromAmount() + " AND " + filter.getToAmount());
        } else if (filter.getFromAmount() != null) {
            sql.append(" AND  c.balance > " + filter.getFromAmount());
        } else if (filter.getToAmount() != null) {
            sql.append(" AND  c.balance < " + filter.getToAmount());
        }

        if (filter.getProfileName() != null) {
            sql.append(" AND  c.profile_name = '" + filter.getProfileName() + "'");
        }

        Query quesry = entityManager.createQuery(sql.toString(), CardEntity.class);
        List<CardEntity> cardEntityList = quesry.getResultList();

        return null;
    }







}