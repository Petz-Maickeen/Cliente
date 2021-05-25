package br.com.petz.cliente.domain;

import br.com.petz.cliente.domain.adapter.ClienteRepository;
import br.com.petz.cliente.domain.service.ClienteMapper;
import br.com.petz.cliente.domain.service.ClienteService;
import br.com.petz.cliente.rest.model.Client;
import br.com.petz.cliente.rest.model.ClientEmpty;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientServiceTest {

    @Autowired
    private ClienteService clienteService;

    @MockBean
    private ClienteRepository clienteRepository;

    @Test
    public void shouldFindClientByID(){

        UUID idCliente = UUID.randomUUID();
        Client cliente = Client.builder()
                .idCliente(idCliente)
                .idade(21)
                .cpf("12345678901")
                .genero("Masculino")
                .nome("Petz")
                .build();

        when(clienteRepository.findByIdCliente(idCliente)).thenReturn(ClienteMapper.modelToEntity(cliente));

        assertEquals(clienteService.findClientByID(idCliente), cliente);
    }

    @Test
    public void shouldNotFindClientByID(){

        UUID idCliente = UUID.randomUUID();

        when(clienteRepository.findByIdCliente(idCliente)).thenReturn(null);

        assertThrows(NullPointerException.class,() -> clienteService.findClientByID(idCliente));
    }

    @Test
    public void shouldInserClient(){
        UUID idCliente = UUID.randomUUID();
        Client clienteE = Client.builder()
                .idade(21)
                .cpf("12345678901")
                .genero("Masculino")
                .nome("Petz")
                .build();

        Client clienteS = Client.builder()
                .idCliente(idCliente)
                .idade(21)
                .cpf("12345678901")
                .genero("Masculino")
                .nome("Petz")
                .build();

        when(clienteRepository.existsByCpf(clienteE.getCpf())).thenReturn(false);
        when(clienteRepository.save(ClienteMapper.modelToEntity(clienteE))).thenReturn(ClienteMapper.modelToEntity(clienteS));

        assertEquals(clienteService.insertClient(clienteE),clienteS);
    }

    @Test
    public void shouldNotInserExistingClient(){
        Client clienteE = Client.builder()
                .idade(21)
                .cpf("12345678901")
                .genero("Masculino")
                .nome("Petz")
                .build();

        when(clienteRepository.existsByCpf(clienteE.getCpf())).thenReturn(true);
        assertTrue(clienteService.insertClient(clienteE) instanceof ClientEmpty);
    }

    @Test
    public void shouldUpdateClient(){
        UUID idCliente = UUID.randomUUID();
        Client clienteE = Client.builder()
                .idade(21)
                .cpf("12345678901")
                .genero("Masculino")
                .nome("Petz")
                .build();

        when(clienteRepository.existsByIdCliente(idCliente)).thenReturn(true);
        when(clienteRepository.save(ClienteMapper.modelToEntity(clienteE))).thenReturn(ClienteMapper.modelToEntity(clienteE));

        assertEquals(clienteService.updateClient(idCliente,clienteE),clienteE);
    }

    @Test
    public void shouldNotUpdateInvalidClient(){
        UUID idCliente = UUID.randomUUID();
        Client clienteE = Client.builder()
                .idade(21)
                .cpf("12345678901")
                .genero("Masculino")
                .nome("Petz")
                .build();

        when(clienteRepository.existsByIdCliente(idCliente)).thenReturn(false);
        when(clienteRepository.save(ClienteMapper.modelToEntity(clienteE))).thenReturn(ClienteMapper.modelToEntity(clienteE));

        assertTrue(clienteService.insertClient(clienteE) instanceof ClientEmpty);
    }

    @Test
    public void shouldDeleteClient(){
        UUID idCliente = UUID.randomUUID();

        when(clienteRepository.existsByIdCliente(idCliente)).thenReturn(true);

        assertTrue(clienteService.deleteClientByID(idCliente));
    }

    @Test
    public void shouldNotDeleteInvalidClient(){
        UUID idCliente = UUID.randomUUID();

        when(clienteRepository.existsByIdCliente(idCliente)).thenReturn(false);

        assertFalse(clienteService.deleteClientByID(idCliente));
    }
}
