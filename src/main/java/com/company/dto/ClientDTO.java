package com.company.dto;

import com.company.ClientStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)

public class ClientDTO {
    private String id;
    @NotNull(message = "name qani ")
    private String name;
    @NotNull(message = "qame ani desam endi surname qolib ketibdi mazzgi")
    private String surname;
    @NotNull(message = "O' mazgi middle name ha kerak")
    private String middleName;
    @NotNull(message = "EEE qoshing bit tushgur phone qani endi")
    private String phone;

    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private ClientStatus status;

    private String profileName;//bu narsa apelsin yoki shunga o'xshagan narsalar bo'aldi

}