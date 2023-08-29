package jdev.mentoria.lojavirtual.controller;

import jdev.mentoria.lojavirtual.ExceptionMentoriaJava;
import jdev.mentoria.lojavirtual.dto.*;
import jdev.mentoria.lojavirtual.model.NotaFiscalCompra;
import jdev.mentoria.lojavirtual.model.NotaFiscalVenda;
import jdev.mentoria.lojavirtual.model.VendaCompraLojaVirtual;
import jdev.mentoria.lojavirtual.repository.NotaFiscalCompraRepository;
import jdev.mentoria.lojavirtual.repository.NotaFiscalVendaRepository;
import jdev.mentoria.lojavirtual.service.NotaFiscalCompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class NotaFiscalCompraController {

    @Autowired
    private NotaFiscalCompraRepository notaFiscalCompraRepository;

    @Autowired
    private NotaFiscalVendaRepository notaFiscalVendaRepository;

    @Autowired
    private NotaFiscalCompraService notaFiscalCompraService;

    @ResponseBody
    @PostMapping(value = "**/relatorioStatusCompra")
    public ResponseEntity<List<ObjetoRelatorioStatusCompra>> relatorioStatusCompra (@Valid
                                                                                    @RequestBody  ObjetoRelatorioStatusCompra objetoRelatorioStatusCompra){

        List<ObjetoRelatorioStatusCompra> retorno = new ArrayList<ObjetoRelatorioStatusCompra>();

        retorno = notaFiscalCompraService.relatorioStatusVendaLojaVirtual(objetoRelatorioStatusCompra);

        return new ResponseEntity<List<ObjetoRelatorioStatusCompra>>(retorno, HttpStatus.OK);

    }


    @ResponseBody
    @PostMapping(value = "**/relatorioProdCompradoNotaFiscal")
    public ResponseEntity<List<ObejtoRequisicaoRelatorioProdCompraNotaFiscalDTO>> relatorioProdCompradoNotaFiscal
            (@Valid @RequestBody ObejtoRequisicaoRelatorioProdCompraNotaFiscalDTO obejtoRequisicaoRelatorioProdCompraNotaFiscalDto){

        List<ObejtoRequisicaoRelatorioProdCompraNotaFiscalDTO> retorno =
                new ArrayList<ObejtoRequisicaoRelatorioProdCompraNotaFiscalDTO>();

        retorno = notaFiscalCompraService.gerarRelatorioProdCompraNota(obejtoRequisicaoRelatorioProdCompraNotaFiscalDto);


        return new ResponseEntity<List<ObejtoRequisicaoRelatorioProdCompraNotaFiscalDTO>>(retorno, HttpStatus.OK);

    }

    @ResponseBody
    @PostMapping(value = "**/relatorioProdAlertaEstoque")
    public ResponseEntity<List<ObejtoRequisicaoRelatorioProdutoAlertaEstoque>> relatorioProdAlertaEstoque
            (@Valid @RequestBody ObejtoRequisicaoRelatorioProdutoAlertaEstoque obejtoRequisicaoRelatorioProdCompraNotaFiscalDto ){

        List<ObejtoRequisicaoRelatorioProdutoAlertaEstoque> retorno =
                new ArrayList<ObejtoRequisicaoRelatorioProdutoAlertaEstoque>();

        retorno = notaFiscalCompraService.gerarRelatorioAlertaEstoque(obejtoRequisicaoRelatorioProdCompraNotaFiscalDto);


        return new ResponseEntity<List<ObejtoRequisicaoRelatorioProdutoAlertaEstoque>>(retorno, HttpStatus.OK);

    }


    @ResponseBody
    @PostMapping(value = "**/salvarNotaFiscalCompra")
    public ResponseEntity<NotaFiscalCompra> salvarNotaFiscalCompra(@RequestBody @Valid NotaFiscalCompra notaFiscalCompra) throws ExceptionMentoriaJava { /*Recebe o JSON e converte pra Objeto*/

        if (notaFiscalCompra.getId() == null) {

            if (notaFiscalCompra.getDescricaoObs() != null) {
                boolean existe = notaFiscalCompraRepository.existeNotaComDescricao(notaFiscalCompra.getDescricaoObs().toUpperCase().trim());

                if(existe) {
                    throw new ExceptionMentoriaJava("Já existe Nota de compra com essa mesma descrição : " + notaFiscalCompra.getDescricaoObs());
                }
            }
        }

        if (notaFiscalCompra.getPessoa() == null || notaFiscalCompra.getPessoa().getId() <= 0) {
            throw new ExceptionMentoriaJava("A Pessoa Juridica da nota fiscal deve ser informada.");
        }

        if (notaFiscalCompra.getEmpresa() == null || notaFiscalCompra.getEmpresa().getId() <= 0) {
            throw new ExceptionMentoriaJava("A empresa responsável deve ser infromada.");
        }

        if (notaFiscalCompra.getContaPagar() == null || notaFiscalCompra.getContaPagar().getId() <= 0) {
            throw new ExceptionMentoriaJava("A cponta a pagar da nota deve ser informada.");
        }

        NotaFiscalCompra notaFiscalCompraSalva = notaFiscalCompraRepository.save(notaFiscalCompra);

        return new ResponseEntity<NotaFiscalCompra>(notaFiscalCompraSalva, HttpStatus.OK);
    }

    @ResponseBody
    @DeleteMapping(value = "**/deleteNotaFiscalCompraPorId/{id}")
    public ResponseEntity<?> deleteNotaFiscalCompraPorId(@PathVariable("id") Long id) {

        notaFiscalCompraRepository.deleteItemNotaFiscalCompra(id);/*Delete os filhos*/
        notaFiscalCompraRepository.deleteById(id); /*Deleta o pai*/

        return new ResponseEntity("Nota Fiscal Compra Removida",HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping(value = "**/obterNotaFiscalCompra/{id}")
    public ResponseEntity<NotaFiscalCompra> obterNotaFiscalCompra(@PathVariable("id") Long id) throws ExceptionMentoriaJava {

        //NotaFiscalCompra notaFiscalCompra = notaFiscalCompraRepository.findById(id).orElse(null);
        NotaFiscalCompra notaFiscalCompra = notaFiscalCompraRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Não foi encontrado o código: " + id));

//        if (notaFiscalCompra == null) {
//            throw new ExceptionMentoriaJava("Não encontrou Nota Fiscal com código: " + id);
//        }

        return new ResponseEntity<NotaFiscalCompra>(notaFiscalCompra, HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping(value = "**/buscarNotaFiscalPorDesc/{desc}")
    public ResponseEntity<List<NotaFiscalCompra>> buscarNotaFiscalPorDesc(@PathVariable("desc") String desc) {

        List<NotaFiscalCompra>  notaFiscalCompras = notaFiscalCompraRepository.buscaNotaDesc(desc.toUpperCase().trim());

        return new ResponseEntity<List<NotaFiscalCompra>>(notaFiscalCompras,HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping(value = "**/obterNotaFiscalCompraDaVenda/{idvenda}")
    public ResponseEntity<List<NotaFiscalVendaDto>> obterNotaFiscalCompraDaVenda(@PathVariable("idvenda") Long idvenda) throws ExceptionMentoriaJava {

        List<NotaFiscalVenda> notaFiscalVenda = notaFiscalVendaRepository.buscaNotaPorVenda(idvenda);

        if (notaFiscalVenda == null) {
            throw new ExceptionMentoriaJava("Não encontrou Nota Fiscal de venda com código da venda: " + idvenda);
        }

        List<NotaFiscalVendaDto> notaFiscalVendaDtoList = new ArrayList<NotaFiscalVendaDto>();

        for (NotaFiscalVenda ntf : notaFiscalVenda) {

            NotaFiscalVendaDto  notaFiscalVendaDto = new NotaFiscalVendaDto();
            notaFiscalVendaDto.setId(ntf.getId());
            notaFiscalVendaDto.setNumero(ntf.getNumero());
            notaFiscalVendaDto.setSerie(ntf.getSerie());
            notaFiscalVendaDto.setTipo(ntf.getTipo());
            notaFiscalVendaDto.setXml(ntf.getXml());
            notaFiscalVendaDto.setPdf(ntf.getPdf());
            notaFiscalVendaDto.getVendaCompraLojaVirtualDTO().setId(ntf.getVendaCompraLojaVirtual().getId());
            notaFiscalVendaDto.getVendaCompraLojaVirtualDTO().setValorTotal(ntf.getVendaCompraLojaVirtual().getValorTotal());
            notaFiscalVendaDto.setEmpresa(ntf.getEmpresa().getNome());
            notaFiscalVendaDtoList.add(notaFiscalVendaDto);
        }


        return new ResponseEntity<List<NotaFiscalVendaDto>>(notaFiscalVendaDtoList, HttpStatus.OK);
    }


    @ResponseBody
    @GetMapping(value = "**/obterNotaFiscalCompraDaVendaUnico/{idvenda}")
    public ResponseEntity<NotaFiscalVendaDto> obterNotaFiscalCompraDaVendaUnico(@PathVariable("idvenda") Long idvenda) throws ExceptionMentoriaJava {

        NotaFiscalVenda notaFiscalCompra = notaFiscalVendaRepository.buscaNotaPorVendaUnica(idvenda);

        if (notaFiscalCompra == null) {
            throw new ExceptionMentoriaJava("Não encontrou Nota Fiscal de venda com código da venda: " + idvenda);
        }

        NotaFiscalVendaDto  notaFiscalVendaDto = new NotaFiscalVendaDto();
        notaFiscalVendaDto.setId(notaFiscalCompra.getId());
        notaFiscalVendaDto.setNumero(notaFiscalCompra.getNumero());
        notaFiscalVendaDto.setSerie(notaFiscalCompra.getSerie());
        notaFiscalVendaDto.setTipo(notaFiscalCompra.getTipo());
        notaFiscalVendaDto.setXml(notaFiscalCompra.getXml());
        notaFiscalVendaDto.setPdf(notaFiscalCompra.getPdf());
        notaFiscalVendaDto.getVendaCompraLojaVirtualDTO().setId(notaFiscalCompra.getVendaCompraLojaVirtual().getId());
        notaFiscalVendaDto.getVendaCompraLojaVirtualDTO().setValorTotal(notaFiscalCompra.getVendaCompraLojaVirtual().getValorTotal());
        notaFiscalVendaDto.setEmpresa(notaFiscalCompra.getEmpresa().getNome());

        return new ResponseEntity<NotaFiscalVendaDto>(notaFiscalVendaDto, HttpStatus.OK);
    }
}
