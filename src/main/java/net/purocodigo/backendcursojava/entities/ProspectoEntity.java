package net.purocodigo.backendcursojava.entities;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity(name = "prospectos")
@EntityListeners(AuditingEntityListener.class)
@Table(indexes = @Index(columnList = "prospectoId", name = "index_prospectoid", unique = true))
public class ProspectoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(name = "primer_apellido", nullable = false)
    private String primerApellido;

    @Column(name = "segundo_apellido")
    private String segundoApellido;

    private String calle;
    private Integer numero;
    private String colonia;

    @Column(name = "codigo_postal")
    private String codigoPostal;

    private String telefono;
    private String rfc;

    @CreatedDate
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "promotor_id")
    private PromotorEntity promotor;

    @Column(nullable = false)
    private String prospectoId;

    @ManyToOne
    @JoinColumn(name = "estatusprospecto_id")
    private EstatusProspectoEntity estatusProspecto;

    @ManyToOne
    @JoinColumn(name = "documentoprospecto_id")
    private DocumentosProspectoEntity documentosProspecto;

    @Column(nullable = false)
    private String observaciones = "";

}
