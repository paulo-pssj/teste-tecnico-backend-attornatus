package com.paulo.pessoaapi.controller.documentation;

import com.paulo.pessoaapi.dto.pessoaDTO.PessoaCreateDTO;
import com.paulo.pessoaapi.dto.pessoaDTO.PessoaEditDTO;
import com.paulo.pessoaapi.dto.pessoaDTO.PessoaRetornoDTO;
import com.paulo.pessoaapi.exception.RegraDeNegocioException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

public interface PessoaDocumentationInterface {
    @Operation(summary = "Listar pessoas", description = "Retorna uma lista com todas pessoas cadastrada.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso."),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    ResponseEntity<List<PessoaRetornoDTO>> listarPessoas();

    @Operation(summary = "Consultar pessoa por id", description = "Retorna todos os dados de uma pessoa")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Pessoa retornada com sucesso."),
                    @ApiResponse(responseCode = "400", description = "Pessoa não encontrada."),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    ResponseEntity<PessoaRetornoDTO> consultarPessoaPorId(@PathVariable("idPessoa") Integer idPessoa) throws RegraDeNegocioException;

    @Operation(summary = "Criar pessoa", description = "Cria uma pessoa.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Pessoa criada com sucesso."),
                    @ApiResponse(responseCode = "400", description = "Erro ao criar pessoa."),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    ResponseEntity<PessoaRetornoDTO> criar(@Valid @RequestBody PessoaCreateDTO pessoaCreateDTO);

    @Operation(summary = "Editar pessoa", description = "Edita dados de uma pessoa.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Pessoa editada com sucesso."),
                    @ApiResponse(responseCode = "400", description = "Erro ao editar pessoa."),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    ResponseEntity<PessoaRetornoDTO> editar(@PathVariable("idPessoa") Integer idPessoa,
                                            @RequestBody PessoaEditDTO pessoaEditDTO);
}
