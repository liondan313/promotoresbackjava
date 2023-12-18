package net.purocodigo.backendcursojava.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "usuarios")
@Table(indexes = { @Index(columnList = "userId", name = "index_userid", unique = true),
        @Index(columnList = "correo", name = "index_correo", unique = true) })
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String correo;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String contrasenaEncryptada;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "promotor")
    private List<ProspectoEntity> prospectos = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "tipousuario_id")
    private TipoUsuarioEntity tipoUsuario;


}
