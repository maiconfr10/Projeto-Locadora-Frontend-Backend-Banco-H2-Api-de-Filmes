package entretenimento.FilmeXYZ.filme;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import entretenimento.FilmeXYZ.emprestimo.EmprestimoEntitie;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "filme")

public class FilmeEntitie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idFilme;
	
	private String imagem;

	@Column(nullable = false, length = 100)
	private String titulo;

	@Column(length = 20)
	private String genero;

	@Column
	private Integer anoLancamento;

	@OneToMany(mappedBy = "filme", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<EmprestimoEntitie> emprestimos = new ArrayList<>();

	public FilmeEntitie() {
	}

	public FilmeEntitie(Integer idFilme, String titulo, String genero, LocalDate anoLacamento,
			List<EmprestimoEntitie> emprestimos) {
		super();
		this.idFilme = idFilme;
		this.titulo = titulo;
		this.genero = genero;
		this.anoLancamento = anoLancamento;
		this.emprestimos = emprestimos;
	}

	public Integer getIdFilme() {
		return idFilme;
	}

	public void setIdFilme(Integer idFilme) {
		this.idFilme = idFilme;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public Integer getAnoLancamento() {
		return anoLancamento;
	}

	public void setAnoLancamento(Integer anoLancamento) {
		this.anoLancamento = anoLancamento;
	}

	public List<EmprestimoEntitie> getEmprestimos() {
		return emprestimos;
	}

	public void setEmprestimos(List<EmprestimoEntitie> emprestimos) {
		this.emprestimos = emprestimos;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

}
