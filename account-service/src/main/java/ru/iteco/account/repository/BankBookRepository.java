package ru.iteco.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import ru.iteco.account.model.entity.BankBookEntity;
import ru.iteco.account.model.entity.CurrencyEntity;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;
import java.util.Optional;

import static org.hibernate.jpa.QueryHints.HINT_CACHEABLE;

public interface BankBookRepository extends JpaRepository<BankBookEntity, Integer> {

    List<BankBookEntity> findAllByUserId(Integer userId);

    Optional<BankBookEntity> findAllByUserIdAndNumberAndCurrency(Integer userId, String number, CurrencyEntity currencyEntity);

    void deleteAllByUserId(Integer userId);

    @Lock(LockModeType.PESSIMISTIC_READ)
    @Query("select bbe from BankBookEntity bbe where bbe.number = :number")
    Optional<BankBookEntity> lockByNumber(@Param("number")String number);

    @Query("select bbe from BankBookEntity bbe where bbe.number = :number")
    @QueryHints(@QueryHint(name = HINT_CACHEABLE, value = "true"))
    Optional<BankBookEntity> findByNumber(@Param("number") String number);

}
