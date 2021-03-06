package ru.iteco.account.model.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

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

    @Column(name = "source_bank_book_id", nullable = false)
    private Integer sourceBankBookId;

    @Column(name = "target_bank_book_id", nullable = false)
    private Integer targetBankBookId;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "initiation_date", nullable = false)
    private LocalDateTime initiationDate;

    @Column(name = "completion_date", nullable = false)
    private LocalDateTime completionDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "status", referencedColumnName = "id")
    private StatusEntity statusEntity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TransactionEntity that = (TransactionEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
