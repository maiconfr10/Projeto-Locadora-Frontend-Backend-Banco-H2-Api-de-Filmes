package entretenimento.FilmeXYZ.filme;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmeRepository extends JpaRepository<FilmeEntitie,Integer> {
	FilmeEntitie findByTitulo(String titulo);
}
