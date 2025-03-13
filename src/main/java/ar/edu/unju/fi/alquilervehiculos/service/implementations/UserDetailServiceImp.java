package ar.edu.unju.fi.alquilervehiculos.service.implementations;

import ar.edu.unju.fi.alquilervehiculos.entities.User;
import ar.edu.unju.fi.alquilervehiculos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailServiceImp implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario no existe"));

        if (userEntity.getRole() == null || userEntity.getRole().getName() == null) {
            throw new UsernameNotFoundException("El usuario no tiene rol asignado");
        }
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + userEntity.getRole().getName());
        return new org.springframework.security.core.userdetails.User(
                userEntity.getUsername(),
                userEntity.getPassword(),
                Collections.singleton(authority)
        );
    }
}
