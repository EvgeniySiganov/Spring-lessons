package ru.iteco.stocksevice.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.iteco.stocksevice.model.entity.UserAuthEntity;
import ru.iteco.stocksevice.repository.UserAuthRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class DBUserDetailService implements UserDetailsService {

    private final UserAuthRepository repository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserAuthEntity userAuthEntity = repository.findByUsername(s).orElseThrow(() -> new UsernameNotFoundException(
                "User not found!"));

        List<SimpleGrantedAuthority> grantedAuthorities = userAuthEntity
                .getRoles()
                .stream()
                .map(roleEntity -> new SimpleGrantedAuthority(roleEntity.getName()))
                .collect(Collectors.toList());

        return new User(userAuthEntity.getUsername(), userAuthEntity.getPassword(), grantedAuthorities);
    }
}
