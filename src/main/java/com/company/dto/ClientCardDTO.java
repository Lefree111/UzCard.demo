package com.company.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientCardDTO {
    private String id;
    private String number;
    private String phone;

}
