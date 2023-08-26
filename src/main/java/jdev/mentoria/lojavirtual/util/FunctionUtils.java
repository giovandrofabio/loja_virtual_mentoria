package jdev.mentoria.lojavirtual.util;

public class FunctionUtils {
    public static boolean isEmpty(String texto) {
        return (texto == null || texto.trim().equals(""));
    }


    public static String removerMascaraTexto(String texto) {
        if (isEmpty(texto)) {
            return texto;
        }
        return texto.replaceAll("[^0-9a-zA-Z]", "");
    }

    public static boolean isNull(Object object) {
        return object == null;
    }
}
