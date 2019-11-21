package br.com.alura.forum.controller;

import br.com.alura.forum.controller.dto.DetalhesDoTopicoDto;
import br.com.alura.forum.controller.dto.TopicoDto;
import br.com.alura.forum.controller.form.AtualizacaoTopicoForm;
import br.com.alura.forum.controller.form.TopicoForm;
import br.com.alura.forum.model.Topico;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

	@Autowired
	private TopicoRepository topicoRepository;
	
	@Autowired
	private CursoRepository cursoRepository;

	/* Use pagination for a large number of items found */
	@GetMapping
	public Page<TopicoDto> lista(@RequestParam(required = false) String nomeCurso, @RequestParam int pagina,
								 @RequestParam int quantidade) {

		Pageable paginacao = PageRequest.of((pagina-1), quantidade);

		if (nomeCurso == null) {
			Page<Topico> topicos = this.topicoRepository.findAll(paginacao);
			return TopicoDto.converter(topicos);
		} else {
			Page<Topico> topicos = this.topicoRepository.findByCursoNome(nomeCurso, paginacao);
			return TopicoDto.converter(topicos);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DetalhesDoTopicoDto> detalhar(@PathVariable Long id) {
		Optional<Topico> topico = this.topicoRepository.findById(id);
		
		if (!topico.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(new DetalhesDoTopicoDto(topico.get()));
	}
	
	@PostMapping
	public ResponseEntity<TopicoDto> cadastrar(@Valid @RequestBody TopicoForm form, UriComponentsBuilder uriCompBuilder) {
		Topico topico = form.converter(this.cursoRepository);
		this.topicoRepository.save(topico);
		
		URI uri = uriCompBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new TopicoDto(topico));
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<TopicoDto> atualizar(@PathVariable Long id, @Valid @RequestBody AtualizacaoTopicoForm atualizacaoTopicoForm) {
		Optional<Topico> optional = this.topicoRepository.findById(id);
		
		if (!optional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		Topico topico = atualizacaoTopicoForm.atualizar(id, this.topicoRepository);
		return ResponseEntity.ok(new TopicoDto(topico));
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		Optional<Topico> topico = this.topicoRepository.findById(id);
		
		if (!topico.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		this.topicoRepository.deleteById(id);
		
		return ResponseEntity.ok().build();
	}
}
