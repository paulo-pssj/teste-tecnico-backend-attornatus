package com.paulo.pessoaapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "ENDERECO")
public class EnderecoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ENDERECO")
    private Integer id;

    @Column(name = "LOGRADOURO")
    private String logradouro;

    @Column(name = "CEP")
    private String cep;

    @Column(name = "NUMERO")
    private String numero;

    @Column(name = "CIDADE")
    private String cidade;

    @Column(name = "PRINCIPAL")
    private boolean principal;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "Pessoa_X_Endereco",
            joinColumns = @JoinColumn(name = "ID_ENDERECO"),
            inverseJoinColumns = @JoinColumn(name = "ID_PESSOA"))
    private Set<PessoaEntity> pessoas = new HashSet<>();

}
