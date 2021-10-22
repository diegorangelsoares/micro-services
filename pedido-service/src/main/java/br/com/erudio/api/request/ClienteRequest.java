package br.com.erudio.api.request;

import br.com.erudio.model.Endereco;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Getter
@Setter
public class ClienteRequest {

    private String nome;
    private long cpf;

    private EnderecoRequest endereco;

}
