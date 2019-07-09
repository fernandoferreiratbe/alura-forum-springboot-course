package br.com.alura.forum.controller.form;

import br.com.alura.forum.model.Curso;
import br.com.alura.forum.model.Topico;
import br.com.alura.forum.repository.CursoRepository;

/* Form - Represent the data posted by user on front-end */
public class TopicoForm {

	private String titulo;
	private String mensagem;
	private String nomeCurso;
	
	public String getTitulo() {
		return this.titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getMensagem() {
		return this.mensagem;
	}
	
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	public String getNomeCurso() {
		return this.nomeCurso;
	}
	
	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}

	public Topico converter(CursoRepository cursoRepository) {
		Curso curso = cursoRepository.findByNome(this.getNomeCurso());
		return new Topico(this.getTitulo(), this.getMensagem(), curso);
	}
	
	@Override
	public String toString() {
		return this.getTitulo() + " " + this.getMensagem() + " " + this.getNomeCurso();
	}
	
}
