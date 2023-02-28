package com.paulo.pessoaapi.dto.pessoaDTO;

import lombok.Data;

import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
public class PessoaEditDTO {
    @Pattern(regexp = "[\\s[a-zA-Zá-úÁ-Ú]*]{0,}", message = "Não é permitido números e caracteres especiais.")
    private String nome;
    private LocalDate dataNasc;
}
