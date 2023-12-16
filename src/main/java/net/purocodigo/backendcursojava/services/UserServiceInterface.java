package net.purocodigo.backendcursojava.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import net.purocodigo.backendcursojava.shared.dto.ProspectoDto;
import net.purocodigo.backendcursojava.shared.dto.PromotorDto;

public interface UserServiceInterface extends UserDetailsService {
    public PromotorDto createUser(PromotorDto user);

    public PromotorDto getUser(String email);

    public List<ProspectoDto> getUserPosts(String email);
}
