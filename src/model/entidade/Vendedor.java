package model.entidade;

import java.io.Serializable;
import java.util.Date;

public class Vendedor implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;
	private String email;
	private Date dtNasc;
	private Double salarioBase;

	private Departamento depto;

	public Vendedor() {

	}

	public Vendedor(Integer id, String nome, String email, Date dtNasc,
			Double salarioBase, Departamento depto) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.dtNasc = dtNasc;
		this.salarioBase = salarioBase;
		this.depto = depto;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDtNasc() {
		return dtNasc;
	}

	public void setDtNasc(Date dtNasc) {
		this.dtNasc = dtNasc;
	}

	public Double getSalarioBase() {
		return salarioBase;
	}

	public void setSalarioBase(Double salarioBase) {
		this.salarioBase = salarioBase;
	}

	public Departamento getDepto() {
		return depto;
	}

	public void setDepto(Departamento depto) {
		this.depto = depto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vendedor other = (Vendedor) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Vendedor [id=" + id + ", nome=" + nome + ", email=" + email
				+ ", dtNasc=" + dtNasc + ", salarioBase=" + salarioBase
				+ ", depto=" + depto + "]";
	}

}