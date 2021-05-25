package br.com.petz.cliente.rest;

import br.com.petz.cliente.rest.model.Client;
import br.com.petz.cliente.rest.model.ClientEmpty;
import br.com.petz.cliente.rest.model.DataResponse;
import br.com.petz.cliente.rest.util.Constantes;
import br.com.petz.cliente.domain.port.ClientPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClientPort clientePort;

    @GetMapping("/{id_cliente}")
    private ResponseEntity<?> findClient(@Valid @PathVariable(value = "id_cliente")UUID idClient){
        try {
            return ResponseEntity.ok().body(DataResponse.builder().data(clientePort.findClientByID(idClient)).build());
        } catch (NullPointerException ex){
            return ResponseEntity.status(404).body(DataResponse.builder().data(Constantes.CLIENTE_NAO_ENCONTRADO).build());
        }
    }

    @GetMapping
    private ResponseEntity<?> findClientId(@Valid @RequestParam(value = "cpf")String cpf){
        try {
            return ResponseEntity.ok().body(DataResponse.builder().data(clientePort.findClientByCpf(cpf)).build());
        } catch (NullPointerException ex){
            return ResponseEntity.status(404).body(DataResponse.builder().data(Constantes.CLIENTE_NAO_ENCONTRADO).build());
        }
    }

    @PatchMapping("/{id_cliente}")
    private ResponseEntity<?> updateClient(@Valid @RequestBody Client client,
                                           @Valid @PathVariable(value = "id_cliente") UUID idClient){
        client.setIdCliente(idClient);
        ClientEmpty clientEmpty = clientePort.updateClient(idClient, client);
        return clientEmpty instanceof Client ?
                ResponseEntity.ok().body(DataResponse.builder().data(client).build()):
                ResponseEntity.status(404).body(DataResponse.builder().data(Constantes.CLIENTE_NAO_ENCONTRADO).build());
    }

    @PostMapping
    private ResponseEntity<?> insertClient(@Valid @RequestBody Client client){
        ClientEmpty clientEmpty = clientePort.insertClient(client);
        return clientEmpty instanceof Client ?
                ResponseEntity.status(201).body(DataResponse.builder().data(clientEmpty).build()):
                ResponseEntity.status(400).body(DataResponse.builder().data(Constantes.CLIENTE_JA_CADASTRADO).build());
    }

    @DeleteMapping("/{id_cliente}")
    private ResponseEntity<?> removeClient(@Valid @PathVariable(value = "id_cliente")UUID idClient){
        return clientePort.deleteClientByID(idClient)?
                ResponseEntity.ok().body(DataResponse.builder().data(Constantes.CLIENTE_EXCLUIDO_SUCESSO).build()):
                ResponseEntity.status(404).body(DataResponse.builder().data(Constantes.CLIENTE_NAO_ENCONTRADO).build());
    }
}
