package ru.iteco.account.model.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "transaction", schema = "bank")
public class TransactionEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "sourceBankBookId", nullable = false)
    private Integer sourceBankBookId;

    @Column(name = "targetBankBookId", nullable = false)
    private Integer targetBankBookId;

    @Column(name = "amoubt", nullable = false)
    private BigDecimal amount;

    @Column(name = "initiationDate", nullable = false)
    private LocalDateTime initiationDate;

    @Column(name = "completionDate", nullable = false)
    private LocalDateTime completionDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "status", referencedColumnName = "id")
    private StatusEntity statusEntity;
}
