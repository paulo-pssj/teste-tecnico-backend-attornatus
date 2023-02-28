package com.paulo.pessoaapi.dto.enderecoDTO;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class EnderecoCreateDTO {
    @NotNull(message = "Logradouro não pode ser Nulo.")
    @NotBlank(message = "Logradouro não pode ficar em Branco.")
    private String logradouro;

    @NotNull(message = "CEP não pode ser Nulo.")
    @NotBlank(message = "CEP não pode ficar em Branco.")
    @Pattern(regexp = "(^[0-9]{5})-?([0-9]{3}$)", message = "CEP precisa estar no formato: 00000-000 ou 00000123")
    private String cep;

    @NotNull(message = "Número não pode ser Nulo.")
    @NotBlank(message = "Número não pode ficar em Branco.")
    @Pattern(regexp = "^[0-9]{0,}$", message = "Número não aceita letras ou caracteres especiais.")
    private String numero;

    @NotNull(message = "Cidade não pode ser Nulo.")
    @NotBlank(message = "Cidade não pode ficar em Branco.")
    private String cidade;
}
