package br.com.petz.cliente.domain.service;

import br.com.petz.cliente.rest.model.Client;
import br.com.petz.cliente.domain.adapter.ClienteRepository;
import br.com.petz.cliente.domain.port.ClientPort;
import br.com.petz.cliente.integration.entity.ClientEntity;
import br.com.petz.cliente.rest.model.ClientEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ClienteService implements ClientPort {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Client findClientByID(UUID idClient) {
            return ClienteMapper.entityToModel(clienteRepository.findByIdCliente(idClient));
    }

    @Override
    public Client findClientByCpf(String cpf) {
        return ClienteMapper.entityToModel(clienteRepository.findByCpf(cpf));
    }

    @Override
    public ClientEmpty insertClient(Client client) {
        if(clienteRepository.existsByCpf(client.getCpf())){
            return new ClientEmpty();
        }else {
            return ClienteMapper.entityToModel(clienteRepository.save(ClienteMapper.modelToEntity(client)));
        }
    }

    @Override
    public ClientEmpty updateClient(UUID idCliente, Client client) {
        try {
            if (clienteRepository.existsByIdCliente(idCliente)) {
                return ClienteMapper.entityToModel(clienteRepository.save(ClienteMapper.modelToEntity(client)));
            } else {
                return new ClientEmpty();
            }
        } catch (NullPointerException ex){
            return new ClientEmpty();
        }
    }

    @Override
    public boolean deleteClientByID(UUID idCliente) {

        if(clienteRepository.existsByIdCliente(idCliente)){
            clienteRepository.delete(clienteRepository.findByIdCliente(idCliente));
            return true;
        }else {
            return false;
        }
    }
}
