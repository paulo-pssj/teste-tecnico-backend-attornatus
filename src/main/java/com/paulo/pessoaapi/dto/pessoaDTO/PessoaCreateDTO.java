package com.paulo.pessoaapi.dto.pessoaDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class PessoaCreateDTO {
    @NotBlank(message = "Nome não pode ficar em Branco.")
    @NotNull(message = "Nome não pode ser Nulo.")
    @Pattern(regexp = "[\\s[a-zA-Zá-úÁ-Ú]*]{0,}", message = "Não é permitido números e caracteres especiais.")
    @Schema(description = "Nome da Pessoa", example = "Paulo Sergio")
    private String nome;

    @NotNull(message = "Data de Nascimento não pode ficar Nulo.")
    @PastOrPresent
    @Schema(description = "Data de Nascimento da Pessoa", example = "1980-10-01")
    private LocalDate dataNasc;
}
