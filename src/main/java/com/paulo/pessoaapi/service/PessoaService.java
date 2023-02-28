package com.paulo.pessoaapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paulo.pessoaapi.dto.enderecoDTO.EnderecoDTO;
import com.paulo.pessoaapi.dto.pessoaDTO.PessoaCreateDTO;
import com.paulo.pessoaapi.dto.pessoaDTO.PessoaEditDTO;
import com.paulo.pessoaapi.dto.pessoaDTO.PessoaRetornoDTO;
import com.paulo.pessoaapi.entity.PessoaEntity;
import com.paulo.pessoaapi.exception.RegraDeNegocioException;
import com.paulo.pessoaapi.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PessoaService {

    private final PessoaRepository pessoaRepository;
    private final ObjectMapper objectMapper;

    public PessoaRetornoDTO criar(PessoaCreateDTO pessoaCreateDTO) {
        PessoaEntity pessoaEntity = new PessoaEntity();
        pessoaEntity.setNome(pessoaCreateDTO.getNome());
        pessoaEntity.setDataNasc(pessoaCreateDTO.getDataNasc());

        PessoaRetornoDTO pessoaRetornoDTO = converterPessoaRetornoDTO(pessoaRepository.save(pessoaEntity));
        return pessoaRetornoDTO;
    }

    public PessoaRetornoDTO editar(Integer idPessoa, PessoaEditDTO pessoaEditDTO) {
        PessoaEntity pessoaEntity = pessoaRepository.findById(idPessoa).get();
        if (!(pessoaEditDTO.getNome() == null)) {
            pessoaEntity.setNome(pessoaEditDTO.getNome());
        }
        if (!(pessoaEditDTO.getDataNasc() == null)) {
            pessoaEntity.setDataNasc(pessoaEditDTO.getDataNasc());
        }
        PessoaEntity pessoaSave = pessoaRepository.save(pessoaEntity);
        return converterPessoaRetornoDTO(pessoaSave);
    }

    public PessoaRetornoDTO consultarPessoa(Integer idPessoa) throws RegraDeNegocioException {
        PessoaEntity pessoaEntity = pessoaRepository.findById(idPessoa)
                .orElseThrow(() -> new RegraDeNegocioException("Pessoa não encontrada."));
        return converterPessoaRetornoDTO(pessoaEntity);
    }

    public List<PessoaRetornoDTO> listarPessoas() {
        return pessoaRepository.findAll().stream().map(this::converterPessoaRetornoDTO).toList();
    }

    private PessoaRetornoDTO converterPessoaRetornoDTO(PessoaEntity pessoaEntity) {
        PessoaRetornoDTO pessoaRetornoDTO = new PessoaRetornoDTO();
        pessoaRetornoDTO.setId(pessoaEntity.getId());
        pessoaRetornoDTO.setNome(pessoaEntity.getNome());
        pessoaRetornoDTO.setDataNasc(pessoaEntity.getDataNasc());
        if (!(pessoaEntity.getEnderecos() == null)) {
            List<EnderecoDTO> enderecos = pessoaEntity.getEnderecos().stream()
                    .map(enderecoEntity -> objectMapper.convertValue(enderecoEntity, EnderecoDTO.class)).toList();
            pessoaRetornoDTO.setEnderecos(enderecos);
        }
        return pessoaRetornoDTO;
    }
}
