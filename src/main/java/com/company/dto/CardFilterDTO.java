package com.company.dto;

import com.company.CardStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CardFilterDTO {
    private String cliendId;
    private String cardNumber;
    private String cardId;

    private Long fromAmount;
    private Long toAmount;

    private String profileName;
    private CardStatus status;





}