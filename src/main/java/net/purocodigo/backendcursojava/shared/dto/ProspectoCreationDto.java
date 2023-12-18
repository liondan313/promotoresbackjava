package net.purocodigo.backendcursojava.shared.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProspectoCreationDto implements Serializable {

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
    private String userEmail;
    private long estatusProspectoId;

}
