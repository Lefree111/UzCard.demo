package com.company.service;

import com.company.CardStatus;
import com.company.dto.CardDTO;
import com.company.dto.ClientCardDTO;
import com.company.entity.CardEntity;
import com.company.entity.ClientEntity;
import com.company.exp.CardAlreadyExistsExsecption;
import com.company.exp.ItemNotFoundException;
import com.company.exp.NumberNotFoundExseption;
import com.company.exp.PhoneNotFoundException;
import com.company.repository.CardRepository;
import com.company.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.company.CardStatus.ACTIVE;


@Service
public class CardService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CardRepository cardRepository;

    /* @Autowired
     private CardCustomRepository cardCustomRepository;
 */
    public CardDTO create(CardDTO dto) {
        Optional<CardEntity> optional = cardRepository.findByNumber(dto.getNumber());
        if (optional.isPresent()) {
            throw new CardAlreadyExistsExsecption("Karta yaratilgan Mazgi 24 qator");
        }

        CardEntity entity = new CardEntity();
        entity.setNumber(getCardNumber());
        entity.setBalance(0L);
        entity.setExpiredDate(LocalDateTime.now());
        entity.setCreateDate(LocalDateTime.now());
        entity.setStatus(CardStatus.NO_ACTIVE);
        cardRepository.save(entity);
        dto.setId(entity.getId());
        dto.setExpiredDate(entity.getExpiredDate());
        return toDTO(entity);
    }

    public Boolean updateStatusCard(String cardNumber) {
        CardEntity entity = cardRepository.findByNumber(cardNumber).orElseThrow();
        if (entity.getStatus().equals(ACTIVE)) {
            throw new ItemNotFoundException("do'stim seni statusing active emas shuni to'g'rla 70 qator");
        }
        int n = cardRepository.updateStatus(ACTIVE, cardNumber);
        return n > 0;
    }

    public ClientCardDTO cardga_clientni_ulash(ClientCardDTO dto) {
        CardEntity entity = cardRepository.findByNumber(dto.getNumber()).orElseThrow(() -> {
            throw new CardAlreadyExistsExsecption("Card yaratilgan o'gayni istambo'lma boshqa ol mazgi  40 qator");
        });
        ClientEntity clientEntity = clientRepository.findByPhone(dto.getPhone()).orElseThrow(() -> {
            throw new PhoneNotFoundException("Tel raqam tolimadi");
        });
        if (entity.getStatus().equals(CardStatus.ACTIVE)) {
            entity.setClientPhone(dto.getPhone());
            entity.setClientId(clientEntity.getId());
        }
        cardRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public List<CardDTO> getCardListByPhone(String phone) {
        List<CardDTO> list = new ArrayList<>();
        cardRepository.findAllByStatusAndClientPhone(ACTIVE, phone).forEach(entity -> {
            list.add(toDTO(entity));
        });
        if (list.isEmpty()) {
            throw new ItemNotFoundException("Not Found!");
        }
        return list;
    }

    public List<CardDTO> getCardListByClientId(String clientId) {
        List<CardDTO> list = new ArrayList<>();
        cardRepository.findAllByStatusAndClientId(ACTIVE, clientId).forEach(entity -> {
            list.add(toDTO(entity));
        });
        if (list.isEmpty()) {
            throw new ItemNotFoundException("Not Found!");
        }
        return list;
    }

    public CardDTO getCardByNumber(String number) {
        Optional<CardEntity> entity = cardRepository.findByNumber(number);
        if (entity.isEmpty()) {
            throw new NumberNotFoundExseption("karta raqami topilmadi");
        }
        CardEntity cardEntity = entity.get();
        return toDTO(cardEntity);
    }

    public Long getCardBalanceByNumber(String number) {
        Optional<CardEntity> optional = cardRepository.findByNumber(number);
        if (optional.isEmpty()) {
            throw new NumberNotFoundExseption("karta topilmadi");
        }
        CardEntity entity = optional.get();
        return entity.getBalance();
    }


 /*   //TODO filter
    public List<CardDTO> filter(CardFilterDTO dto){
        return cardCustomRepository.filter(dto);
    }
*/


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
        dto.setNumber(entity.getNumber());
        dto.setBalance(entity.getBalance());
        dto.setExpiredDate(entity.getExpiredDate());
        dto.setClientPhone(entity.getClientPhone());
        dto.setCreateDate(entity.getCreateDate());
        return dto;
    }
}
