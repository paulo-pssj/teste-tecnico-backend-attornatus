package com.paulo.pessoaapi.controller;

import com.paulo.pessoaapi.controller.documentation.PessoaDocumentationInterface;
import com.paulo.pessoaapi.dto.pessoaDTO.PessoaCreateDTO;
import com.paulo.pessoaapi.dto.pessoaDTO.PessoaEditDTO;
import com.paulo.pessoaapi.dto.pessoaDTO.PessoaRetornoDTO;
import com.paulo.pessoaapi.exception.RegraDeNegocioException;
import com.paulo.pessoaapi.service.PessoaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/pessoa")
public class PessoaController implements PessoaDocumentationInterface {
    private final PessoaService pessoaService;

    @GetMapping
    public ResponseEntity<List<PessoaRetornoDTO>> listarPessoas() {
        List<PessoaRetornoDTO> listaPessoas = pessoaService.listarPessoas();
        return new ResponseEntity<>(listaPessoas, HttpStatus.OK);
    }

    @GetMapping("/{idPessoa}")
    public ResponseEntity<PessoaRetornoDTO> consultarPessoaPorId(@PathVariable("idPessoa") Integer idPessoa) throws RegraDeNegocioException {
        PessoaRetornoDTO pessoaRetornoDTO = pessoaService.consultarPessoa(idPessoa);
        return new ResponseEntity<>(pessoaRetornoDTO, HttpStatus.OK);
    }

    @PostMapping("/criar-pessoa/")
    public ResponseEntity<PessoaRetornoDTO> criar(@Valid @RequestBody PessoaCreateDTO pessoaCreateDTO) {
        PessoaRetornoDTO pessoaRetornoDTO = pessoaService.criar(pessoaCreateDTO);
        return new ResponseEntity<>(pessoaRetornoDTO, HttpStatus.OK);
    }

    @PutMapping("/{idPessoa}/editar")
    public ResponseEntity<PessoaRetornoDTO> editar(@PathVariable("idPessoa") Integer idPessoa,
                                                   @RequestBody PessoaEditDTO pessoaEditDTO) {
        PessoaRetornoDTO pessoaRetornoDTO = pessoaService.editar(idPessoa, pessoaEditDTO);
        return new ResponseEntity<>(pessoaRetornoDTO, HttpStatus.OK);
    }
}
