package com.company.dto;

import com.company.ClientStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ClientDTO {
    private String id;
    private String name;
    private String surname;
    private String middleName;
    private String phone;

    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private ClientStatus status;
    private String profileName;//bu narsa apelsin yoki shunga o'xshagan narsalar bo'aldi

}