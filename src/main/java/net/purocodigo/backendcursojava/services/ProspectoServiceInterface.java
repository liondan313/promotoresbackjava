package net.purocodigo.backendcursojava.services;

import java.util.List;

import net.purocodigo.backendcursojava.shared.dto.ProspectoCreationDto;
import net.purocodigo.backendcursojava.shared.dto.ProspectoDto;

public interface ProspectoServiceInterface {
    public ProspectoDto createProspecto(ProspectoCreationDto promotor);

    public List<ProspectoDto> getLastProspectos();

    public ProspectoDto getPost(String postId);

    public void deletePost(String postId, long userId);

    public ProspectoDto updatePost(String postId, long userId, ProspectoCreationDto postUpdateDto);
}
