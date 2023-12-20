package net.purocodigo.backendcursojava.models.responses;

import lombok.Data;

import java.util.Date;

@Data
public class ProspectoRest {

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

    //private EstatusProspectoRest estatusProspecto;
    private long estatusProspectoId;

    private String prospectoId;

    private String observaciones;


}
