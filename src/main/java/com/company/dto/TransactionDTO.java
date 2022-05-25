package com.company.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class TransactionDTO {
    private String id;
    private String fromCard;
    private String toCard;
    private Long amount;

    private LocalDateTime createDate;
    private Boolean status = true;

    private String profileId;
}