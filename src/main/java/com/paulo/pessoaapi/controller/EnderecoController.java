package com.paulo.pessoaapi.controller;

import com.paulo.pessoaapi.controller.documentation.EnderecoDocumentationInterface;
import com.paulo.pessoaapi.dto.enderecoDTO.EnderecoCreateDTO;
import com.paulo.pessoaapi.dto.enderecoDTO.EnderecoDTO;
import com.paulo.pessoaapi.exception.RegraDeNegocioException;
import com.paulo.pessoaapi.service.EnderecoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/endereco")
public class EnderecoController implements EnderecoDocumentationInterface {
    private final EnderecoService enderecoService;

    @GetMapping("/{idPessoa}")
    public ResponseEntity<List<EnderecoDTO>> listarEnderecoPorPessoa(@PathVariable("idPessoa") Integer idPessoa) {
        List<EnderecoDTO> enderecos = enderecoService.listarEnderecosPorPessoa(idPessoa);
        return new ResponseEntity<>(enderecos, HttpStatus.OK);
    }

    @PostMapping("/criar-endereco/{idPessoa}")
    public ResponseEntity<EnderecoDTO> criarEndereco(@PathVariable("idPessoa") Integer idPessoa,
                                                     @RequestBody EnderecoCreateDTO enderecoCreateDTO) {
        EnderecoDTO enderecoDTO = enderecoService.criar(idPessoa, enderecoCreateDTO);
        return new ResponseEntity<>(enderecoDTO, HttpStatus.OK);
    }

    @PutMapping("/definir-principal")
    public ResponseEntity<EnderecoDTO> definirPrincipal(@RequestParam Integer idPessoa,
                                                        @RequestParam Integer idEndereco) throws RegraDeNegocioException {
        EnderecoDTO enderecoDTO = enderecoService.definirEnderecoPrincipal(idPessoa, idEndereco);
        return new ResponseEntity<>(enderecoDTO, HttpStatus.OK);
    }
}
