package jdev.mentoria.lojavirtual.controller;

import jdev.mentoria.lojavirtual.ExceptionMentoriaJava;
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
    public ResponseEntity<ContaPagar> salvarAcesso(@RequestBody @Valid ContaPagar contaPagar) throws ExceptionMentoriaJava { /*Recebe o JSON e converte pra Objeto*/

        if (contaPagar.getEmpresa() == null || contaPagar.getEmpresa().getId() <= 0) {
            throw new ExceptionMentoriaJava("Empresa responsável deve ser informada");
        }


        if (contaPagar.getPessoa() == null || contaPagar.getPessoa().getId() <= 0) {
            throw new ExceptionMentoriaJava("Pessoa responsável deve ser informada");
        }

        if (contaPagar.getPessoa_fornecedor() == null || contaPagar.getPessoa_fornecedor().getId() <= 0) {
            throw new ExceptionMentoriaJava("Fornecedor responsável deve ser informada");
        }

//        if(contaPagar.getPessoa().getId() > 0) {
//            PessoaFisica pessoaFisica = pesssoaFisicaRepository.findById(contaPagar.getPessoa().getId()).orElse(null);
//            contaPagar.setPessoa(pessoaFisica);
//        }
//
//        if(contaPagar.getPessoa().getId() > 0) {
//            PessoaJuridica pessoaJuridica = pesssoaRepository.findById(contaPagar.getPessoa_fornecedor().getId()).orElse(null);
//            contaPagar.setPessoa_fornecedor(pessoaJuridica);
//        }
//
//        if(contaPagar.getPessoa().getId() > 0) {
//            PessoaJuridica pessoaJuridica = pesssoaRepository.findById(contaPagar.getEmpresa().getId()).orElse(null);
//            contaPagar.setEmpresa(pessoaJuridica);
//        }


        if (contaPagar.getId() == null) {
            List<ContaPagar> contaPagars = contaPagarRepository.buscaContaDesc(contaPagar.getDescricao().toUpperCase().trim());
            if(!contaPagars.isEmpty()) {
                throw new ExceptionMentoriaJava("Já existe conta a pagar com a mesma descrição.");
            }
        }


        ContaPagar conPagarSalva = contaPagarRepository.save(contaPagar);

        return new ResponseEntity<ContaPagar>(conPagarSalva, HttpStatus.OK);
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
    public ResponseEntity<ContaPagar> obterContaPagar(@PathVariable("id") Long id) throws ExceptionMentoriaJava {

        //ContaPagar contaPagar = contaPagarRepository.findById(id).orElse(null);
        ContaPagar contaPagar = contaPagarRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Não foi encontrado o código: " + id));

//        if (contaPagar == null) {
//            throw new ExceptionMentoriaJava("Não encontrou Conta a Pagar com código: " + id);
//        }

        return new ResponseEntity<ContaPagar>(contaPagar,HttpStatus.OK);
    }



    @ResponseBody
    @GetMapping(value = "**/buscarContaPagarDesc/{desc}")
    public ResponseEntity<List<ContaPagar>> buscarContaPagarDesc(@PathVariable("desc") String desc) {

        List<ContaPagar> contaPagar = contaPagarRepository.buscaContaDesc(desc.toUpperCase());

        return new ResponseEntity<List<ContaPagar>>(contaPagar,HttpStatus.OK);
    }
}
