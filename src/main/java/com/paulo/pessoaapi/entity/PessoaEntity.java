package com.paulo.pessoaapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "PESSOA")
public class PessoaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PESSOA")
    private Integer id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "DATA_NASC")
    private LocalDate dataNasc;

    @ManyToMany(mappedBy = "pessoas", fetch = FetchType.LAZY)
    private Set<EnderecoEntity> enderecos = new HashSet<>();
}
