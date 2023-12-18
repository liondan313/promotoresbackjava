package net.purocodigo.backendcursojava.repositories;

import net.purocodigo.backendcursojava.entities.ProspectoEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ProspectosRepository extends PagingAndSortingRepository<ProspectoEntity, Long> {
    List<ProspectoEntity> getByPromotorIdOrderByCreatedAtDesc(long userId);

    @Query(value = "SELECT * FROM prospectos p ORDER BY created_at DESC", nativeQuery = true)
    List<ProspectoEntity> getLastPublicPosts(@Param("estatusProspecto") long estatusProspectoId, @Param("now") Date now);


    ProspectoEntity findByProspectoId(String postId);
}
