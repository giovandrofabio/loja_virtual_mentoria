package jdev.mentoria.lojavirtual.controller;

import jdev.mentoria.lojavirtual.ExceptionMentoriaJava;
import jdev.mentoria.lojavirtual.dto.ContaPagarDto;
import jdev.mentoria.lojavirtual.dto.CupDescDto;
import jdev.mentoria.lojavirtual.model.ContaPagar;
import jdev.mentoria.lojavirtual.model.PessoaFisica;
import jdev.mentoria.lojavirtual.model.PessoaJuridica;
import jdev.mentoria.lojavirtual.repository.ContaPagarRepository;
import jdev.mentoria.lojavirtual.repository.PesssoaFisicaRepository;
import jdev.mentoria.lojavirtual.repository.PesssoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RestController
public class ContaPagarController {

    @Autowired
    private ContaPagarRepository contaPagarRepository;

    @Autowired
    private PesssoaRepository pesssoaRepository;

    @Autowired
    private PesssoaFisicaRepository pesssoaFisicaRepository;

    @ResponseBody /*Poder dar um retorno da API*/
    @PostMapping(value = "**/salvarContaPagar") /*Mapeando a url para receber JSON*/
    public ResponseEntity<ContaPagarDto> salvarAcesso(@RequestBody @Valid ContaPagar contaPagar) throws ExceptionMentoriaJava { /*Recebe o JSON e converte pra Objeto*/

        if (contaPagar.getEmpresa() == null || contaPagar.getEmpresa().getId() <= 0) {
            throw new ExceptionMentoriaJava("Empresa responsável deve ser informada");
        }


        if (contaPagar.getPessoa() == null || contaPagar.getPessoa().getId() <= 0) {
            throw new ExceptionMentoriaJava("Pessoa responsável deve ser informada");
        }

        if (contaPagar.getPessoa_fornecedor() == null || contaPagar.getPessoa_fornecedor().getId() <= 0) {
            throw new ExceptionMentoriaJava("Fornecedor responsável deve ser informada");
        }

        if(contaPagar.getPessoa().getId() > 0) {
            PessoaJuridica pessoaJuridica = pesssoaRepository.findById(contaPagar.getPessoa_fornecedor().getId()).orElse(null);
            contaPagar.setPessoa_fornecedor(pessoaJuridica);
        }

        if (contaPagar.getId() == null) {
            List<ContaPagar> contaPagars = contaPagarRepository.buscaContaDesc(contaPagar.getDescricao().toUpperCase().trim());
            if(!contaPagars.isEmpty()) {
                throw new ExceptionMentoriaJava("Já existe conta a pagar com a mesma descrição.");
            }
        }

        ContaPagar conPagarSalva = contaPagarRepository.save(contaPagar);

        ContaPagarDto contaPagarDto = getContaPagarDto(conPagarSalva);

        return new ResponseEntity<ContaPagarDto>(contaPagarDto, HttpStatus.OK);
    }

    private static ContaPagarDto getContaPagarDto(ContaPagar conPagarSalva) {
        ContaPagarDto contaPagarDto = new ContaPagarDto();
        contaPagarDto.setId(conPagarSalva.getId());
        contaPagarDto.setDescricao(conPagarSalva.getDescricao());
        contaPagarDto.setValorTotal(conPagarSalva.getValorTotal());
        contaPagarDto.setValorDesconto(conPagarSalva.getValorDesconto());
        contaPagarDto.setStatus(conPagarSalva.getStatus());
        contaPagarDto.setDtVencimento(conPagarSalva.getDtVencimento());
        contaPagarDto.setDtPagamento(conPagarSalva.getDtVencimento());
        contaPagarDto.setPessoa(conPagarSalva.getPessoa().getNome());
        contaPagarDto.setPessoa_fornecedor(conPagarSalva.getPessoa_fornecedor().getNomeFantasia());
        contaPagarDto.setEmpresa(conPagarSalva.getEmpresa().getNomeFantasia());
        return contaPagarDto;
    }

    @ResponseBody /*Poder dar um retorno da API*/
    @PostMapping(value = "**/deleteContaPagar") /*Mapeando a url para receber JSON*/
    public ResponseEntity<String> deleteContaPagar(@RequestBody ContaPagar contaPagar) { /*Recebe o JSON e converte pra Objeto*/

        contaPagarRepository.deleteById(contaPagar.getId());

        return new ResponseEntity<String>("Conta Pagar Removida",HttpStatus.OK);
    }


    @ResponseBody
    @DeleteMapping(value = "**/deleteContaPagarPorId/{id}")
    public ResponseEntity<String> deleteContaPagarPorId(@PathVariable("id") Long id) {

        contaPagarRepository.deleteById(id);

        return new ResponseEntity<String>("Conta Pagar Removida",HttpStatus.OK);
    }




    @ResponseBody
    @GetMapping(value = "**/obterContaPagar/{id}")
    public ResponseEntity<ContaPagarDto> obterContaPagar(@PathVariable("id") Long id) throws ExceptionMentoriaJava {

        ContaPagar contaPagar = contaPagarRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Não foi encontrado o código: " + id));

        if(contaPagar == null){
            contaPagar = new ContaPagar();
        }

        ContaPagarDto contaPagarDto = getContaPagarDto(contaPagar);

        return new ResponseEntity<ContaPagarDto>(contaPagarDto,HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping(value = "**/buscarContaPagarDesc/{desc}")
    public ResponseEntity<List<ContaPagarDto>> buscarContaPagarDesc(@PathVariable("desc") String desc) throws ExceptionMentoriaJava {

        List<ContaPagar> contaPagar = contaPagarRepository.buscaContaDesc(desc.toUpperCase());

        if(contaPagar == null){
            throw new ExceptionMentoriaJava("Não foi localizado nenhuma Conta Pagar");
        }

        List<ContaPagarDto> contaPagarDtoList = new ArrayList<ContaPagarDto>();

        for(ContaPagar cp: contaPagar){
            ContaPagarDto contaPagarDto = getContaPagarDto(cp);

            contaPagarDtoList.add(contaPagarDto);
        }

        return new ResponseEntity<List<ContaPagarDto>>(contaPagarDtoList,HttpStatus.OK);
    }
}
