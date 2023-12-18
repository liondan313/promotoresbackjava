package net.purocodigo.backendcursojava.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.purocodigo.backendcursojava.models.requests.ProspectoCreateRequestModel;
import net.purocodigo.backendcursojava.models.responses.OperationStatusModel;
import net.purocodigo.backendcursojava.models.responses.ProspectoRest;
import net.purocodigo.backendcursojava.services.ProspectoServiceInterface;
import net.purocodigo.backendcursojava.services.UserServiceInterface;
import net.purocodigo.backendcursojava.shared.dto.ProspectoCreationDto;
import net.purocodigo.backendcursojava.shared.dto.ProspectoDto;
import net.purocodigo.backendcursojava.shared.dto.PromotorDto;
import net.purocodigo.backendcursojava.utils.Exposures;

@RestController
@RequestMapping("/prospectos")
public class ProspectosController {

    @Autowired
    ProspectoServiceInterface postService;

    @Autowired
    UserServiceInterface userService;

    @Autowired
    ModelMapper mapper;

    @PostMapping
    public ProspectoRest createPost(@RequestBody @Valid ProspectoCreateRequestModel createRequestModel) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getPrincipal().toString();

        ProspectoCreationDto prospectoCreationDto = mapper.map(createRequestModel, ProspectoCreationDto.class);

        prospectoCreationDto.setUserEmail(email);

        ProspectoDto prospectoDto = postService.createProspecto(prospectoCreationDto);

        ProspectoRest postToReturn = mapper.map(prospectoDto, ProspectoRest.class);

        return postToReturn;
    }

    @GetMapping(path = "/last") // localhost:8080/posts/last
    public List<ProspectoRest> lastPosts() {
        List<ProspectoDto> posts = postService.getLastProspectos();

        List<ProspectoRest> prospectoRests = new ArrayList<>();

        for (ProspectoDto post : posts) {
            ProspectoRest prospectoRest = mapper.map(post, ProspectoRest.class);
            prospectoRests.add(prospectoRest);
        }

        return prospectoRests;
    }

    @GetMapping(path = "/{id}") // localhost:8080/posts/uuid
    public ProspectoRest getPost(@PathVariable String id) {

        ProspectoDto prospectoDto = postService.getPost(id);

        ProspectoRest prospectoRest = mapper.map(prospectoDto, ProspectoRest.class);

        // VALIDAR SI EL POST ES PRIVADO O SI EL POST YA EXPIRO
        //if (prospectoRest.getEstatusProspecto().getId() == Exposures.PRIVATE ){//|| prospectoRest.getExpired()) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            PromotorDto user = userService.getUser(authentication.getPrincipal().toString());

            if (user.getUserId() != prospectoDto.getPromotor().getUserId()) {
                throw new RuntimeException("No tienes permisos para realizar esta accion");
            }
        //}

        return prospectoRest;
    }

    @DeleteMapping(path = "/{id}")
    public OperationStatusModel deletePost(@PathVariable String id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        PromotorDto user = userService.getUser(authentication.getPrincipal().toString());

        OperationStatusModel operationStatusModel = new OperationStatusModel();
        operationStatusModel.setName("DELETE");

        postService.deletePost(id, user.getId());
        operationStatusModel.setResult("SUCCESS");

        return operationStatusModel;
    }

    @PutMapping(path = "/{id}")
    public ProspectoRest updatePost(@RequestBody @Valid ProspectoCreateRequestModel prospectoCreateRequestModel,
                                    @PathVariable String id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        PromotorDto user = userService.getUser(authentication.getPrincipal().toString());

        ProspectoCreationDto postUpdateDto = mapper.map(prospectoCreateRequestModel, ProspectoCreationDto.class);

        ProspectoDto prospectoDto = postService.updatePost(id, user.getId(), postUpdateDto);

        ProspectoRest updatedPost = mapper.map(prospectoDto, ProspectoRest.class);

        return updatedPost;
    }

}
