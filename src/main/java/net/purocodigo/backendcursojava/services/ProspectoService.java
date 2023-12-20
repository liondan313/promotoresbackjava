package net.purocodigo.backendcursojava.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import net.purocodigo.backendcursojava.entities.*;
import net.purocodigo.backendcursojava.repositories.ProspectosRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.purocodigo.backendcursojava.repositories.EstatusProspectoRepository;
import net.purocodigo.backendcursojava.repositories.UserRepository;
import net.purocodigo.backendcursojava.shared.dto.ProspectoCreationDto;
import net.purocodigo.backendcursojava.shared.dto.ProspectoDto;
import net.purocodigo.backendcursojava.utils.Exposures;

@Service
public class ProspectoService implements ProspectoServiceInterface {

    @Autowired
    ProspectosRepository prospectosRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EstatusProspectoRepository estatusProspectoRepository;

    @Autowired
    ModelMapper mapper;

    private static final int TIPO_USUARIO_SUPERVISOR = 2;

    @Override
    public ProspectoDto createProspecto(ProspectoCreationDto post) {

        UserEntity userEntity = userRepository.findByCorreo(post.getUserEmail());
        EstatusProspectoEntity estatusProspectoEntity = estatusProspectoRepository.findById(post.getEstatusProspectoId());

        ProspectoEntity prospectoEntity = new ProspectoEntity();

        prospectoEntity.setUsuario(userEntity);
        prospectoEntity.setEstatusProspecto(estatusProspectoEntity);
        prospectoEntity.setNombre(post.getNombre());
        prospectoEntity.setPrimerApellido(post.getPrimerApellido());
        prospectoEntity.setSegundoApellido(post.getSegundoApellido());
        prospectoEntity.setColonia(post.getColonia());
        prospectoEntity.setCalle(post.getCalle());
        prospectoEntity.setCodigoPostal(post.getCodigoPostal());
        prospectoEntity.setNumero(post.getNumero());
        prospectoEntity.setTelefono(post.getTelefono());
        prospectoEntity.setRfc(post.getRfc());


        prospectoEntity.setProspectoId(UUID.randomUUID().toString());
        //postEntity.setExpiresAt(new Date(System.currentTimeMillis() + (post.getExpirationTime() * 60000)));

        ProspectoEntity createdPost = prospectosRepository.save(prospectoEntity);

        ProspectoDto postToReturn = mapper.map(createdPost, ProspectoDto.class);

        return postToReturn;
    }

    @Override
    public List<ProspectoDto> getLastProspectos() {

        List<ProspectoEntity> postEntities = prospectosRepository.getLastPublicPosts(Exposures.PUBLIC,
                new Date(System.currentTimeMillis()));

        List<ProspectoDto> prospectoDtos = new ArrayList<>();

        for (ProspectoEntity post : postEntities) {
            ProspectoDto prospectoDto = mapper.map(post, ProspectoDto.class);
            prospectoDtos.add(prospectoDto);
        }

        return prospectoDtos;
    }

    @Override
    public ProspectoDto getPost(String postId) {

        ProspectoEntity postEntity = prospectosRepository.findByProspectoId(postId);
        ProspectoDto prospectoDto = mapper.map(postEntity, ProspectoDto.class);
        return prospectoDto;
    }

    @Override
    public void deletePost(String postId, long userId) {
        ProspectoEntity postEntity = prospectosRepository.findByProspectoId(postId);
        if (postEntity.getUsuario().getId() != userId)
            throw new RuntimeException("No se puede realizar esta accion");

        prospectosRepository.delete(postEntity);

    }

    @Override
    public ProspectoDto updatePost(String postId, long userId, ProspectoCreationDto postUpdateDto) {
        ProspectoEntity postEntity = prospectosRepository.findByProspectoId(postId);

        if (postEntity.getUsuario().getTipoUsuario().getId() != TIPO_USUARIO_SUPERVISOR ) {
            throw new RuntimeException("No tienes permisos para realizar esta accion");
        }

        EstatusProspectoEntity exposureEntity = estatusProspectoRepository.findById(postUpdateDto.getEstatusProspectoId());

        postEntity.setEstatusProspecto(exposureEntity);

        //BeanUtils.copyProperties(postUpdateDto, postEntity);

        ProspectoEntity updatedPost = prospectosRepository.save(postEntity);

        ProspectoDto prospectoDto = mapper.map(updatedPost, ProspectoDto.class);

        return prospectoDto;

    }

}
