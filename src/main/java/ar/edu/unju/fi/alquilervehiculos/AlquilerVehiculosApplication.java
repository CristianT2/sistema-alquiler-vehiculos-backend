package ar.edu.unju.fi.alquilervehiculos;

import ar.edu.unju.fi.alquilervehiculos.entities.City;
import ar.edu.unju.fi.alquilervehiculos.entities.Role;
import ar.edu.unju.fi.alquilervehiculos.entities.User;
import ar.edu.unju.fi.alquilervehiculos.exceptions.CustomeException;
import ar.edu.unju.fi.alquilervehiculos.repository.CityRepository;
import ar.edu.unju.fi.alquilervehiculos.repository.RoleRepository;
import ar.edu.unju.fi.alquilervehiculos.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AlquilerVehiculosApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlquilerVehiculosApplication.class, args);
    }

    @Bean
    CommandLineRunner init(UserRepository userRepository, CityRepository cityRepository, RoleRepository roleRepository){
        return args -> {
            City citySsj = cityRepository.findByPostalCode("4600")
                    .orElseGet(() -> cityRepository.save(City.builder()
                    .name("San Salvador de Jujuy")
                    .postalCode("4600")
                    .build()));

            Role roleAdmin = roleRepository.findByName("ADMIN")
                    .orElseGet(() -> roleRepository.save(Role.builder()
                    .name("ADMIN")
                    .build()));

            Role roleDeveloper = roleRepository.findByName("DEVELOPER")
                    .orElseGet(() -> roleRepository.save(Role.builder()
                    .name("DEVELOPER")
                    .build()));

            Role roleUser = roleRepository.findByName("USER")
                    .orElseGet(() -> roleRepository.save(Role.builder()
                    .name("USER")
                    .build()));

            if (!userRepository.findByDni("11223344").isPresent()) {
                User userAdmin = User.builder()
                        .username("cristianTT1")
                        .password("$2a$10$uDO8eiuPjr1Q9AahgXmhMu3sOr4U1FmLb7Cej/QXGy1bRkRmaweeO") //admin1234
                        .name("Cristian")
                        .lastname("Torrejón")
                        .dni("11223344")
                        .email("c.torrejon96@gmail.com")
                        .phoneNumber("3884752033")
                        .city(citySsj)
                        .address("Av. San Martín 741")
                        .role(roleAdmin)
                        .photo(null)
                        .build();
                userRepository.save(userAdmin);
            }

            if (!userRepository.findByDni("12345678").isPresent()) {
                User userDeveloper = User.builder()
                        .username("develop")
                        .password("$2a$10$gQxUTdxO2w7UrYGUIah1WOxt0k3GQ9scr11qfp75Mp83uZBmE/YkC") //develop1234
                        .name("Lucas")
                        .lastname("Rojas")
                        .dni("12345678")
                        .email("email@email.com")
                        .phoneNumber("3885100871")
                        .city(citySsj)
                        .address("Las Heras 1026")
                        .role(roleDeveloper)
                        .photo(null)
                        .build();
                userRepository.save(userDeveloper);
            }

            if (!userRepository.findByDni("40173894").isPresent()) {
                User userCommon = User.builder()
                        .username("marcos17")
                        .password("$2a$10$eDOL.XJytLnB6VTR.2d/eO8Y4cHmoMMVEkn5oMO5OMjTCmeCU9ITu")  //usuario1234
                        .name("Marcos")
                        .lastname("Alvarez")
                        .dni("40173894")
                        .email("marcos@email.com")
                        .phoneNumber("3884906125")
                        .city(citySsj)
                        .address("Belgrano 94")
                        .role(roleUser)
                        .photo(null)
                        .build();
                userRepository.save(userCommon);
            }

        };
    }
}
