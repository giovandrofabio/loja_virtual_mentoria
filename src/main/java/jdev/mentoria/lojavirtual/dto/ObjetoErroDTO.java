package jdev.mentoria.lojavirtual.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class ObjetoErroDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String error;
    private String code;
}
