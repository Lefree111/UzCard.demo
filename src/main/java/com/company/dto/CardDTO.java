package com.company.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class CardDTO {
    private String id;//
    private String name;//
    private String number;//
    private LocalDateTime expiredDate;
    private String phone;
    private Boolean status = true;//
    private LocalDateTime createDate;//
    private Long balance;//


    private String clientId;//
    private String clientPhone;//

}