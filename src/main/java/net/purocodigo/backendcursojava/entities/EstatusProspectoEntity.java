package net.purocodigo.backendcursojava.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "estatus_prospecto")
public class EstatusProspectoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String estatus;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estatusProspecto")
    private List<ProspectoEntity> prospectos = new ArrayList<>();


}
