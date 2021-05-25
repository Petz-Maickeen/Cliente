package br.com.petz.cliente.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Client extends ClientEmpty {
    @JsonProperty(value = "idCliente")
    private UUID idCliente;

    @JsonProperty(value = "nome")
    private String nome;

    @JsonProperty(value = "idade")
    private int idade;

    @JsonProperty(value = "genero")
    private String genero;

    @JsonProperty(value = "cpf")
    private String cpf;
}
