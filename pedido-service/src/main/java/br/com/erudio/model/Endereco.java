package br.com.erudio.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity(name = "endereco")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Endereco  implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "logradouro", nullable = false, length = 200)
    private String logradouro;

    @Column(name = "bairro", length = 50)
    private String bairro;

    @Column(name = "cidade", length = 50)
    private String cidade;

    @Column(name = "estado", length = 50)
    private String estado;

    @Column(name = "cep")
    private long cep;

    @Column(name = "numero")
    private long numero;

    @Column(name = "complemento", length = 150)
    private String complemento;

}
