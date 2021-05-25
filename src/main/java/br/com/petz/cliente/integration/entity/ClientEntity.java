package br.com.petz.cliente.integration.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "cliente")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientEntity {

    @Id
    @NotNull
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id_cliente",columnDefinition = "BINARY(16)")
    private UUID idCliente;

    @NotNull
    @Column(name = "nome")
    private String nome;

    @NotNull
    @Column(name = "idade")
    private int idade;

    @NotNull
    @Column(name = "genero")
    private String genero;

    @NotNull
    @Column(name = "cpf")
    private String cpf;

}
