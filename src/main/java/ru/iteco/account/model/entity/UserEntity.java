package ru.iteco.account.model.entity;

import lombok.*;
import org.hibernate.Hibernate;
import ru.iteco.account.validation.CreateEntity;
import ru.iteco.account.validation.UpdateEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users", schema = "ad")
@Getter
@Setter
@RequiredArgsConstructor
public class UserEntity {

    @Null(groups = CreateEntity.class)
    @NotNull(groups = UpdateEntity.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email")
    private String email;

    @JoinColumn(name = "address", referencedColumnName = "id")
    @OneToOne(cascade = CascadeType.ALL)
    private AddressEntity address;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<BankBookEntity> bankBookEntities;

    @ManyToMany
    @JoinTable(
            name = "users_groups", schema = "ad",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "group_id", referencedColumnName = "id")
    )
    private Set<GroupEntity> groupEntities = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserEntity that = (UserEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
