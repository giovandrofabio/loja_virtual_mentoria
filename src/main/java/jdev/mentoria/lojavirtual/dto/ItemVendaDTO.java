package jdev.mentoria.lojavirtual.dto;

import jdev.mentoria.lojavirtual.model.Produto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ItemVendaDTO {

    private Double quantidade;

    private Produto produto;
}
