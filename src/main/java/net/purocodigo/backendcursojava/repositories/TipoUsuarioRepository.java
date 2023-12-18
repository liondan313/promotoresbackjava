package net.purocodigo.backendcursojava.repositories;

import net.purocodigo.backendcursojava.entities.EstatusProspectoEntity;
import net.purocodigo.backendcursojava.entities.TipoUsuarioEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoUsuarioRepository extends CrudRepository<TipoUsuarioEntity, Long> {
    TipoUsuarioEntity findById(long id);
}