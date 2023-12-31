package net.purocodigo.backendcursojava.repositories;

import net.purocodigo.backendcursojava.entities.EstatusProspectoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstatusProspectoRepository extends CrudRepository<EstatusProspectoEntity, Long> {
    EstatusProspectoEntity findById(long id);
}