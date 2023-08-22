package jdev.mentoria.lojavirtual.dto;

import jdev.mentoria.lojavirtual.model.Endereco;
import jdev.mentoria.lojavirtual.model.Pessoa;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class VendaCompraLojaVirtualDTO {

    private Long id;

    private BigDecimal valorTotal;

    private BigDecimal valorDesc;

    private Pessoa pessoa;

    private Endereco cobranca;

    private Endereco entrega;

    private BigDecimal valorFrete;

    private List<ItemVendaDTO> itemVendaLoja = new ArrayList<ItemVendaDTO>();
}
