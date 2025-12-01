package entretenimento.FilmeXYZ.cliente;

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
@Table(name = "cliente")

public class ClienteEntitie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCliente;

	@Column(nullable = false, length = 100)
	private String nome;

	@Column(nullable = false, length = 11, unique = true)
	private String cpf;

	@Column
	private String telefone;

	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<EmprestimoEntitie> emprestimos = new ArrayList<>();

	public ClienteEntitie() {
	}

	public ClienteEntitie(Integer idCliente, String nome, String cpf, String telefone,
			List<EmprestimoEntitie> emprestimos) {

		this.idCliente = idCliente;
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
		this.emprestimos = emprestimos;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public List<EmprestimoEntitie> getEmprestimos() {
		return emprestimos;
	}

	public void setEmprestimos(List<EmprestimoEntitie> emprestimos) {
		this.emprestimos = emprestimos;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

}
