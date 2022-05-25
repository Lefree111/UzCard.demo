package com.company.service;

import com.company.dto.CardDTO;
import com.company.entity.CardEntity;
import com.company.exp.CardAlreadyExistsExsecption;
import com.company.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;




/*    public CardDTO create(CardDTO dto){
        CardEntity entity = cardRepository.findByNumber(dto.getNumber()).orElseThrow(()->{
            throw new CardAlreadyExistsExsecption("Card yaratilib bo'lga mazgi harakat qilma iloji bo'lsa vashe ");
        });



    }*/





}
