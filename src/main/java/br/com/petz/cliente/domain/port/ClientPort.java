package br.com.petz.cliente.domain.port;

import br.com.petz.cliente.rest.model.Client;
import br.com.petz.cliente.rest.model.ClientEmpty;

import java.util.UUID;

public interface ClientPort {
    Client findClientByID(UUID idCliente);
    Client findClientByCpf(String cpf);
    ClientEmpty insertClient(Client cliente);
    ClientEmpty updateClient(UUID idcliente, Client cliente);
    boolean deleteClientByID(UUID idCliente);
}
