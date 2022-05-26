package com.company.service;

import com.company.CardStatus;
import com.company.dto.CardDTO;
import com.company.dto.ClientCardDTO;
import com.company.dto.ClientDTO;
import com.company.entity.CardEntity;
import com.company.entity.ClientEntity;
import com.company.exp.CardAlreadyExistsExsecption;
import com.company.exp.PhoneNotFoundException;
import com.company.repository.CardRepository;
import com.company.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CardService {


    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CardRepository cardRepository;


    public CardDTO create(CardDTO dto) {
        Optional<CardEntity> optional = cardRepository.findByNumber(dto.getNumber());
        if (optional.isPresent()) {
            throw new CardAlreadyExistsExsecption("Karta yaratilgan Mazgi 24 qator");
        }

        CardEntity entity = new CardEntity();
        entity.setName(dto.getName());
        entity.setNumber(getCardNumber());
        entity.setBalance(0L);
        entity.setExpiredDate(LocalDateTime.now());
        entity.setCreateDate(LocalDateTime.now());
        entity.setStatus(CardStatus.NO_ACTIVE);
        cardRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public CardDTO cardga_clientni_ulash(ClientCardDTO dto) {
        CardEntity entity = cardRepository.findByNumber(dto.getNumber()).orElseThrow(() -> {
            throw new CardAlreadyExistsExsecption("Card yaratilgan o'gayni istambo'lma boshqa ol mazgi  40 qator");
        });
        ClientEntity clientEntity = clientRepository.findByPhone(dto.getPhone()).orElseThrow(() -> {
            throw new PhoneNotFoundException("Tel raqam tolimadi");
        });
        entity.setClientPhone(dto.getPhone());
        entity.setClientId(clientEntity.getId());
        entity.setStatus(CardStatus.ACTIVE);
        cardRepository.save(entity);
        return toDTO(entity);
    }








    private String getCardNumber() {
        int max = 9999, min = 1000;
        int a = (int) (Math.random() * (max - min + 1) + min);
        int b = (int) (Math.random() * (max - min + 1) + min);
        int c = (int) (Math.random() * (max - min + 1) + min);
        String cardNumber = "8600-" + a + "-" + b + "-" + c;

        Optional<CardEntity> optional = cardRepository.findByNumber(cardNumber);
        if (optional.isPresent()) {
            getCardNumber();
        }
        return cardNumber;
    }


    public CardDTO toDTO(CardEntity entity) {
        CardDTO dto = new CardDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setNumber(entity.getNumber());
        dto.setBalance(entity.getBalance());
        dto.setExpiredDate(entity.getExpiredDate());
        dto.setClientPhone(entity.getClientPhone());
        dto.setCreateDate(entity.getCreateDate());
        return dto;
    }

}
