package net.purocodigo.backendcursojava.models.responses;

import lombok.Data;
import net.purocodigo.backendcursojava.entities.TipoUsuarioEntity;

import java.util.List;

@Data
public class UserRest {
    private String userId;
    private String nombre;
    private String primerApellido;
    private String correo;
    private List<ProspectoRest> prospectos;
    private long tipoUsuarioId;

}
