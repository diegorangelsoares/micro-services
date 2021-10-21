package br.com.erudio.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity(name = "pedido")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Pedido  implements Serializable {

    private static final long serialVersionUID = 1L;

//    public static final String STATUS_ABERTO = 'A';
//    public static final String STATUS_CANCELADO = 'A';

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "data_criacao", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date DataCriacao;

    @Column(name = "data_alteracao", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date DataAlteracao;

    @Column(name = "valorTotal", nullable = false)
    private Double totalTotal;

    @Column(name = "status", nullable = false, length = 180)
    private String status;

    @ManyToOne
    private Cliente cliente;



}
