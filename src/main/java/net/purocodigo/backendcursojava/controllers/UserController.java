package net.purocodigo.backendcursojava.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import net.purocodigo.backendcursojava.entities.EstatusProspectoEntity;
import net.purocodigo.backendcursojava.entities.TipoUsuarioEntity;
import net.purocodigo.backendcursojava.repositories.TipoUsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.purocodigo.backendcursojava.models.requests.UserDetailsRequestModel;
import net.purocodigo.backendcursojava.models.responses.ProspectoRest;
import net.purocodigo.backendcursojava.models.responses.UserRest;
import net.purocodigo.backendcursojava.services.UserServiceInterface;
import net.purocodigo.backendcursojava.shared.dto.ProspectoDto;
import net.purocodigo.backendcursojava.shared.dto.PromotorDto;

@RestController
@RequestMapping("/users") // localhost:8080/setTipoUsuarioIdusers
public class UserController {

    @Autowired
    UserServiceInterface userService;

    @Autowired
    ModelMapper mapper;

    @GetMapping(produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public UserRest getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getPrincipal().toString();

        PromotorDto promotorDto = userService.getUser(email);

        promotorDto.setTipoUsuarioId(promotorDto.getTipoUsuario().getId());

        UserRest userToReturn = new UserRest(); //mapper.map(promotorDto, UserRest.class);

        userToReturn.setPrimerApellido(promotorDto.getPrimerApellido());
        userToReturn.setNombre(promotorDto.getNombre());
        userToReturn.setCorreo(promotorDto.getCorreo());
        userToReturn.setTipoUsuarioId(promotorDto.getTipoUsuarioId());

        return userToReturn;
    }

    @PostMapping
    public UserRest createUser(@RequestBody @Valid UserDetailsRequestModel userDetails) {

        UserRest userToReturn = new UserRest();

        PromotorDto promotorDto = new PromotorDto();

        promotorDto.setPrimerApellido(userDetails.getLastName());
        promotorDto.setNombre(userDetails.getFirstName());
        promotorDto.setCorreo(userDetails.getEmail());
        promotorDto.setContrasena(userDetails.getPassword());

        UUID userID = UUID.randomUUID();
        promotorDto.setUserId(userID.toString());
        promotorDto.setTipoUsuarioId(userDetails.getTipoUsuarioId());

        PromotorDto createdUser = userService.createUser(promotorDto);

        userToReturn.setNombre(createdUser.getNombre());
        userToReturn.setPrimerApellido(createdUser.getPrimerApellido());
        userToReturn.setCorreo(createdUser.getCorreo());
        userToReturn.setUserId(createdUser.getUserId());
        userToReturn.setTipoUsuarioId(createdUser.getTipoUsuario().getId());

        return userToReturn;
    }

    @GetMapping(path = "/prospectos") // localhost:8080/users/posts
    public List<ProspectoRest> getPosts() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getPrincipal().toString();

        List<ProspectoDto> posts = userService.getUserPosts(email);

        List<ProspectoRest> prospectoRests = new ArrayList<>();

        for (ProspectoDto post : posts) {
            ProspectoRest prospectoRest = mapper.map(post, ProspectoRest.class);
            prospectoRests.add(prospectoRest);
        }

        return prospectoRests;
    }
}
