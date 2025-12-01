package entretenimento.FilmeXYZ.EmprestimoResponseDTO;

import java.time.LocalDate;

import entretenimento.FilmeXYZ.emprestimo.EmprestimoEntitie;

public class EmprestimoResponseDTO {

	private Integer id;
	private LocalDate dataEmprestimo;
	private LocalDate dataDevolucao;
	private String nomeCliente;
	private String tituloFilme;

	// Construtor que recebe sua entidade EmprestimoEntitie
	public EmprestimoResponseDTO(EmprestimoEntitie e) {
		if (e == null)
			return;

		this.id = e.getIdEmprestimo(); // seu campo é idEmprestimo (Integer)
		this.dataEmprestimo = e.getDataEmprestimo();
		this.dataDevolucao = e.getDataDevolucao();

		// proteção contra null (se cliente/filme vierem vazios)
		if (e.getCliente() != null) {
			this.nomeCliente = e.getCliente().getNome();
		} else {
			this.nomeCliente = null;
		}

		if (e.getFilme() != null) {
			this.tituloFilme = e.getFilme().getTitulo();
		} else {
			this.tituloFilme = null;
		}
	}

	// Getters (opcionais: adicione setters se realmente precisar)
	public Integer getId() {
		return id;
	}

	public LocalDate getDataEmprestimo() {
		return dataEmprestimo;
	}

	public LocalDate getDataDevolucao() {
		return dataDevolucao;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public String getTituloFilme() {
		return tituloFilme;
	}
}
