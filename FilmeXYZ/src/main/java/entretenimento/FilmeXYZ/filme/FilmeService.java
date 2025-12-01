package entretenimento.FilmeXYZ.filme;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entretenimento.FilmeXYZ.filme.tmdb.TmdbMovieDTO;
import entretenimento.FilmeXYZ.filme.tmdb.TmdbService;

@Service
public class FilmeService {

	@Autowired
	public FilmeRepository repository;

	@Autowired
	private TmdbService tmdbService;

	public FilmeEntitie buscarOuCadastrar(String titulo) {
	    FilmeEntitie filme = repository.findByTitulo(titulo);

	    if (filme != null) {
	        return filme;
	    }

	    TmdbMovieDTO externo = tmdbService.buscarFilme(titulo);

	    if (externo == null) {
	        return null;
	    }

	    FilmeEntitie novo = new FilmeEntitie();
	    novo.setTitulo(externo.title);
	    novo.setGenero("Desconhecido");

	    novo.setAnoLancamento(
	        externo.release_date != null && externo.release_date.length() >= 4
	            ? Integer.parseInt(externo.release_date.substring(0, 4))
	            : null
	    );

	    // 🔥 ADICIONAR POSTER (TMDB usa prefixo)
	    if (externo.poster_path != null) {
	        novo.setImagem("https://image.tmdb.org/t/p/w500" + externo.poster_path);
	    } else {
	        novo.setImagem(null);
	    }

	    return repository.save(novo);
	}

	
	
	public FilmeEntitie cadastrarFilme(FilmeEntitie filme) {
		return repository.save(filme);
	}

	public List<FilmeEntitie> listarFilme() {
		return repository.findAll();

	}

	public Optional<FilmeEntitie> buscarFilmeId(Integer idFilme) {
		return repository.findById(idFilme);
	}

	public FilmeEntitie atualizarFilme(Integer idFilme, FilmeEntitie atualizadoFilme) {

		FilmeEntitie filmeExistente = repository.findById(idFilme)
				.orElseThrow(() -> new RuntimeException("Filme não encontrado com Id" + idFilme));

		filmeExistente.setTitulo(atualizadoFilme.getTitulo());
		filmeExistente.setAnoLancamento(atualizadoFilme.getAnoLancamento());
		filmeExistente.setGenero(atualizadoFilme.getGenero());
		filmeExistente.setEmprestimos(atualizadoFilme.getEmprestimos());

		return repository.save(filmeExistente);

	}

	public void apagarFilme(Integer idFilme) {
		repository.deleteById(idFilme);
	}

	public FilmeEntitie buscarTitulo(String titulo) {
		return repository.findByTitulo(titulo);
	}

}
