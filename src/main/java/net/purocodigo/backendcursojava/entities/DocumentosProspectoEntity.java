package net.purocodigo.backendcursojava.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "documentos_prospecto")
public class DocumentosProspectoEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Lob
    @Column(nullable = false)
    private byte[] archivo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "documentosProspecto")
    private List<ProspectoEntity> prospectos = new ArrayList<>();


}
