package br.com.petz.cliente.domain.adapter;

import br.com.petz.cliente.integration.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClienteRepository extends JpaRepository<ClientEntity, String> {
    ClientEntity findByIdCliente(UUID idCliente);
    ClientEntity findByCpf(String cpf);
    boolean existsByCpf(String cpf);
    boolean existsByIdCliente(UUID idCliente);
}
