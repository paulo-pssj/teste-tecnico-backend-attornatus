package com.paulo.pessoaapi.factory;

import com.paulo.pessoaapi.dto.pessoaDTO.PessoaCreateDTO;
import com.paulo.pessoaapi.dto.pessoaDTO.PessoaEditDTO;
import com.paulo.pessoaapi.dto.pessoaDTO.PessoaRetornoDTO;
import com.paulo.pessoaapi.entity.EnderecoEntity;
import com.paulo.pessoaapi.entity.PessoaEntity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class PessoaFactory {
    public static PessoaEntity criaPessoaEntity(){
        Set<EnderecoEntity> enderecoEntities = new HashSet<>();
        enderecoEntities.add(EnderecoFactory.criaEnderecoEntity());
        PessoaEntity pessoaEntity = new PessoaEntity();
        pessoaEntity.setId(1);
        pessoaEntity.setNome("Paulo Teste");
        pessoaEntity.setDataNasc(LocalDate.of(1999, 03, 9));
        pessoaEntity.setEnderecos(enderecoEntities);
        return pessoaEntity;
    }

    public static PessoaCreateDTO criaPessoaCreateDTO(){
        PessoaCreateDTO pessoaCreateDTO = new PessoaCreateDTO();
        pessoaCreateDTO.setNome("Paulo Teste");
        pessoaCreateDTO.setDataNasc(LocalDate.of(1999, 03, 9));
        return pessoaCreateDTO;
    }

    public static PessoaEditDTO criaPessoaEditDTO(){
        PessoaEditDTO pessoaEditDTO = new PessoaEditDTO();
        pessoaEditDTO.setNome("Paulo Editado");
        pessoaEditDTO.setDataNasc(LocalDate.of(2000,05,10));
        return pessoaEditDTO;
    }

    public static PessoaRetornoDTO criarPessoaRetornoDTO(){
        PessoaRetornoDTO pessoaRetornoDTO = new PessoaRetornoDTO();
        pessoaRetornoDTO.setId(1);
        pessoaRetornoDTO.setNome("Paulo Teste");
        pessoaRetornoDTO.setDataNasc(LocalDate.of(1999, 03, 9));
        pessoaRetornoDTO.setEnderecos(null);
        return pessoaRetornoDTO;
    }
}
