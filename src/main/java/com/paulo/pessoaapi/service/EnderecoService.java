package com.paulo.pessoaapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paulo.pessoaapi.dto.enderecoDTO.EnderecoCreateDTO;
import com.paulo.pessoaapi.dto.enderecoDTO.EnderecoDTO;
import com.paulo.pessoaapi.entity.EnderecoEntity;
import com.paulo.pessoaapi.entity.PessoaEntity;
import com.paulo.pessoaapi.exception.RegraDeNegocioException;
import com.paulo.pessoaapi.repository.EnderecoRepository;
import com.paulo.pessoaapi.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnderecoService {
    private final EnderecoRepository enderecoRepository;
    private final ObjectMapper objectMapper;
    private final PessoaRepository pessoaRepository;

    public EnderecoDTO criar(Integer idPessoa, EnderecoCreateDTO enderecoCreateDTO) {
        PessoaEntity pessoaEntity = pessoaRepository.findById(idPessoa).get();
        EnderecoEntity enderecoEntity = new EnderecoEntity();

        enderecoEntity.setLogradouro(enderecoCreateDTO.getLogradouro());
        enderecoEntity.setCep(enderecoCreateDTO.getCep());
        enderecoEntity.setCidade(enderecoCreateDTO.getCidade());
        enderecoEntity.setNumero(enderecoCreateDTO.getNumero());
        enderecoEntity.setPrincipal(false);
        enderecoEntity.getPessoas().add(pessoaEntity);

        EnderecoEntity enderecoSalvo = enderecoRepository.save(enderecoEntity);
        return objectMapper.convertValue(enderecoSalvo, EnderecoDTO.class);

    }

    public List<EnderecoDTO> listarEnderecosPorPessoa(Integer idPessoa){
        return enderecoRepository.findAllByPessoas_Id(idPessoa).stream()
                .map(enderecoEntity -> objectMapper.convertValue(enderecoEntity, EnderecoDTO.class)).toList();
    }

    public EnderecoDTO definirEnderecoPrincipal(Integer idPessoa, Integer idEndereco) throws RegraDeNegocioException {
        List<EnderecoEntity> enderecos = enderecoRepository.findAllByPessoas_Id(idPessoa);
        for(EnderecoEntity endereco: enderecos){
            if(endereco.isPrincipal()){
                endereco.setPrincipal(false);
                enderecoRepository.save(endereco);
            }
        }
        EnderecoEntity enderecoEntity = buscarEnderecoPorId(idEndereco);
        enderecoEntity.setPrincipal(true);
        return objectMapper.convertValue(enderecoRepository.save(enderecoEntity), EnderecoDTO.class);

    }

    private EnderecoEntity buscarEnderecoPorId(Integer id) throws RegraDeNegocioException{
        return enderecoRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("Endereço não encontrado."));
    }

}
