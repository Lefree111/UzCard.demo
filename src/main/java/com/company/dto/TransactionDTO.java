package com.company.dto;

import com.company.TransactionStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionDTO {
    private String id;
    private String fromCard;
    private String toCard;
    private Long amount;
    private String profileName;

    private LocalDateTime createDate;
    private TransactionStatus status;

}