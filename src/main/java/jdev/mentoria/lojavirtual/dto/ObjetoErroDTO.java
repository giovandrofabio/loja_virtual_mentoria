package jdev.mentoria.lojavirtual.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class ObjetoErroDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String error;
    private String code;

    public String getError() {
        return error;
    }
    public void setError(String error) {
        this.error = error;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

}
