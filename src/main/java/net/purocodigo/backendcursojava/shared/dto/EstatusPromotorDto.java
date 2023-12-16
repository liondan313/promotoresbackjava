package net.purocodigo.backendcursojava.shared.dto;

import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.List;

@Data
public class EstatusPromotorDto implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Long id;

    private String estatus;

    private List<ProspectoDto> prospectos;



}
