package net.purocodigo.backendcursojava.repositories;

import net.purocodigo.backendcursojava.entities.PromotorEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.purocodigo.backendcursojava.entities.UserEntity;

@Repository
public interface PromotorRepository extends CrudRepository<PromotorEntity, Long> {
    PromotorEntity findByCorreo(String email);
}