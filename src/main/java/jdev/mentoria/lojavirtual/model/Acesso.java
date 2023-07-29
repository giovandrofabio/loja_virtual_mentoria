package jdev.mentoria.lojavirtual.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name="acesso")
@SequenceGenerator(name="seq_acesso", sequenceName ="seq_acesso", allocationSize = 1, initialValue = 1)
public class Acesso implements GrantedAuthority {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_acesso")
    private Long id;
    @Column(nullable = false)
    private String descricao; /* Acesso ex: ROLE_ADMIN ou ROLE_SECRETARIO */
    @JsonIgnore
    @Override
    public String getAuthority() {
        return this.descricao;
    }
}
