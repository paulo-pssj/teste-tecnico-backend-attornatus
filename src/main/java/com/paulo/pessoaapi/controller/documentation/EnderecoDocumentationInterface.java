package com.paulo.pessoaapi.controller.documentation;

import com.paulo.pessoaapi.dto.enderecoDTO.EnderecoCreateDTO;
import com.paulo.pessoaapi.dto.enderecoDTO.EnderecoDTO;
import com.paulo.pessoaapi.exception.RegraDeNegocioException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface EnderecoDocumentationInterface {
    @Operation(summary = "Listar endereços por pessoa", description = "Retorna uma lista com todos endereços de uma pessoa.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso."),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    ResponseEntity<List<EnderecoDTO>> listarEnderecoPorPessoa(@PathVariable("idPessoa") Integer idPessoa);

    @Operation(summary = "Criar endereço para uma pessoa", description = "Cria endereço para pessoa passada por id.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Endereco criado com sucesso."),
                    @ApiResponse(responseCode = "400", description = "Erro ao criar endereço."),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    ResponseEntity<EnderecoDTO> criarEndereco(@PathVariable("idPessoa") Integer idPessoa,
                                              @RequestBody EnderecoCreateDTO enderecoCreateDTO);

    @Operation(summary = "Define endereço principal", description = "Define o endereço principal de uma pessoa.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Endereco editado com sucesso."),
                    @ApiResponse(responseCode = "400", description = "Erro ao editar endereço."),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    ResponseEntity<EnderecoDTO> definirPrincipal(@RequestParam Integer idPessoa,
                                                 @RequestParam Integer idEndereco) throws RegraDeNegocioException;
}
