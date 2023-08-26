package jdev.mentoria.lojavirtual.service;

import jdev.mentoria.lojavirtual.model.Produto;
import jdev.mentoria.lojavirtual.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto retornaDadosProduto(Long id){
        return produtoRepository.findById(id).orElse(null);
    }
}
