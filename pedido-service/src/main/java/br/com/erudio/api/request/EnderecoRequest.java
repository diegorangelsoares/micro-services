package br.com.erudio.api.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class EnderecoRequest {

    private String logradouro;

    private String bairro;

    private String cidade;

    private String estado;

    private long cep;

    private long numero;

    private String complemento;

}
