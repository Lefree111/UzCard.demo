package com.company.entity;

import com.company.TransactionStatus;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "transaction")
public class TransactionEntity {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "uuid", unique = true)
    private String uuid;

    @Column(name = "fromCard",nullable = false)
    private String fromCard;

    @Column(name = "")
    @ManyToOne(fetch = FetchType.LAZY)
    private CardEntity card;

    @Column(name = "toCard")
    private String toCard;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "profileName")
    private String profileName;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    @Column(name = "create_date")
    private LocalDateTime createDate;

}