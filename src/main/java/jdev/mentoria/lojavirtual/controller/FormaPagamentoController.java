package jdev.mentoria.lojavirtual.controller;

import jdev.mentoria.lojavirtual.ExceptionMentoriaJava;
import jdev.mentoria.lojavirtual.dto.FormaPagamentoDto;
import jdev.mentoria.lojavirtual.model.FormaPagamento;
import jdev.mentoria.lojavirtual.repository.FormaPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class FormaPagamentoController {

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    @ResponseBody
    @PostMapping(value = "**/salvarFormaPagamento")
    public ResponseEntity<FormaPagamento> salvarFormaPagamento(@RequestBody @Valid FormaPagamento formaPagamento)
            throws ExceptionMentoriaJava {

        formaPagamento = formaPagamentoRepository.save(formaPagamento);

        return new ResponseEntity<FormaPagamento>(formaPagamento, HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping(value = "**/listaFormaPagamento/{idEmpresa}")
    public ResponseEntity<List<FormaPagamentoDto>> listaFormaPagamentoidEmpresa(@PathVariable(value = "idEmpresa") Long idEmpresa) throws ExceptionMentoriaJava {

        List<FormaPagamento> formaPagamento = formaPagamentoRepository.findAll(idEmpresa);

        if (formaPagamento == null) {
            throw new ExceptionMentoriaJava("Não foi localizado nenhuma forma de pagamento");
        }

        List<FormaPagamentoDto> formaPagamentoDtoList = new ArrayList<FormaPagamentoDto>();

        for(FormaPagamento fp: formaPagamento){

            FormaPagamentoDto formaPagamentoDto = new FormaPagamentoDto();
            formaPagamentoDto.setId(fp.getId());
            formaPagamentoDto.setDescricao(fp.getDescricao());
            formaPagamentoDto.setEmpresa(fp.getEmpresa().getNome());

            formaPagamentoDtoList.add(formaPagamentoDto);
        }

        return new ResponseEntity<List<FormaPagamentoDto>>(formaPagamentoDtoList, HttpStatus.OK);

    }

    @ResponseBody
    @GetMapping(value = "**/listaFormaPagamento")
    public ResponseEntity<List<FormaPagamentoDto>> listaFormaPagamento() throws ExceptionMentoriaJava {

        List<FormaPagamento> formaPagamento = formaPagamentoRepository.findAll();

        if (formaPagamento == null) {
            throw new ExceptionMentoriaJava("Não foi localizado nenhuma forma de pagamento");
        }

        List<FormaPagamentoDto> formaPagamentoDtoList = new ArrayList<FormaPagamentoDto>();

        for(FormaPagamento fp: formaPagamento){

            FormaPagamentoDto formaPagamentoDto = new FormaPagamentoDto();
            formaPagamentoDto.setId(fp.getId());
            formaPagamentoDto.setDescricao(fp.getDescricao());
            formaPagamentoDto.setEmpresa(fp.getEmpresa().getNome());

            formaPagamentoDtoList.add(formaPagamentoDto);
        }

        return new ResponseEntity<List<FormaPagamentoDto>>(formaPagamentoDtoList, HttpStatus.OK);

    }

}
