package jdev.mentoria.lojavirtual.controller;

import jdev.mentoria.lojavirtual.ExceptionMentoriaJava;
import jdev.mentoria.lojavirtual.model.Acesso;
import jdev.mentoria.lojavirtual.repository.AcessoRepository;
import jdev.mentoria.lojavirtual.service.AcessoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
public class AcessoController {

    @Autowired
    private AcessoService acessoService;

    @Autowired
    private AcessoRepository acessoRepository;

    @ResponseBody /*Poder dar uma restorno da API */
    @PostMapping(value = "**/salvarAcesso") /*Mapeando a url para receber JSON */
    public ResponseEntity<Acesso> salvarAcesso(@RequestBody Acesso acesso) throws ExceptionMentoriaJava { /*Recebe o JSON e converte pra Objeto */

        if(acesso.getId() == null) {
            List<Acesso> acessos = acessoRepository.buscarAcessoDesc(acesso.getDescricao().toUpperCase());
            if(!acessos.isEmpty()){
                throw new ExceptionMentoriaJava("Já existe Acesso com a descrição: " + acesso.getDescricao());
            }
        }

        Acesso acessoSalvo = acessoService.save(acesso);

        return ResponseEntity.status(HttpStatus.OK).body(acessoSalvo);
    }

    @ResponseBody /*Poder dar uma restorno da API */
    @PostMapping(value = "**/deleteAcesso") /*Mapeando a url para receber JSON */
    public ResponseEntity<Acesso> deleteAcesso(@RequestBody Acesso acesso){ /*Recebe o JSON e converte pra Objeto */

        acessoRepository.deleteById(acesso.getId());

        return new ResponseEntity("Acesso Removido",HttpStatus.OK);
    }

    @ResponseBody
    @DeleteMapping(value = "**/deleteAcessoPorId/{id}")
    public ResponseEntity<?> deleteAcessoPorId(@PathVariable("id") Long id) {

        acessoRepository.deleteById(id);

        return new ResponseEntity("Acesso Removido",HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping(value = "**/obterAcesso/{id}")
    public ResponseEntity<Acesso> obterAcesso(@PathVariable("id") Long id) throws ExceptionMentoriaJava {

        //Acesso acesso = acessoRepository.findById(id).orElse(null);
        Acesso acesso = acessoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Não foi encontrado o código: " + id));

//        if (acesso == null) {
//            throw new ExceptionMentoriaJava("Não encontrou Acesso com código: " + id);
//        }

        return new ResponseEntity<Acesso>(acesso,HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping(value = "**/buscarPorDesc/{desc}")
    public ResponseEntity<List<Acesso>> buscarPorDesc(@PathVariable("desc") String desc) {

        List<Acesso> acesso = acessoRepository.buscarAcessoDesc(desc.toUpperCase());

        return new ResponseEntity<List<Acesso>>(acesso,HttpStatus.OK);
    }
}
