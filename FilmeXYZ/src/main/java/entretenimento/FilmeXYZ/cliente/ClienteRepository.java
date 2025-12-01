package entretenimento.FilmeXYZ.cliente;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntitie,Integer>{
	  List<ClienteEntitie> findByCpf(String cpf);
}
