package entretenimento.FilmeXYZ.emprestimo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import entretenimento.FilmeXYZ.StatusEmprestimo;

@Repository
public interface EmprestimoRepository extends JpaRepository<EmprestimoEntitie, Integer> {
	List<EmprestimoEntitie> findByClienteIdCliente(Integer idCliente);

	List<EmprestimoEntitie> findByFilmeIdFilme(Integer idFilme);

	boolean existsByFilmeIdFilmeAndStatus(Integer idFilme, StatusEmprestimo ativo);


}
