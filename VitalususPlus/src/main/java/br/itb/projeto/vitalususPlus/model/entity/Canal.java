package br.itb.projeto.vitalususPlus.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "Canal")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Canal{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "visualizacoes")
	private long visualizacoes;

	@NotBlank(message = "campo não preenchido")
	@Column(name = "nome")
	private String nome;

	@ManyToMany
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@JoinTable(name="Aluno_segue_canal", joinColumns=
			{@JoinColumn(name="canal_id")}, inverseJoinColumns=
			{@JoinColumn(name="seguidor_id")})
	private List<Aluno> alunos;
	private Integer seguidores;
	@OneToOne
	@JoinColumn(name = "treinador_id")
	private Treinador treinador;

	public Treinador getTreinador() {
		return treinador;
	}

	public void setTreinador(Treinador treinador) {
		this.treinador = treinador;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getVisualizacoes() {
		return visualizacoes;
	}

	public void setVisualizacoes(long visualizacoes) {
		this.visualizacoes = visualizacoes;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public Integer getSeguidores() {
		return seguidores;
	}

	public void setSeguidores(Integer seguidores) {
		this.seguidores = seguidores;
	}
	
}
