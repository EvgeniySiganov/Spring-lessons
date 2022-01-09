package ru.iteco.account.model.entity;

import lombok.*;
import org.hibernate.Hibernate;
import ru.iteco.account.validation.Currency;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "bank_book", schema = "dict")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class BankBookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "number", nullable = false)
    private String number;

    @Column(name = "amount", nullable = false)
    @Min(0)
    private BigDecimal amount;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "currency", referencedColumnName = "id")
    private CurrencyEntity currency;

//    @ManyToOne
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
//    @JoinTable
//    private UserEntity userEntity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BankBookEntity that = (BankBookEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
