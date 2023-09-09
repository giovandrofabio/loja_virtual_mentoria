package jdev.mentoria.lojavirtual;

import jdev.mentoria.lojavirtual.dto.CriarWebHook;
import jdev.mentoria.lojavirtual.service.ServiceJunoBoleto;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

@Profile("dev")
@SpringBootTest(classes = LojaVirtualMentoriaApplication.class)
public class TesteJunoBoleto extends TestCase {

    @Autowired
    private ServiceJunoBoleto serviceJunoBoleto;

    @Test
    public void deleteWebHook() throws Exception {

        serviceJunoBoleto.deleteWebHook("wbh_E71095B5BF65E8D2DB018EE8A89BACB8");

    }



    @Test
    public void listaWebHook() throws Exception {

        String retorno = serviceJunoBoleto.listaWebHook();

        System.out.println(retorno);
    }

    @Test
    public void testeCriarWebHook() throws Exception {

        CriarWebHook criarWebHook = new CriarWebHook();
        criarWebHook.setUrl("https://lojavirtualmentoria-env.eba-bijtuvkg.sa-east-1.elasticbeanstalk.com/loja_virtual_mentoria/requisicaojunoboleto/notificacaoapiv2");
        criarWebHook.getEventTypes().add("PAYMENT_NOTIFICATION");
        criarWebHook.getEventTypes().add("BILL_PAYMENT_STATUS_CHANGED");

        String retorno = serviceJunoBoleto.criarWebHook(criarWebHook);

        System.out.println(retorno);
    }

}
