package jdev.mentoria.lojavirtual.dto;

import java.io.Serializable;

public class CancelaEtiquetaDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String idEtiqueta;
    private String reason_id;
    private String descricao;

    public String getIdEtiqueta() {
        return idEtiqueta;
    }

    public void setIdEtiqueta(String idEtiqueta) {
        this.idEtiqueta = idEtiqueta;
    }

    public String getReason_id() {
        return reason_id;
    }

    public void setReason_id(String reason_id) {
        this.reason_id = reason_id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
