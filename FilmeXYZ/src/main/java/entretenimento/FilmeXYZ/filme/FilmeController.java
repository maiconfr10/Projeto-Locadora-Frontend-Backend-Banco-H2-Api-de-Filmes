package entretenimento.FilmeXYZ.filme;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("filme")
public class FilmeController {

	@Autowired
	private FilmeService service;

	@PostMapping
	public FilmeEntitie cadastrarFilme(@RequestBody FilmeEntitie novoFilme) {
		return service.cadastrarFilme(novoFilme);
	}

	@PutMapping("/{idFilme}")
	public FilmeEntitie atualizarFilme(@PathVariable Integer idFilme, @RequestBody FilmeEntitie filmeAtualizado) {
		return service.atualizarFilme(idFilme, filmeAtualizado);
	}

	@GetMapping("/{idFilme}")
	public ResponseEntity<FilmeEntitie> buscarFilme(@PathVariable Integer idFilme) {
		return service.buscarFilmeId(idFilme).map(ResponseEntity::ok)// 200
				.orElse(ResponseEntity.notFound().build());// 404
	}

	@GetMapping
	public List<FilmeEntitie> listarFilmes() {
		return service.listarFilme();

	}

	@GetMapping(params = "titulo")
	public FilmeEntitie buscarTitulo(String titulo) {
		return service.buscarTitulo(titulo);
	}

	@DeleteMapping("/{idFilme}")
	public void apagar(@PathVariable Integer idFilme) {
		service.apagarFilme(idFilme);
	}
	
	  // =========================================================
    // 🌎 TMDB - BUSCAR EXTERNO (NÃO SALVA → APENAS MOSTRA)
    // =========================================================
    @GetMapping("/externo")
    public ResponseEntity<?> buscarExterno(@RequestParam String titulo) {
        FilmeEntitie filme = service.buscarExterno(titulo);

        if (filme == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(filme);
    }

    // =========================================================
    // 🌎 TMDB - CADASTRAR EXTERNO (SALVA NO BANCO)
    // =========================================================
    @PostMapping("/externo")
    public ResponseEntity<FilmeEntitie> cadastrarFilmeExterno(@RequestBody FilmeEntitie filmeTemp) {
        FilmeEntitie salvo = service.cadastrarExterno(filmeTemp);
        return ResponseEntity.ok(salvo);
    }
}
	

	
	
	
	
	
	

