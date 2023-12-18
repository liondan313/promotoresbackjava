package net.purocodigo.backendcursojava.repositories;

import net.purocodigo.backendcursojava.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findByCorreo(String email);
}