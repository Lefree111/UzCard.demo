package com.company.service;

import com.company.TransactionStatus;
import com.company.dto.TransactionDTO;
import com.company.entity.CardEntity;
import com.company.entity.TransactionEntity;
import com.company.exp.ItemNotFoundException;
import com.company.repository.CardRepository;
import com.company.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public TransactionDTO create(TransactionDTO dto) {
        TransactionEntity entity = new TransactionEntity();

        CardEntity fromCard = cardRepository.findByNumber(String.valueOf(dto.getFromCard())).orElseThrow(() -> {
            throw new ItemNotFoundException("Not found");
        });
        CardEntity toCard = cardRepository.findByNumber(String.valueOf(dto.getToCard())).orElseThrow(() -> {
            throw new ItemNotFoundException("Not found");
        });
        if (fromCard.getBalance() >= dto.getAmount()) {

            Long vaqtincha = fromCard.getBalance() - dto.getAmount();

            toCard.setBalance(dto.getAmount());
            fromCard.setBalance(vaqtincha);

            entity.setFromCard(fromCard.getNumber());
            entity.setToCard(toCard.getNumber());
            entity.setAmount(dto.getAmount());
            entity.setStatus(TransactionStatus.SUCCESS);
        }
        transactionRepository.save(entity);
        return toDTO(entity);
    }


    public TransactionDTO toDTO (TransactionEntity entity){
        TransactionDTO dto = new TransactionDTO();
        dto.setFromCard(entity.getFromCard());
        dto.setToCard(entity.getToCard());
        dto.setAmount(entity.getAmount());
        return dto;
    }

}
