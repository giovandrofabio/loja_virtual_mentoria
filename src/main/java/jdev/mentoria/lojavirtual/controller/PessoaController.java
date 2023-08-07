package jdev.mentoria.lojavirtual.controller;


import jdev.mentoria.lojavirtual.model.PessoaFisica;
import jdev.mentoria.lojavirtual.repository.PesssoaFisicaRepository;
import jdev.mentoria.lojavirtual.util.ValidaCNPJ;
import jdev.mentoria.lojavirtual.util.ValidaCPF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jdev.mentoria.lojavirtual.ExceptionMentoriaJava;
import jdev.mentoria.lojavirtual.model.PessoaJuridica;
import jdev.mentoria.lojavirtual.repository.PesssoaRepository;
import jdev.mentoria.lojavirtual.service.PessoaUserService;

@RestController
public class PessoaController {

    @Autowired
    private PesssoaRepository pesssoaRepository;

    @Autowired
    private PesssoaFisicaRepository pesssoaFisicaRepository;

    @Autowired
    private PessoaUserService pessoaUserService;

    /*end-point é microsservicos é um API*/
    @ResponseBody
    @PostMapping(value = "**/salvarPj")
    public ResponseEntity<PessoaJuridica> salvarPj(@RequestBody PessoaJuridica pessoaJuridica) throws ExceptionMentoriaJava{

        if (pessoaJuridica == null) {
            throw new ExceptionMentoriaJava("Pessoa juridica nao pode ser NULL");
        }

        if (pessoaJuridica.getId() == null && pesssoaRepository.existeCnpjCadastrado(pessoaJuridica.getCnpj()) != null) {
            throw new ExceptionMentoriaJava("Já existe CNPJ cadastrado com o número: " + pessoaJuridica.getCnpj());
        }

        if (pessoaJuridica.getId() == null && pesssoaRepository.existeInsEstadualCadastrado(pessoaJuridica.getInscEstadual()) != null) {
            throw new ExceptionMentoriaJava("Já existe Inscrição estadual cadastrado com o número: " + pessoaJuridica.getCnpj());
        }

        if(!ValidaCNPJ.isCNPJ(pessoaJuridica.getCnpj())) {
            throw new ExceptionMentoriaJava("CNPJ : " + pessoaJuridica.getCnpj() + " está inválido.");
        }

        pessoaJuridica = pessoaUserService.salvarPessoaJuridica(pessoaJuridica);

        return new ResponseEntity<PessoaJuridica>(pessoaJuridica, HttpStatus.OK);
    }

    /*end-point é microsservicos é um API*/
    @ResponseBody
    @PostMapping(value = "**/salvarPf")
    public ResponseEntity<PessoaFisica> salvarPf(@RequestBody PessoaFisica pessoaFisica) throws ExceptionMentoriaJava{

        if (pessoaFisica == null) {
            throw new ExceptionMentoriaJava("Pessoa física não pode ser NULL");
        }

        if (pessoaFisica.getId() == null && pesssoaFisicaRepository.existeCpfCadastrado(pessoaFisica.getCpf()) != null) {
            throw new ExceptionMentoriaJava("Já existe CPF cadastrado com o número: " + pessoaFisica.getCpf());
        }

        if(!ValidaCPF.isCPF(pessoaFisica.getCpf())) {
            throw new ExceptionMentoriaJava("CPF : " + pessoaFisica.getCpf() + " está inválido.");
        }

        pessoaFisica = pessoaUserService.salvarPessoaFisica(pessoaFisica);

        return new ResponseEntity<PessoaFisica>(pessoaFisica, HttpStatus.OK);
    }
}
