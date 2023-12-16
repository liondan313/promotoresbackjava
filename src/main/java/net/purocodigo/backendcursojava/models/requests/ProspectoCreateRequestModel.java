package net.purocodigo.backendcursojava.models.requests;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
public class ProspectoCreateRequestModel {

    @NotEmpty(message = "El nombre es obligatorio")
    private String nombre;

    @NotEmpty(message = "El primerApellido es obligatorio")
    private String primerApellido;

    @NotEmpty(message = "El segundoApellido es obligatorio")
    private String segundoApellido;

    @NotEmpty(message = "La calle es obligatorio")
    private String calle;

    @NotEmpty(message = "El numero es obligatorio")
    private String numero;

    @NotEmpty(message = "La colonia es obligatorio")
    private String colonia;

    @NotEmpty(message = "La codigoPostal es obligatorio")
    private String codigoPostal;

    @NotEmpty(message = "El telefono es obligatorio")
    private String telefono;

    @NotEmpty(message = "La rfc es obligatorio")
    private String rfc;

    @NotNull(message = "El estatus del prospecto es obligatoria")
    @Range(min = 1, max = 2, message = "El estatus del prospecto es invalida")
    private long estatusProspectoId;


}









