package entretenimento.FilmeXYZ.emprestimo;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import entretenimento.FilmeXYZ.StatusEmprestimo;
import entretenimento.FilmeXYZ.cliente.ClienteEntitie;
import entretenimento.FilmeXYZ.filme.FilmeEntitie;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "emprestimo")

public class EmprestimoEntitie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idEmprestimo;

	@Column(nullable = false)
	private LocalDate dataEmprestimo;
	
	@Column(nullable=false)
	private LocalDate dataDevolucao;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private StatusEmprestimo status;

	@Column(nullable = false)
	private BigDecimal valor;
    
	
	@ManyToOne
	@JoinColumn(name = "idFilme", nullable = false)
	private FilmeEntitie filme;

	
	@ManyToOne
	@JoinColumn(name = "idCliente", nullable = false)
	private ClienteEntitie cliente;
	
	
	public EmprestimoEntitie() {}


	public EmprestimoEntitie(Integer idEmprestimo, LocalDate dataEmprestimo, LocalDate dataDevolucao,
			StatusEmprestimo status, BigDecimal valor, FilmeEntitie filme, ClienteEntitie cliente) {
		super();
		this.idEmprestimo = idEmprestimo;
		this.dataEmprestimo = dataEmprestimo;
		this.dataDevolucao = dataDevolucao;
		this.status = status;
		this.valor = valor;
		this.filme = filme;
		this.cliente = cliente;
	}


	public Integer getIdEmprestimo() {
		return idEmprestimo;
	}


	public void setIdEmprestimo(Integer idEmprestimo) {
		this.idEmprestimo = idEmprestimo;
	}


	public LocalDate getDataEmprestimo() {
		return dataEmprestimo;
	}


	public void setDataEmprestimo(LocalDate dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}


	public LocalDate getDataDevolucao() {
		return dataDevolucao;
	}


	public void setDataDevolucao(LocalDate dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}


	public StatusEmprestimo getStatus() {
		return status;
	}


	public void setStatus(StatusEmprestimo status) {
		this.status = status;
	}


	public BigDecimal getValor() {
		return valor;
	}


	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}


	public FilmeEntitie getFilme() {
		return filme;
	}


	public void setFilme(FilmeEntitie filme) {
		this.filme = filme;
	}


	public ClienteEntitie getCliente() {
		return cliente;
	}


	public void setCliente(ClienteEntitie cliente) {
		this.cliente = cliente;
	}
	
	
}
