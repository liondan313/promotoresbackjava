package net.purocodigo.backendcursojava.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "tipo_usuario")
public class TipoUsuarioEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoUsuario")
    private List<UserEntity> tipoUsuario = new ArrayList<>();


}
