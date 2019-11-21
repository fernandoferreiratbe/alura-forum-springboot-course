package br.com.alura.forum.controller.dto;

import br.com.alura.forum.model.Topico;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

/* Data transfer object */
public class TopicoDto {

	private Long id;
	private String titulo;
	private String mensagem;
	private LocalDateTime dataCriacao;

	public TopicoDto(Topico topico) {
		this.id          = topico.getId();
		this.titulo      = topico.getTitulo();
		this.mensagem    = topico.getMensagem();
		this.dataCriacao = topico.getDataCriacao();
	}

	public Long getId() {
		return this.id;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public String getMensagem() {
		return this.mensagem;
	}

	public LocalDateTime getDataCriacao() {
		return this.dataCriacao;
	}

	public static Page<TopicoDto> converter(Page<Topico> topicos) {
		return topicos.map(TopicoDto::new);

//		return topicos
//				.stream()
//				.map(TopicoDto::new)
//				.collect(Collectors.toList());
	}

}
