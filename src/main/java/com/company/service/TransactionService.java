package com.company.service;

import com.company.TransactionStatus;
import com.company.dto.CardDTO;
import com.company.dto.TransactionDTO;
import com.company.entity.CardEntity;
import com.company.entity.TransactionEntity;
import com.company.exp.ItemNotFoundException;
import com.company.repository.CardRepository;
import com.company.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private CardService cardService;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public String create(TransactionDTO dto) {
        TransactionEntity entity = new TransactionEntity();

        CardEntity fromCard = cardRepository.findByNumber(dto.getFromCard()).orElseThrow(() -> {
            throw new ItemNotFoundException("Not found");
        });
        CardEntity toCard = cardRepository.findByNumber(dto.getToCard()).orElseThrow(() -> {
            throw new ItemNotFoundException("Not found");
        });
        if (fromCard.getBalance() >= dto.getAmount()) {

            fromCard.setBalance(fromCard.getBalance() - dto.getAmount());
            toCard.setBalance(toCard.getBalance() + dto.getAmount());

            //todo fromcard bilan tocardni bog'langan joyi
            entity.setFromCard(fromCard.getNumber());
            entity.setToCard(toCard.getNumber());
            //
            entity.setAmount(dto.getAmount());
            entity.setProfileName(dto.getProfileName());
            entity.setCreateDate(LocalDateTime.now());
            entity.setStatus(TransactionStatus.SUCCESS);
            transactionRepository.save(entity);
        } else if (fromCard.getBalance() < dto.getAmount()) {
            entity.setStatus(TransactionStatus.FAILED);
            return "no Success";
        }
        return "Success";
    }

    public List<TransactionDTO> paginationListCardId(int page, int size, String cardId) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createDate"));

        List<TransactionDTO> list = new ArrayList<>();
        transactionRepository.findAllByFromCard(cardId, pageable).forEach(courseEntity -> {
            list.add(toDTO(courseEntity));
        });

        transactionRepository.findAllByToCard(cardId, pageable).forEach(courseEntity -> {
            list.add(toDTO(courseEntity));
        });
        if (list.isEmpty()) {
            throw new ItemNotFoundException("List bo'm bo'sh ku Mazgi");
        }
        return list;
    }


    public List<TransactionDTO> paginationListProfileName(int page, int size, String profileName) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createDate"));

        List<TransactionDTO> list = new ArrayList<>();
        transactionRepository.findAllByProfileName(profileName, pageable).forEach(courseEntity -> {
            list.add(toDTO(courseEntity));
        });

        if (list.isEmpty()) {
            throw new ItemNotFoundException("List bo'm bo'sh ku Mazgi");
        }
        return list;
    }

   /* public List<TransactionDTO> paginationListClientId(int page, int size, String clientId) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createDate"));

        List<TransactionDTO> list = new ArrayList<>();

        transactionRepository.findAllByClientId(pageable, clientId, TransactionStatus.SUCCESS).forEach(courseEntity -> {
            list.add(toDTO((TransactionEntity) courseEntity));
        });

        if (list.isEmpty()) {
            throw new ItemNotFoundException("List bo'm bo'sh ku Mazgi");
        }
        return list;
    }*/

    public PageImpl<TransactionDTO> paginationListClientId(String clientId, int page, int size) {
        List<CardDTO> cardList = cardService.getByClientId(clientId);
        Pageable pageable = PageRequest.of(page, size);
        long totalElements = 0;
        List<TransactionDTO> list = new java.util.ArrayList<>();
        for (CardDTO dto : cardList) {
            Page<TransactionEntity> toCard = transactionRepository.findByToCard(dto.getNumber(), pageable);
            Page<TransactionEntity> fromCard = transactionRepository.findByFromCard(dto.getNumber(), pageable);
            list.addAll(toCard.stream().map(this::toDTO).toList());
            list.addAll(fromCard.stream().map(this::toDTO).toList());
            totalElements = toCard.getTotalElements() + fromCard.getTotalElements();
        }
        return new PageImpl<>(list, pageable, totalElements);
    }

    public TransactionDTO toDTO(TransactionEntity entity) {
        TransactionDTO dto = new TransactionDTO();
        dto.setFromCard(String.valueOf(entity.getFromCard()));
        dto.setToCard(String.valueOf(entity.getToCard()));
        return dto;
    }

}
