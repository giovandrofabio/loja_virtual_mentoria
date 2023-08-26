package jdev.mentoria.lojavirtual.service;

import java.util.Calendar;


import jdev.mentoria.lojavirtual.ExceptionMentoriaJava;
import jdev.mentoria.lojavirtual.dto.CepDTO;
import jdev.mentoria.lojavirtual.dto.ConsultaCnpjDto;
import jdev.mentoria.lojavirtual.model.PessoaFisica;
import jdev.mentoria.lojavirtual.repository.PesssoaFisicaRepository;
import jdev.mentoria.lojavirtual.util.FunctionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jdev.mentoria.lojavirtual.model.PessoaJuridica;
import jdev.mentoria.lojavirtual.model.Usuario;
import jdev.mentoria.lojavirtual.repository.PesssoaRepository;
import jdev.mentoria.lojavirtual.repository.UsuarioRepository;
import org.springframework.web.client.RestTemplate;


@Service
public class PessoaUserService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PesssoaRepository pesssoaRepository;

    @Autowired
    private PesssoaFisicaRepository pesssoaFisicaRepository;

    @Autowired
    private ServiceSendEmail serviceSendEmail;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public PessoaJuridica salvarPessoaJuridica(PessoaJuridica juridica) {

        for (int i = 0; i< juridica.getEnderecos().size(); i++) {
            juridica.getEnderecos().get(i).setPessoa(juridica);
            juridica.getEnderecos().get(i).setEmpresa(juridica);
        }

        juridica = pesssoaRepository.save(juridica);

        Usuario usuarioPj = usuarioRepository.findUserByPessoa(juridica.getId(), juridica.getEmail());

        if (usuarioPj == null) {

            String constraint = usuarioRepository.consultaConstraintAcesso();
            if (constraint != null) {
                jdbcTemplate.execute("begin; alter table usuarios_acesso drop constraint " + constraint +"; commit;");
            }

            usuarioPj = new Usuario();
            usuarioPj.setDataAtualSenha(Calendar.getInstance().getTime());
            usuarioPj.setEmpresa(juridica);
            usuarioPj.setPessoa(juridica);
            usuarioPj.setLogin(juridica.getEmail());

            String senha = "" + Calendar.getInstance().getTimeInMillis();
            String senhaCript = new BCryptPasswordEncoder().encode(senha);

            usuarioPj.setSenha(senhaCript);

            usuarioPj = usuarioRepository.save(usuarioPj);

            usuarioRepository.insereAcessoUser(usuarioPj.getId());
            usuarioRepository.insereAcessoUser(usuarioPj.getId(),"ROLE_ADMIN");

            /* Fazer o envio de e-mail do logine da senha */
            StringBuilder menssagemHtml = new StringBuilder();

            menssagemHtml.append("<b>Segue abaixo seus dados de acesso para a loja virtual</b><br/>");
            menssagemHtml.append("<b>Login: </b>"+juridica.getEmail()+"<br/>");
            menssagemHtml.append("<b>Senha: </b>").append(senha).append("<br/><br/>");
            menssagemHtml.append("Obrigado!");
            try {

                serviceSendEmail.enviarEmailHtml("Acesso Gerado para Loja Virtual", menssagemHtml.toString() , juridica.getEmail());
            }catch (Exception e) {
                e.printStackTrace();
            }

        }

        return juridica;

    }

    public PessoaFisica salvarPessoaFisica(PessoaFisica pessoaFisica) throws ExceptionMentoriaJava {

//        PessoaFisica pessoaFisicaSalva = pesssoaFisicaRepository.findByCpf(pessoaFisica.getCpf());
//        pessoaFisica.setId(pessoaFisicaSalva.getId());

        for (int i = 0; i< pessoaFisica.getEnderecos().size(); i++) {
            pessoaFisica.getEnderecos().get(i).setPessoa(pessoaFisica);
            //pessoaFisica.getEnderecos().get(i).setEmpresa(pessoaFisica);
        }

        pessoaFisica = pesssoaFisicaRepository.save(pessoaFisica);

        Usuario usuarioPj = usuarioRepository.findUserByPessoa(pessoaFisica.getId(), pessoaFisica.getEmail());

        if (usuarioPj == null) {

            String constraint = usuarioRepository.consultaConstraintAcesso();
            if (constraint != null) {
                jdbcTemplate.execute("begin; alter table usuarios_acesso drop constraint " + constraint +"; commit;");
            }

            usuarioPj = new Usuario();
            usuarioPj.setDataAtualSenha(Calendar.getInstance().getTime());
            usuarioPj.setEmpresa(pessoaFisica.getEmpresa());
            usuarioPj.setPessoa(pessoaFisica);
            usuarioPj.setLogin(pessoaFisica.getEmail());

            String senha = "" + Calendar.getInstance().getTimeInMillis();
            String senhaCript = new BCryptPasswordEncoder().encode(senha);

            usuarioPj.setSenha(senhaCript);

            try {
                usuarioPj = usuarioRepository.save(usuarioPj);
            }catch (Exception e) {
//            e.printStackTrace();
             throw new ExceptionMentoriaJava("Já existe esse email cadastrado como usuario no sistema: ");
            }


            usuarioRepository.insereAcessoUser(usuarioPj.getId());

            StringBuilder menssagemHtml = new StringBuilder();

            menssagemHtml.append("<b>Segue abaixo seus dados de acesso para a loja virtual</b><br/>");
            menssagemHtml.append("<b>Login: </b>"+pessoaFisica.getEmail()+"<br/>");
            menssagemHtml.append("<b>Senha: </b>").append(senha).append("<br/><br/>");
            menssagemHtml.append("Obrigado!");

            try {
                serviceSendEmail.enviarEmailHtml("Acesso Gerado para Loja Virtual", menssagemHtml.toString() , pessoaFisica.getEmail());
            }catch (Exception e) {
                e.printStackTrace();
            }

        }

        return pessoaFisica;
    }

    public CepDTO consultaCep(String cep) {
        return new RestTemplate().getForEntity("https://viacep.com.br/ws/" + cep + "/json/", CepDTO.class).getBody();
    }

    public ConsultaCnpjDto consultaCnpjReceitaWS(String cnpj){
        return new RestTemplate().getForEntity("https://www.receitaws.com.br/v1/cnpj/"+cnpj, ConsultaCnpjDto.class).getBody();
    }

    public PessoaFisica retornaDadosPessoaFisica(String cpf){
        return pesssoaFisicaRepository.findByCpf(cpf);
    }

}
