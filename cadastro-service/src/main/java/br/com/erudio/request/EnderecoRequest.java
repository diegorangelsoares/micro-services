package br.com.erudio.request;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Getter
@Setter
public class EnderecoRequest {

    private long id;
    private String logradouro;
    private Date DataCriacao;

    private String bairro;

    private String cidade;

    private String estado;

    private long cep;

    private long numero;

    private String complemento;

}
