package jdev.mentoria.lojavirtual.controller;

import jdev.mentoria.lojavirtual.ExceptionMentoriaJava;
import jdev.mentoria.lojavirtual.dto.CupDescDto;
import jdev.mentoria.lojavirtual.model.CupDesc;
import jdev.mentoria.lojavirtual.model.MarcaProduto;
import jdev.mentoria.lojavirtual.repository.CupDescontoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CupDescontoController {

    @Autowired
    private CupDescontoRepository cupDescontoRepository;

    @ResponseBody
    @GetMapping(value = "**/obterCupomPorId/{id}")
    public ResponseEntity<CupDesc> obterMarcaProduto(@PathVariable("id") Long id) throws ExceptionMentoriaJava {

        CupDesc cupDesc = cupDescontoRepository.findById(id).orElse(null);

        if (cupDesc == null) {
            throw new ExceptionMentoriaJava("Não encontrou Cupom Produto com código: " + id);
        }

        return new ResponseEntity<CupDesc>(cupDesc,HttpStatus.OK);
    }

    @ResponseBody
    @DeleteMapping(value = "**/deleteCupomPorId/{id}")
    public ResponseEntity<?> deleteMarcaPorId(@PathVariable("id") Long id) {

        cupDescontoRepository.deleteById(id);

        return new ResponseEntity("Cupom Produto Removido",HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping(value = "**/salvarCupDesc")
    public ResponseEntity<CupDesc> salvarMarca(@RequestBody @Valid CupDesc cupDesc ) throws ExceptionMentoriaJava { /*Recebe o JSON e converte pra Objeto*/

        CupDesc cupDesc2 = cupDescontoRepository.save(cupDesc);

        return new ResponseEntity<CupDesc>(cupDesc2, HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping(value = "**/listaCupomDesc/{idEmpresa}")
    public ResponseEntity<List<CupDescDto>> listaCupomDesc(@PathVariable("idEmpresa") Long idEmpresa) throws ExceptionMentoriaJava {

        List<CupDesc> cupDesc = cupDescontoRepository.cupDescontoPorEmpresa(idEmpresa);

        if (cupDesc == null) {
            throw new ExceptionMentoriaJava("Não foi localizado nenhum cupom de desconto");
        }

        List<CupDescDto> cupDescDtoList = new ArrayList<CupDescDto>();

        for(CupDesc cp: cupDesc) {

            CupDescDto cupDescDto = new CupDescDto();
            cupDescDto.setId(cp.getId());
            cupDescDto.setCodDesc(cp.getCodDesc());
            cupDescDto.setValorPorcentDesc(cp.getValorPorcentDesc());
            cupDescDto.setValorRealDesc(cp.getValorRealDesc());
            cupDescDto.setDataValidadeCupom(cp.getDataValidadeCupom());

            cupDescDtoList.add(cupDescDto);
        }

        return new ResponseEntity<List<CupDescDto>>(cupDescDtoList, HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping(value = "**/listaCupomDesc")
    public ResponseEntity<List<CupDescDto>> listaCupomDesc() throws ExceptionMentoriaJava {

        List<CupDesc> cupDesc = cupDescontoRepository.findAll();

        if (cupDesc == null) {
            throw new ExceptionMentoriaJava("Não foi localizado nenhum cupom de desconto");
        }

        List<CupDescDto> cupDescDtoList = new ArrayList<CupDescDto>();

        for(CupDesc cp: cupDesc) {

            CupDescDto cupDescDto = new CupDescDto();
            cupDescDto.setId(cp.getId());
            cupDescDto.setCodDesc(cp.getCodDesc());
            cupDescDto.setValorPorcentDesc(cp.getValorPorcentDesc());
            cupDescDto.setValorRealDesc(cp.getValorRealDesc());
            cupDescDto.setDataValidadeCupom(cp.getDataValidadeCupom());

            cupDescDtoList.add(cupDescDto);
        }

        return new ResponseEntity<List<CupDescDto>>(cupDescDtoList , HttpStatus.OK);
    }
}
