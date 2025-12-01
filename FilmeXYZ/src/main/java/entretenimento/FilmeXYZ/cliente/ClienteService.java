package entretenimento.FilmeXYZ.cliente;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    // CREATE
    public ClienteEntitie cadastrarCliente(ClienteEntitie novoCliente) {
        return repository.save(novoCliente);
    }

    // READ - listar todos
    public List<ClienteEntitie> listarCliente() {
        return repository.findAll();
    }

    // READ - buscar por ID
    public Optional<ClienteEntitie> buscarClienteId(Integer idCliente) {
        return repository.findById(idCliente);
    }

    // READ - buscar por CPF
    public List<ClienteEntitie> buscarClienteCpf(String cpf) {
        return repository.findByCpf(cpf);
    }

    // UPDATE
    public ClienteEntitie atualizarCliente(Integer idCliente, ClienteEntitie atualizado) {

        ClienteEntitie clienteExistente = repository.findById(idCliente)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com id " + idCliente));

        clienteExistente.setNome(atualizado.getNome());
        clienteExistente.setTelefone(atualizado.getTelefone());
        

        return repository.save(clienteExistente);
    }

    // DELETE
    public void deletarCliente(Integer idCliente) {
        repository.deleteById(idCliente);
    }
}
