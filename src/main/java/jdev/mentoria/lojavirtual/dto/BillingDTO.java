package jdev.mentoria.lojavirtual.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

//@Getter
//@Setter
public class BillingDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private boolean free;

    private boolean database;

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public boolean isDatabase() {
        return database;
    }

    public void setDatabase(boolean database) {
        this.database = database;
    }
}
