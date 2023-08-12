package jdev.mentoria.lojavirtual.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class CategoriaProdutoDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String nomeDesc;

    private String empresa;
}
