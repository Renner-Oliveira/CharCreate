package Entidades;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Personagem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long cod_char;

	@Column
	private String nome_char;

	@Column
	private int num_pele;

	@Column
	private int num_cabelo;

	@Column
	private int num_corcabelo;

	@Column
	private int num_classe;

	@Column
	private String classe;

	@ManyToOne
	@JoinColumn(name = "cod_conta")
	private Conta conta;

	public long getCod_char() {
		return cod_char;
	}

	public void setCod_char(long cod_char) {
		this.cod_char = cod_char;
	}

	public String getNome_char() {
		return nome_char;
	}

	public void setNome_char(String string) {
		this.nome_char = string;
	}

	public int getNum_pele() {
		return num_pele;
	}

	public void setNum_pele(int num_pele) {
		this.num_pele = num_pele;
	}

	public int getNum_cabelo() {
		return num_cabelo;
	}

	public void setNum_cabelo(int num_cabelo) {
		this.num_cabelo = num_cabelo;
	}

	public int getNum_classe() {
		return num_classe;
	}

	public void setNum_classe(int num_classe) {
		this.num_classe = num_classe;
	}

	public int getNum_corcabelo() {
		return num_corcabelo;
	}

	public void setNum_corcabelo(int num_corcabelo) {
		this.num_corcabelo = num_corcabelo;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}



}
