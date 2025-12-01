package entretenimento.FilmeXYZ.emprestimo;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import entretenimento.FilmeXYZ.EmprestimoResponseDTO.EmprestimoResponseDTO;

@RestController
@RequestMapping("emprestimo")
public class EmprestimoController {

    @Autowired
    private EmprestimoService service;

    // CREATE
    @PostMapping
    public EmprestimoEntitie cadastrarEmprestimo(@RequestBody EmprestimoEntitie novoCadastro) {
        return service.cadastrarEmprestimo(novoCadastro);
    }

    // UPDATE
    @PutMapping("/{idEmprestimo}")
    public EmprestimoEntitie atualizarEmprestimo(
            @PathVariable Integer idEmprestimo,
            @RequestBody EmprestimoEntitie atualizado) {
        return service.atualizarEmprestimo(idEmprestimo, atualizado);
    }

    // LISTAR TODOS
    @GetMapping
    public List<EmprestimoEntitie> listarEmprestimo() {
        return service.listarEmprestimo();
    }

    // LISTAR POR CLIENTE
    @GetMapping("/cliente/{idCliente}")
    public List<EmprestimoResponseDTO> listarEmprestimoCliente(@PathVariable Integer idCliente) {
        return service.listarEmprestimoPorCliente(idCliente);
    }

    // LISTAR POR FILME
    @GetMapping("/filme/{idFilme}")
    public List<EmprestimoResponseDTO> listarEmprestimoFilme(@PathVariable Integer idFilme) {
        return service.listarEmprestimoPorFilme(idFilme);
    }

    // BUSCAR POR ID
    @GetMapping("/{idEmprestimo}")
    public Optional<EmprestimoEntitie> listarId(@PathVariable Integer idEmprestimo) {
        return service.buscarId(idEmprestimo);
    }

    // VERIFICAR DISPONIBILIDADE DO FILME
    @GetMapping("/filme/{idFilme}/disponivel")
    public boolean verificarFilme(@PathVariable Integer idFilme) {
        return service.verificarDisponibilidadeFilme(idFilme);
    }

    // DELETE
    @DeleteMapping("/{idEmprestimo}")
    public void deletarEmprestimo(@PathVariable Integer idEmprestimo) {
        service.apagarEmprestimo(idEmprestimo);
    }
}
