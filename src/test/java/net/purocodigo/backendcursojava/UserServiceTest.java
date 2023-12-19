package net.purocodigo.backendcursojava;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

import net.purocodigo.backendcursojava.entities.TipoUsuarioEntity;
import net.purocodigo.backendcursojava.entities.UserEntity;
import net.purocodigo.backendcursojava.repositories.TipoUsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import net.purocodigo.backendcursojava.exceptions.EmailExistsException;
import net.purocodigo.backendcursojava.repositories.UserRepository;
import net.purocodigo.backendcursojava.services.UserService;
import net.purocodigo.backendcursojava.shared.dto.PromotorDto;

public class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Mock
    TipoUsuarioRepository tipoUsuarioRepository;

    UserEntity userEntity;

    TipoUsuarioEntity tipoUsuarioEntity;

    @Mock
    BCryptPasswordEncoder bCryptPasswordEncoder;

    String encryptedPassword = "abc";

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        userEntity = new UserEntity();

        userEntity.setCorreo("correo");
        userEntity.setNombre("Daniel");
        userEntity.setId(1L);
        userEntity.setContrasenaEncryptada(encryptedPassword);

        tipoUsuarioEntity = new TipoUsuarioEntity();
        tipoUsuarioEntity.setId(1);
        tipoUsuarioEntity.setNombre("PROMOTOR");
        userEntity.setTipoUsuario(tipoUsuarioEntity);
    }

    @Test
    final void testGetUser() {

        when(userRepository.findByCorreo(anyString())).thenReturn(userEntity);

        PromotorDto promotorDto = userService.getUser("test@test.com");

        assertNotNull(promotorDto);

    }

    @Test
    final void testGetUser_UsernameNotFoundException() {
        when(userRepository.findByCorreo(anyString())).thenReturn(null);

        assertThrows(UsernameNotFoundException.class, () -> {
            userService.getUser("test@test.com");
        });
    }

    @Test
    final void testCreateUser() {
        when(userRepository.findByCorreo(anyString())).thenReturn(null);
        when(bCryptPasswordEncoder.encode(anyString())).thenReturn(encryptedPassword);
        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);
        when(tipoUsuarioRepository.findById(anyByte())).thenReturn(tipoUsuarioEntity);

        PromotorDto promotorDto = userService.createUser(new PromotorDto());

        assertNotNull(promotorDto);
        assertEquals(userEntity.getNombre(), promotorDto.getNombre());
    }

    @Test
    final void testCreateUser_EmailExistsException() {
        when(userRepository.findByCorreo(anyString())).thenReturn(userEntity);

        assertThrows(EmailExistsException.class, () -> {

            PromotorDto promotorDto = new PromotorDto();
            promotorDto.setCorreo("abc");

            userService.createUser(promotorDto);
        });

    }

}
