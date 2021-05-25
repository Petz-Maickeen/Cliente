package br.com.petz.cliente.domain.service;

import br.com.petz.cliente.rest.model.Client;
import br.com.petz.cliente.integration.entity.ClientEntity;

public class ClienteMapper {
    public static Client entityToModel(ClientEntity clienteEntity){
        return Client.builder()
                .idCliente(clienteEntity.getIdCliente())
                .nome(clienteEntity.getNome())
                .idade(clienteEntity.getIdade())
                .cpf(clienteEntity.getCpf())
                .genero(clienteEntity.getGenero())
                .build();
    }
    public static ClientEntity modelToEntity(Client cliente){
        return ClientEntity.builder()
                .idCliente(cliente.getIdCliente())
                .nome(cliente.getNome())
                .idade(cliente.getIdade())
                .cpf(cliente.getCpf())
                .genero(cliente.getGenero())
                .build();
    }
}
