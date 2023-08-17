package jdev.mentoria.lojavirtual.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ImagemProdutoDTO implements Serializable {

    private Long id;

    private String imagemOriginal;

    private String imagemMiniatura;

    private Long produto;

    private Long empresa;
}
