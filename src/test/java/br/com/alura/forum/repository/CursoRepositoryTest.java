package br.com.alura.forum.repository;

import br.com.alura.forum.model.Curso;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class CursoRepositoryTest {

    @Autowired
    private CursoRepository repository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void deveriaCarregarUmCursoAoBuscarPeloNome() {
        String nomeCurso = "HTML 5";
        String categoria = "Programação";
        Curso cursoHtml = new Curso();
        cursoHtml.setNome(nomeCurso);
        cursoHtml.setCategoria(categoria);

        this.testEntityManager.persist(cursoHtml);
        Curso curso = this.repository.findByNome(nomeCurso);

        Assertions.assertNotNull(curso);
        Assertions.assertEquals(nomeCurso, curso.getNome());
    }

    @Test
    public void naoDeveriaCarregarUmCursoCujoNomeNaoEstaCadastrado() {
        String nomeCurso = "JPA";
        Curso curso = this.repository.findByNome(nomeCurso);
        Assertions.assertNull(curso);
    }
}
