package com.paulo.pessoaapi.factory;

import com.paulo.pessoaapi.dto.enderecoDTO.EnderecoCreateDTO;
import com.paulo.pessoaapi.dto.enderecoDTO.EnderecoDTO;
import com.paulo.pessoaapi.entity.EnderecoEntity;

public class EnderecoFactory {
    public static EnderecoEntity criaEnderecoEntity(){
        EnderecoEntity enderecoEntity = new EnderecoEntity();
        enderecoEntity.setId(1);
        enderecoEntity.setLogradouro("rua teste");
        enderecoEntity.setCep("00000-000");
        enderecoEntity.setCidade("Cotia");
        enderecoEntity.setNumero("120");
        return enderecoEntity;
    }

    public static EnderecoCreateDTO criaEnderecoCreateDTO(){
        EnderecoCreateDTO enderecoCreateDTO = new EnderecoCreateDTO();
        enderecoCreateDTO.setLogradouro("rua teste");
        enderecoCreateDTO.setCep("00000-000");
        enderecoCreateDTO.setCidade("Cotia");
        enderecoCreateDTO.setNumero("120");
        return enderecoCreateDTO;
    }

    public static EnderecoDTO criaEnderecoDTO(){
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setId(1);
        enderecoDTO.setLogradouro("rua teste");
        enderecoDTO.setCep("00000-000");
        enderecoDTO.setCidade("Cotia");
        enderecoDTO.setNumero("120");
        enderecoDTO.setPrincipal(false);
        return enderecoDTO;
    }
}
