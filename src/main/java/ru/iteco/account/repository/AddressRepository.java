package ru.iteco.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.iteco.account.model.entity.AddressEntity;

public interface AddressRepository extends JpaRepository<AddressEntity, Integer> {
}
