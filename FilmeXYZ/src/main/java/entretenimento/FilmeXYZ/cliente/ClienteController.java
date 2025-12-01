package entretenimento.FilmeXYZ.cliente;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cliente")
public class ClienteController {

	@Autowired
	private ClienteService service;

	@PostMapping
	public ClienteEntitie cadastrar(@RequestBody ClienteEntitie novo) {
		return service.cadastrarCliente(novo);
	}

	@GetMapping
	public List<ClienteEntitie> listar() {
		return service.listarCliente();
	}

	@PutMapping("/{idCliente}")
	public ClienteEntitie atualizar(@PathVariable Integer idCliente, @RequestBody ClienteEntitie atualizado) {
		return service.atualizarCliente(idCliente, atualizado);
	}

	@GetMapping("/id/{idCliente}")
	public Optional<ClienteEntitie> buscarPorId(@PathVariable Integer idCliente) {
		return service.buscarClienteId(idCliente);
	}

	@GetMapping("/cpf/{cpf}")
	public List<ClienteEntitie> buscarCpfCliente(@PathVariable String cpf) {
		return service.buscarClienteCpf(cpf);
	}

	@DeleteMapping("/{idCliente}")
	public void deletarCliente(@PathVariable Integer idCliente) {
		service.deletarCliente(idCliente);
	}
}
