package jdev.mentoria.lojavirtual;

import jdev.mentoria.lojavirtual.controller.PessoaController;
import jdev.mentoria.lojavirtual.enums.TipoEndereco;
import jdev.mentoria.lojavirtual.model.Endereco;
import jdev.mentoria.lojavirtual.model.PessoaFisica;
import jdev.mentoria.lojavirtual.model.PessoaJuridica;
import jdev.mentoria.lojavirtual.repository.PesssoaRepository;
import jdev.mentoria.lojavirtual.service.ServiceJunoBoleto;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import java.sql.SQLException;
import java.util.Calendar;

@Profile("test")
@SpringBootTest(classes = LojaVirtualMentoriaApplication.class)
public class TestePessoaUsuario extends TestCase {

    @Autowired
    private PessoaController pessoaController;

    @Autowired
    private PesssoaRepository pesssoaRepository;

    @Autowired
    private ServiceJunoBoleto serviceJunoBoleto;

    @Test
    public void testeToken() throws Exception {

        serviceJunoBoleto.obterTokenApiJuno();
    }

    @Test
    public void testCadPessoaJuridica() throws ExceptionMentoriaJava, SQLException {

        PessoaJuridica pessoaJuridica = new PessoaJuridica();
        pessoaJuridica.setCnpj("" + Calendar.getInstance().getTimeInMillis());
        pessoaJuridica.setNome("Alex fernando");
        pessoaJuridica.setEmail("giovandrofabiosantos@hotmail.com");
        pessoaJuridica.setTelefone("45999795800");
        pessoaJuridica.setInscEstadual("65556565656665");
        pessoaJuridica.setInscMunicipal("55554565656565");
        pessoaJuridica.setNomeFantasia("54556565665");
        pessoaJuridica.setRazaoSocial("4656656566");

        Endereco endereco1 = new Endereco();
        endereco1.setBairro("Residencial Recanto das Árvores");
        endereco1.setCep("13180-653");
        endereco1.setComplemento("Casa cinza");
        endereco1.setEmpresa(pessoaJuridica);
        endereco1.setNumero("517");
        endereco1.setPessoa(pessoaJuridica);
        endereco1.setRuaLogra("Rua Quatro");
        endereco1.setTipoEndereco(TipoEndereco.COBRANCA);
        endereco1.setUf("PR");
        endereco1.setCidade("Maringa");

        Endereco endereco2 = new Endereco();
        endereco2.setBairro("Rochdale");
        endereco2.setCep("06226-260");
        endereco2.setComplemento("Casa cinza");
        endereco2.setEmpresa(pessoaJuridica);
        endereco2.setNumero("973");
        endereco2.setPessoa(pessoaJuridica);
        endereco2.setRuaLogra("Rua das Margaridas");
        endereco2.setTipoEndereco(TipoEndereco.ENTREGA);
        endereco2.setUf("PR");
        endereco2.setCidade("Maringa");

        pessoaJuridica.getEnderecos().add(endereco1);
        pessoaJuridica.getEnderecos().add(endereco2);

        pessoaJuridica = pessoaController.salvarPj(pessoaJuridica).getBody();

        assertEquals(true,pessoaJuridica.getId() > 0);

        for (Endereco endereco : pessoaJuridica.getEnderecos()) {
            assertEquals(true, endereco.getId() > 0);
        }

        assertEquals(2, pessoaJuridica.getEnderecos().size());
    }

    @Test
    public void testCadPessoaFisica() throws ExceptionMentoriaJava, SQLException {

        PessoaJuridica pessoaJuridica =  pesssoaRepository.existeCnpjCadastrado("1691093946346");

        PessoaFisica pessoaFisica = new PessoaFisica();
        pessoaFisica.setCpf("145.710.870-47");
        pessoaFisica.setNome("Alex fernando");
        pessoaFisica.setEmail("giovandrofabiosantos1235@hotmail.com");
        pessoaFisica.setTelefone("45999795800");
        pessoaFisica.setEmpresa(pessoaJuridica);

        Endereco endereco1 = new Endereco();
        endereco1.setBairro("Residencial Recanto das Árvores");
        endereco1.setCep("13180-653");
        endereco1.setComplemento("Casa cinza");
        endereco1.setEmpresa(pessoaJuridica);
        endereco1.setNumero("517");
        endereco1.setPessoa(pessoaFisica);
        endereco1.setRuaLogra("Rua Quatro");
        endereco1.setTipoEndereco(TipoEndereco.COBRANCA);
        endereco1.setUf("PR");
        endereco1.setCidade("Maringa");

        Endereco endereco2 = new Endereco();
        endereco2.setBairro("Rochdale");
        endereco2.setCep("06226-260");
        endereco2.setComplemento("Casa cinza");
        endereco2.setEmpresa(pessoaJuridica);
        endereco2.setNumero("973");
        endereco2.setPessoa(pessoaFisica);
        endereco2.setRuaLogra("Rua das Margaridas");
        endereco2.setTipoEndereco(TipoEndereco.ENTREGA);
        endereco2.setUf("PR");
        endereco2.setCidade("Maringa");

        pessoaFisica.getEnderecos().add(endereco1);
        pessoaFisica.getEnderecos().add(endereco2);

        pessoaFisica = pessoaController.salvarPf(pessoaFisica).getBody();

        assertEquals(true,pessoaFisica.getId() > 0);

        for (Endereco endereco : pessoaFisica.getEnderecos()) {
            assertEquals(true, endereco.getId() > 0);
        }

        assertEquals(2, pessoaFisica.getEnderecos().size());
    }
}
