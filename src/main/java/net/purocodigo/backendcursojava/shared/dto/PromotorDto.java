package net.purocodigo.backendcursojava.shared.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PromotorDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String correo;

    private String nombre;

    private String primerApellido;

    private String segundoApellido;

    private String calle;
    private Integer numero;
    private String colonia;

    private String codigoPostal;

    private String telefono;
    private String rfc;

    private String contrasena;
    private List<ProspectoDto> prospectos;

    private String userId;


}
