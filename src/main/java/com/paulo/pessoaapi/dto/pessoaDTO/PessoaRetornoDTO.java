package com.paulo.pessoaapi.dto.pessoaDTO;

import com.paulo.pessoaapi.dto.enderecoDTO.EnderecoDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class PessoaRetornoDTO {
    private Integer id;
    private String nome;
    private LocalDate dataNasc;
    private List<EnderecoDTO> enderecos;
}
