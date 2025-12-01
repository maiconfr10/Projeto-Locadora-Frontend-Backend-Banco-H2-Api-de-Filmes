package entretenimento.FilmeXYZ.emprestimo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entretenimento.FilmeXYZ.StatusEmprestimo;
import entretenimento.FilmeXYZ.EmprestimoResponseDTO.EmprestimoResponseDTO;
import entretenimento.FilmeXYZ.cliente.ClienteEntitie;
import entretenimento.FilmeXYZ.cliente.ClienteRepository;
import entretenimento.FilmeXYZ.filme.FilmeEntitie;
import entretenimento.FilmeXYZ.filme.FilmeRepository;

@Service
public class EmprestimoService {
	
	@Autowired
	private FilmeRepository filmeRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EmprestimoRepository repository;
	public EmprestimoEntitie cadastrarEmprestimo(EmprestimoEntitie emprestimo) {

	    // 🔹 Buscar o filme no banco
	    FilmeEntitie filme = filmeRepository.findById(emprestimo.getFilme().getIdFilme())
	            .orElseThrow(() -> new RuntimeException("Filme não encontrado!"));

	    // 🔹 Buscar o cliente no banco
	    ClienteEntitie cliente = clienteRepository.findById(emprestimo.getCliente().getIdCliente())
	            .orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));

	    // 🔹 Setar os objetos completos
	    emprestimo.setFilme(filme);
	    emprestimo.setCliente(cliente);

	    // 🔹 Verifica se o filme já está emprestado (status ATIVO)
	    boolean emprestado = repository.existsByFilmeIdFilmeAndStatus(
	            emprestimo.getFilme().getIdFilme(),
	            StatusEmprestimo.ATIVO
	    );

	    if (emprestado) {
	        throw new RuntimeException("❌ O filme já está emprestado e não pode ser alugado novamente.");
	    }

	    // 🔹 Salvar o empréstimo
	    return repository.save(emprestimo);
	}


	public List<EmprestimoEntitie> listarEmprestimo() {
		return repository.findAll();
	}

	public Optional<EmprestimoEntitie> buscarId(Integer idEmprestimo) {
		return repository.findById(idEmprestimo);

	}

	public List<EmprestimoResponseDTO> listarEmprestimoPorCliente(Integer idCliente) {
	    return repository.findByClienteIdCliente(idCliente)
	                     .stream()
	                     .map(EmprestimoResponseDTO::new)
	                     .toList();
	}

	public List<EmprestimoResponseDTO> listarEmprestimoPorFilme(Integer idFilme) {
	    return repository.findByFilmeIdFilme(idFilme)
	                     .stream()
	                     .map(EmprestimoResponseDTO::new)
	                     .toList();
	}

	public EmprestimoEntitie atualizarEmprestimo(Integer idEmprestimo, EmprestimoEntitie atualizado) {
		EmprestimoEntitie emprestimoExistente = repository.findById(idEmprestimo)
				.orElseThrow(() -> new RuntimeException("Emprestimo não encontrado com id" + idEmprestimo));

		emprestimoExistente.setDataDevolucao(atualizado.getDataDevolucao());
		emprestimoExistente.setDataEmprestimo(atualizado.getDataEmprestimo());
		emprestimoExistente.setValor(atualizado.getValor());
		emprestimoExistente.setFilme(atualizado.getFilme());
		emprestimoExistente.setStatus(atualizado.getStatus());
		emprestimoExistente.setCliente(atualizado.getCliente());

		return repository.save(emprestimoExistente);

	}

	public boolean verificarDisponibilidadeFilme(Integer idFilme) {
	    boolean filmeEmprestado = repository.existsByFilmeIdFilmeAndStatus(idFilme, StatusEmprestimo.ATIVO);
	   
	    return !filmeEmprestado; // true = disponível, false = indisponível
	}//Agora o método devolve o contrário da resposta

	public void apagarEmprestimo(Integer idEmprestimo) {
		repository.deleteById(idEmprestimo);
	}
}
