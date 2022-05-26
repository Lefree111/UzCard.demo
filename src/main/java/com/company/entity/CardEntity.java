package com.company.entity;

import com.company.CardStatus;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name ="card")
public class CardEntity {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "uuid", unique = true)
    private String id;

    @Column(name = "name")
    private String name;
    @Column(name = "number",nullable = false)
    private String number;
    @Column(name = "balance")
    private Long balance;
    @Column(name = "expiredDate")
    private LocalDateTime expiredDate;

    @Column(name = "client_id")
    private String clientId;
    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "client_id",insertable = false,updatable = false)
    private ClientEntity client;

    @Column(name = "clientPhone")
    private String clientPhone;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private CardStatus status;

}