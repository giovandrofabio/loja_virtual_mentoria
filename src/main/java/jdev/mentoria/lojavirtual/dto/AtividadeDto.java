package jdev.mentoria.lojavirtual.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AtividadeDto implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String text;
    private String code;

}
