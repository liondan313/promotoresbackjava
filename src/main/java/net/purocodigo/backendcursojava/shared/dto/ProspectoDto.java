package net.purocodigo.backendcursojava.shared.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ProspectoDto implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Long id;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private String calle;
    private Integer numero;
    private String colonia;
    private String codigoPostal;
    private String telefono;
    private String rfc;
    private Date createdAt;
    private PromotorDto promotor;

    private EstatusPromotorDto estatusPromotor;

}
