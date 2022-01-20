package ru.iteco.account.model.entity;

import lombok.*;
import org.hibernate.Hibernate;
import ru.iteco.account.validation.CreateEntity;
import ru.iteco.account.validation.UpdateEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Objects;

@Entity
@Table(name = "address", schema = "ad")
@Getter
@Setter
@RequiredArgsConstructor
public class AddressEntity {

    @Null(groups = CreateEntity.class)
    @NotNull(groups = UpdateEntity.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "home")
    private String home;

    @OneToOne(mappedBy = "address",
        cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH})
    private UserEntity user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AddressEntity that = (AddressEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "AddressEntity{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", home='" + home + '\'' +
                '}';
    }
}
