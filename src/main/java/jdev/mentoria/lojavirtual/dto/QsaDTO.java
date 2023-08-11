package jdev.mentoria.lojavirtual.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class QsaDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;


    private String nome;
    private String qual;
    private String pais_origem;
    private String nome_rep_legal;
    private String qual_rep_legal;

}