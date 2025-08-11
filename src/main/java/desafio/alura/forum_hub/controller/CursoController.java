package desafio.alura.forum_hub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import desafio.alura.forum_hub.domain.curso.Curso;
import desafio.alura.forum_hub.domain.curso.DadosCriarCurso;
import desafio.alura.forum_hub.domain.curso.DadosCurso;
import desafio.alura.forum_hub.repository.CursoRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @SuppressWarnings("rawtypes")
    @PostMapping
    @Transactional
    public ResponseEntity criarCurso(@RequestBody @Valid DadosCriarCurso dados, UriComponentsBuilder uriBuilder) {
        var curso = new Curso(dados);
        cursoRepository.save(curso);

        var uri = uriBuilder.path("/cursos/{id}").buildAndExpand(curso.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosCurso(curso));
    }
}
