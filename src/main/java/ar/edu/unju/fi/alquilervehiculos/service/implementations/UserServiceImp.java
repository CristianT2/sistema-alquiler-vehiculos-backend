package ar.edu.unju.fi.alquilervehiculos.service.implementations;

import ar.edu.unju.fi.alquilervehiculos.dto.UserDTO;
import ar.edu.unju.fi.alquilervehiculos.entities.City;
import ar.edu.unju.fi.alquilervehiculos.entities.Role;
import ar.edu.unju.fi.alquilervehiculos.entities.User;
import ar.edu.unju.fi.alquilervehiculos.exceptions.CustomeException;
import ar.edu.unju.fi.alquilervehiculos.mappers.UserMapper;
import ar.edu.unju.fi.alquilervehiculos.repository.CityRepository;
import ar.edu.unju.fi.alquilervehiculos.repository.RoleRepository;
import ar.edu.unju.fi.alquilervehiculos.repository.UserRepository;
import ar.edu.unju.fi.alquilervehiculos.service.interfaces.IUserService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

//@Slf4j
@Service
public class UserServiceImp implements IUserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final CityRepository cityRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImp(UserRepository userRepository, UserMapper userMapper, CityRepository cityRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.cityRepository = cityRepository;
        this.roleRepository = roleRepository;
    }

    /**
     * Crea un usuario
     * @param userDTO
     * @return
     */
    @Transactional
    @Override
    public UserDTO createUser(UserDTO userDTO) {
        //log.info("Creando usuario: {}", userDTO);
        try{
            City city = cityRepository.findById(userDTO.getCity().getId()).orElseThrow(() -> new CustomeException("Ciudad no encontrada"));
            Role role = roleRepository.findById(userDTO.getRole().getId()).orElseThrow(() -> new CustomeException("Rol no encontrada"));

            User user = userMapper.toEntity(userDTO);
            user.setCity(city);
            user.setRole(role);

            //log.info("Usuario creado correctamente");
            return userMapper.toDTO(userRepository.save(user));
        } catch (Exception e) {
            //log.error("Error al crear el usuario", e.getMessage());
            throw new CustomeException("Error al crear el usuario");
        }
    }

    /**
     * Actualiza el usuario
     * @param id
     * @param userDTO
     * @return
     */
    @Transactional
    @Override
    public UserDTO updateUser(Integer id, UserDTO userDTO) {
        //log.info("Actualizando usuario: {}", userDTO);
        if (id == null || id <= 0) {
            throw new CustomeException("ID de usuario inválido");
        }
        try{
            User user = userRepository.findById(id).orElseThrow(() -> new CustomeException("Usuario no encontrado"));
            City city = cityRepository.findById(userDTO.getCity().getId()).orElseThrow(() -> new CustomeException("Ciudad no encontrada"));
            Role role = roleRepository.findById(userDTO.getRole().getId()).orElseThrow(() -> new CustomeException("Rol no encontrada"));

            userMapper.toEntity(userDTO);
            user.setCity(city);
            user.setRole(role);
            user.setId(id);

            //log.info("Usuario actualizado correctamente");
            return userMapper.toDTO(userRepository.save(user));
        } catch (Exception e) {
            //log.error("Error al intentar actualizar el usuario", e.getMessage());
            throw new CustomeException("Error al intentar actualizar el usuario");
        }
    }

    /**
     * Elimina el usuario seleccionado por su id
     * @param id
     */
    @Transactional
    @Override
    public void deleteUser(Integer id) {
        //log.info("Eliminando usuario por ID: {}", id);
        try{
            User user = userRepository.findById(id).orElseThrow(() -> new CustomeException("Usuario no encontrado"));
            userRepository.delete(user);
            //log.info("Usuario eliminado correctamente");
        } catch (Exception e) {
            //log.error("Error al intentar eliminar el usuario", e.getMessage());
            throw new CustomeException("Error al intentar eliminar el usuario");
        }
    }

    /**
     * Obtiene todos los usuarios
     * @return
     */
    @Override
    public List<UserDTO> getAllUsers() {
        //log.info("Obteniendo todos los usuarios");
        try{
            List<User> users = userRepository.findAll();
            return users.stream()
                    .map(userMapper::toDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            //log.error("Error al intentar obtener los usuarios", e.getMessage());
            throw new CustomeException("Error al intentar obtener los usuarios");
        }
    }

    /**
     * Obtiene el usuario seleccionado por su id
     * @param id
     * @return
     */
    @Override
    public UserDTO getUserById(Integer id) {
        //log.info("Buscando usuario por ID: {}", id);
        try{
            User user = userRepository.findById(id).orElseThrow(() -> new CustomeException("Usuario no encontrado"));
            return userMapper.toDTO(user);
        } catch (Exception e) {
            //log.error("Error al intentar obtener el usuario", e.getMessage());
            throw new CustomeException("Error al intentar obtener el usuario");
        }
    }
}
