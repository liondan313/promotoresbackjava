package net.purocodigo.backendcursojava.shared.dto;

import lombok.Data;
import net.purocodigo.backendcursojava.entities.ProspectoEntity;
import net.purocodigo.backendcursojava.entities.TipoUsuarioEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class PromotorDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String userId;

    private String correo;

    private String nombre;

    private String contrasena;

    private List<ProspectoEntity> prospectos = new ArrayList<>();

    private TipoUsuarioEntity tipoUsuario;

    private long tipoUsuarioId;


}
