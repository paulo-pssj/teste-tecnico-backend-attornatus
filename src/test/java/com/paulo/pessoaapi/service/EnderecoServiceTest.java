package com.paulo.pessoaapi.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.paulo.pessoaapi.dto.enderecoDTO.EnderecoCreateDTO;
import com.paulo.pessoaapi.dto.enderecoDTO.EnderecoDTO;
import com.paulo.pessoaapi.entity.EnderecoEntity;
import com.paulo.pessoaapi.exception.RegraDeNegocioException;
import com.paulo.pessoaapi.factory.EnderecoFactory;
import com.paulo.pessoaapi.factory.PessoaFactory;
import com.paulo.pessoaapi.repository.EnderecoRepository;
import com.paulo.pessoaapi.repository.PessoaRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EnderecoServiceTest {

    @InjectMocks
    private EnderecoService enderecoService;

    @Mock
    private EnderecoRepository enderecoRepository;

    @Mock
    private PessoaRepository pessoaRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void init() {
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        ReflectionTestUtils.setField(enderecoService, "objectMapper", objectMapper);
    }

    @Test
    public void deveTestarCriarComSucesso(){
        EnderecoEntity endereco = EnderecoFactory.criaEnderecoEntity();
        EnderecoCreateDTO enderecoCreateDTO = EnderecoFactory.criaEnderecoCreateDTO();

        when(pessoaRepository.findById(1)).thenReturn(Optional.of(PessoaFactory.criaPessoaEntity()));
        when(enderecoRepository.save(any())).thenReturn(endereco);

        EnderecoDTO enderecoDTO = enderecoService.criar(1, enderecoCreateDTO);

        assertEquals(endereco.getId(), enderecoDTO.getId());
        assertEquals(endereco.getNumero(), enderecoDTO.getNumero());
        assertEquals(endereco.getLogradouro(), enderecoDTO.getLogradouro());
        assertEquals(endereco.getCep(), enderecoDTO.getCep());
        assertEquals(endereco.getCidade(), enderecoDTO.getCidade());

    }

    @Test
    public void deveTestarListarEnderecosPorPessoaComSucesso(){
        EnderecoEntity endereco = EnderecoFactory.criaEnderecoEntity();
        List<EnderecoEntity> enderecoEntities = new ArrayList<>();
        enderecoEntities.add(endereco);

        when(enderecoRepository.findAllByPessoas_Id(any())).thenReturn(enderecoEntities);

        List<EnderecoDTO> enderecoDTOS = enderecoService.listarEnderecosPorPessoa(1);

        assertEquals(1, enderecoDTOS.size());

    }

    @Test
    public void deveTestarDefinirEnderecoPrincipalComSucesso() throws RegraDeNegocioException {
        EnderecoEntity enderecoTrue = EnderecoFactory.criaEnderecoEntity();
        enderecoTrue.setPrincipal(true);
        EnderecoEntity enderecoFalse = EnderecoFactory.criaEnderecoEntity();
        enderecoFalse.setId(2);
        List<EnderecoEntity> enderecoEntities = new ArrayList<>();
        enderecoEntities.add(enderecoTrue);
        enderecoEntities.add(enderecoFalse);

        when(enderecoRepository.findAllByPessoas_Id(any())).thenReturn(enderecoEntities);
        when(enderecoRepository.save(any())).thenReturn(enderecoFalse);
        when(enderecoRepository.findById(anyInt())).thenReturn(Optional.of(enderecoTrue));
        when(enderecoRepository.save(any())).thenReturn(enderecoTrue);

        EnderecoDTO enderecoDTO = enderecoService.definirEnderecoPrincipal(1, 2);

        assertEquals(1, enderecoDTO.getId());
        assertEquals(true, enderecoDTO.isPrincipal());

    }


}
