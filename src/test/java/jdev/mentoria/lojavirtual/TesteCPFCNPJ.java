package jdev.mentoria.lojavirtual;

import jdev.mentoria.lojavirtual.util.ValidaCNPJ;
import jdev.mentoria.lojavirtual.util.ValidaCPF;

public class TesteCPFCNPJ {

    public static void main(String[] args) {
        boolean isCnpj =  ValidaCNPJ.isCNPJ("61.178.365/0001-03");

        System.out.println("Cnpj Válido : " + isCnpj);

        boolean isCpf = ValidaCPF.isCPF("635.364.770-03");

        System.out.println("CPF Válido : " + isCpf);
    }
}
