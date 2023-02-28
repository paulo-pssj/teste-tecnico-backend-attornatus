package com.paulo.pessoaapi.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.paulo.pessoaapi.dto.pessoaDTO.PessoaCreateDTO;
import com.paulo.pessoaapi.dto.pessoaDTO.PessoaEditDTO;
import com.paulo.pessoaapi.dto.pessoaDTO.PessoaRetornoDTO;
import com.paulo.pessoaapi.entity.PessoaEntity;
import com.paulo.pessoaapi.exception.RegraDeNegocioException;
import com.paulo.pessoaapi.factory.PessoaFactory;
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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PessoaServiceTest {

    @InjectMocks
    private PessoaService pessoaService;

    @Mock
    private PessoaRepository pessoaRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void init() {
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        ReflectionTestUtils.setField(pessoaService, "objectMapper", objectMapper);
    }

    @Test
    public void deveTestarCriarPessoaComSucesso(){
        PessoaEntity pessoaEntity = PessoaFactory.criaPessoaEntity();
        PessoaCreateDTO pessoaCreateDTO = PessoaFactory.criaPessoaCreateDTO();

        when(pessoaRepository.save(any())).thenReturn(pessoaEntity);

        PessoaRetornoDTO pessoaRetornoDTO = pessoaService.criar(pessoaCreateDTO);

        assertEquals(pessoaEntity.getId(), pessoaRetornoDTO.getId());
        assertEquals(pessoaEntity.getNome(), pessoaRetornoDTO.getNome());
        assertEquals(pessoaEntity.getDataNasc(), pessoaRetornoDTO.getDataNasc());
        assertNotNull(pessoaRetornoDTO.getEnderecos());

    }

    @Test
    public void deveTestarEditarPessoaComSucesso(){
        PessoaEntity pessoaEntity = PessoaFactory.criaPessoaEntity();
        PessoaEditDTO pessoaEditDTO = PessoaFactory.criaPessoaEditDTO();

        when(pessoaRepository.findById(anyInt())).thenReturn(Optional.of(pessoaEntity));
        when(pessoaRepository.save(any())).thenReturn(pessoaEntity);

        PessoaRetornoDTO pessoaRetornoDTO = pessoaService.editar(1, pessoaEditDTO);

        assertEquals(pessoaEntity.getId(), pessoaRetornoDTO.getId());
        assertEquals(pessoaEntity.getNome(), pessoaRetornoDTO.getNome());
        assertEquals(pessoaEntity.getDataNasc(), pessoaRetornoDTO.getDataNasc());
        assertNotNull(pessoaRetornoDTO.getEnderecos());
    }

    @Test
    public void deveTestarListarPessoasComSucesso(){
        PessoaEntity pessoaEntity = PessoaFactory.criaPessoaEntity();
        List<PessoaEntity> pessoaEntityList = new ArrayList<>();
        pessoaEntityList.add(pessoaEntity);

        when(pessoaRepository.findAll()).thenReturn(pessoaEntityList);

        List<PessoaRetornoDTO> pessoaRetornoDTOList = pessoaService.listarPessoas();

        assertEquals(1, pessoaRetornoDTOList.size());
    }

    @Test
    public void deveTestarConsultarPessoa() throws RegraDeNegocioException {
        PessoaEntity pessoaEntity = PessoaFactory.criaPessoaEntity();
        Integer idPessoa = 1;

        when(pessoaRepository.findById(anyInt())).thenReturn(Optional.of(pessoaEntity));

        PessoaRetornoDTO pessoaRetornoDTO = pessoaService.consultarPessoa(idPessoa);

        assertEquals(pessoaEntity.getId(), pessoaRetornoDTO.getId());
        assertEquals(pessoaEntity.getNome(), pessoaRetornoDTO.getNome());
        assertEquals(pessoaEntity.getDataNasc(), pessoaRetornoDTO.getDataNasc());
        assertNotNull(pessoaRetornoDTO.getEnderecos());
    }
}
