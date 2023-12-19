package net.purocodigo.backendcursojava.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import net.purocodigo.backendcursojava.entities.UserEntity;
import net.purocodigo.backendcursojava.entities.ProspectoEntity;
import net.purocodigo.backendcursojava.entities.TipoUsuarioEntity;
import net.purocodigo.backendcursojava.repositories.ProspectosRepository;
import net.purocodigo.backendcursojava.repositories.TipoUsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import net.purocodigo.backendcursojava.repositories.UserRepository;
import net.purocodigo.backendcursojava.exceptions.EmailExistsException;
import net.purocodigo.backendcursojava.shared.dto.ProspectoDto;
import net.purocodigo.backendcursojava.shared.dto.PromotorDto;

@Service
public class UserService implements UserServiceInterface {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProspectosRepository prospectosRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    ModelMapper mapper;

    @Autowired
    TipoUsuarioRepository tipoUsuarioRepository;

    @Override
    public PromotorDto createUser(PromotorDto user) {

        if (userRepository.findByCorreo(user.getCorreo()) != null)
            throw new EmailExistsException("El correo electronico ya existe");

        UserEntity userEntity = new UserEntity();

        BeanUtils.copyProperties(user, userEntity);

        userEntity.setContrasenaEncryptada(bCryptPasswordEncoder.encode(user.getContrasena()));

        UUID userId = UUID.randomUUID();
        userEntity.setUserId(userId.toString());

        TipoUsuarioEntity tipoUsuarioEntity = tipoUsuarioRepository.findById(user.getTipoUsuarioId());

        userEntity.setTipoUsuario(tipoUsuarioEntity);

        UserEntity storedUserDetails = userRepository.save(userEntity);

        PromotorDto userToReturn = new PromotorDto();
        BeanUtils.copyProperties(storedUserDetails, userToReturn);

        return userToReturn;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByCorreo(email);

        if (userEntity == null) {
            throw new UsernameNotFoundException(email);
        }

        return new User(userEntity.getCorreo(), userEntity.getContrasenaEncryptada(), new ArrayList<>());
    }

    @Override
    public PromotorDto getUser(String email) {
        UserEntity userEntity = userRepository.findByCorreo(email);

        if (userEntity == null) {
            throw new UsernameNotFoundException(email);
        }

        PromotorDto userToReturn = new PromotorDto();

        BeanUtils.copyProperties(userEntity, userToReturn);

        return userToReturn;
    }

    @Override
    public List<ProspectoDto> getUserPosts(String email) {

        UserEntity userEntity = userRepository.findByCorreo(email);

        List<ProspectoEntity> posts = prospectosRepository.getByUsuarioIdOrderByCreatedAtDesc(userEntity.getId());

        List<ProspectoDto> prospectoDtos = new ArrayList<>();

        for (ProspectoEntity post : posts) {
            ProspectoDto prospectoDto = mapper.map(post, ProspectoDto.class);
            prospectoDtos.add(prospectoDto);
        }

        return prospectoDtos;
    }

}
