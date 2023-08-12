package jdev.mentoria.lojavirtual.controller;

import jdev.mentoria.lojavirtual.ExceptionMentoriaJava;
import jdev.mentoria.lojavirtual.dto.CategoriaProdutoDto;
import jdev.mentoria.lojavirtual.model.CategoriaProduto;
import jdev.mentoria.lojavirtual.repository.CategoriaProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoriaProdutController {

    @Autowired
    private CategoriaProdutoRepository categoriaProdutoRepository;

    @ResponseBody
    @GetMapping(value = "**/buscarPorDescCatgoria/{desc}")
    public ResponseEntity<List<CategoriaProduto>> buscarPorDesc(@PathVariable("desc") String desc) {

        List<CategoriaProduto> acesso = categoriaProdutoRepository.buscarCategoriaDes(desc.toUpperCase());

        return new ResponseEntity<List<CategoriaProduto>>(acesso,HttpStatus.OK);
    }


    @ResponseBody /*Poder dar um retorno da API*/
    @PostMapping(value = "**/deleteCategoria") /*Mapeando a url para receber JSON*/
    public ResponseEntity<?> deleteAcesso(@RequestBody CategoriaProduto CategoriaProduto) { /*Recebe o JSON e converte pra Objeto*/

        categoriaProdutoRepository.deleteById(CategoriaProduto.getId());

        return new ResponseEntity("Categoria Removida",HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping(value = "**/salvarCategoria")
    public ResponseEntity<CategoriaProdutoDto> salvarCategoria(@RequestBody CategoriaProduto categoriaProduto) throws ExceptionMentoriaJava {

        if (categoriaProduto.getEmpresa() == null || (categoriaProduto.getEmpresa().getId() == null)) {
            throw new ExceptionMentoriaJava("A empresa deve ser informada.");
        }

        if (categoriaProduto.getId() == null && categoriaProdutoRepository.existeCatehoria(categoriaProduto.getNomeDesc())) {
            throw new ExceptionMentoriaJava("Não pode cadastar categoria com mesmo nome.");
        }


        CategoriaProduto categoriaSalva = categoriaProdutoRepository.save(categoriaProduto);

        CategoriaProdutoDto categoriaProdutoDto = new CategoriaProdutoDto();
        categoriaProdutoDto.setId(categoriaSalva.getId());
        categoriaProdutoDto.setNomeDesc(categoriaSalva.getNomeDesc());
        categoriaProdutoDto.setEmpresa(categoriaSalva.getEmpresa().getId().toString());

        return new ResponseEntity<CategoriaProdutoDto>(categoriaProdutoDto, HttpStatus.OK);
    }
}
