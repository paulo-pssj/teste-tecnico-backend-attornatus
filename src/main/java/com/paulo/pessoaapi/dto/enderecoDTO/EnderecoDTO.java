package com.paulo.pessoaapi.dto.enderecoDTO;

import lombok.Data;

@Data
public class EnderecoDTO {
    private Integer id;
    private String logradouro;
    private String cep;
    private String numero;
    private String cidade;
    private boolean principal;
}
