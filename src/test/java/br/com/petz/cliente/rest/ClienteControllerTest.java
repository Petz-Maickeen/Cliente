package br.com.petz.cliente.rest;

import br.com.petz.cliente.JsonUtil;
import br.com.petz.cliente.domain.port.ClientPort;
import br.com.petz.cliente.rest.model.Client;
import br.com.petz.cliente.rest.model.ClientEmpty;
import br.com.petz.cliente.rest.model.DataResponse;
import br.com.petz.cliente.rest.util.Constantes;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(ClienteController.class)
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientPort clientPort;

    List<UUID> clientes = new ArrayList<>();

    @Test
    public void shouldFindClientByID() throws Exception {
        UUID idCliente = UUID.randomUUID();
        Client cliente = Client.builder()
                .idCliente(idCliente)
                .idade(21)
                .cpf("12345678901")
                .genero("Masculino")
                .nome("Petz")
                .build();

        when(clientPort.findClientByID(idCliente)).thenReturn(cliente);
        mockMvc
                .perform(MockMvcRequestBuilders.get("/cliente/" + idCliente))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(JsonUtil.toJson(DataResponse.builder().data(cliente).build())));
    }

    @Test
    public void shouldNotFindClientByID() throws Exception {
        UUID idCliente = UUID.randomUUID();

        when(clientPort.findClientByID(idCliente)).thenThrow(new NullPointerException());
        mockMvc
                .perform(MockMvcRequestBuilders.get("/cliente/" + idCliente))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().json(JsonUtil.toJson(DataResponse.builder().data(Constantes.CLIENTE_NAO_ENCONTRADO).build())));
    }

    @Test
    public void shouldInsertClient() throws Exception {
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

        when(clientPort.insertClient(clienteE)).thenReturn(clienteS);
        mockMvc
                .perform(MockMvcRequestBuilders.post("/cliente/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(clienteE)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json(JsonUtil.toJson(DataResponse.builder().data(clienteS).build())));
    }

    @Test
    public void shouldNotInsertClient() throws Exception {
        UUID idCliente = UUID.randomUUID();
        Client clienteE = Client.builder()
                .idade(21)
                .cpf("12345678901")
                .genero("Masculino")
                .nome("Petz")
                .build();

        when(clientPort.insertClient(clienteE)).thenReturn(new ClientEmpty());
        mockMvc
                .perform(MockMvcRequestBuilders.post("/cliente/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(clienteE)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().json(JsonUtil.toJson(DataResponse.builder().data(Constantes.CLIENTE_JA_CADASTRADO).build())));
    }

    @Test
    public void shouldUpdateClient() throws Exception {
        UUID idCliente = UUID.randomUUID();
        Client clienteE = Client.builder()
                .idCliente(idCliente)
                .idade(21)
                .cpf("12345678901")
                .genero("Masculino")
                .nome("Petz")
                .build();

        when(clientPort.updateClient(idCliente, clienteE)).thenReturn(clienteE);
        mockMvc
                .perform(MockMvcRequestBuilders.patch("/cliente/"+idCliente)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(clienteE)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(JsonUtil.toJson(DataResponse.builder().data(clienteE).build())));
    }

    @Test
    public void shouldNotUpdateClient() throws Exception {
        UUID idCliente = UUID.randomUUID();
        Client clienteE = Client.builder()
                .idCliente(idCliente)
                .idade(21)
                .cpf("12345678901")
                .genero("Masculino")
                .nome("Petz")
                .build();

        when(clientPort.updateClient(idCliente, clienteE)).thenReturn(new ClientEmpty());
        mockMvc
                .perform(MockMvcRequestBuilders.patch("/cliente/"+idCliente)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(clienteE)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().json(JsonUtil.toJson(DataResponse.builder().data(Constantes.CLIENTE_NAO_ENCONTRADO).build())));
    }

    @Test
    public void shouldDeleteClient() throws Exception {
        UUID idCliente = UUID.randomUUID();
        when(clientPort.deleteClientByID(idCliente)).thenReturn(true);
        mockMvc
                .perform(MockMvcRequestBuilders.delete("/cliente/" + idCliente))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(JsonUtil.toJson(DataResponse.builder().data(Constantes.CLIENTE_EXCLUIDO_SUCESSO).build())));
    }

    @Test
    public void shouldNotDeleteClient() throws Exception {
        UUID idCliente = UUID.randomUUID();
        when(clientPort.deleteClientByID(idCliente)).thenReturn(false);
        mockMvc
                .perform(MockMvcRequestBuilders.delete("/cliente/" + idCliente))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().json(JsonUtil.toJson(DataResponse.builder().data(Constantes.CLIENTE_NAO_ENCONTRADO).build())));
    }
}