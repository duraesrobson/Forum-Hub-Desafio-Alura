package desafio.alura.forum_hub.controller;

import java.time.LocalDateTime;

import org.hibernate.query.SortDirection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import desafio.alura.forum_hub.domain.topico.DadosCriarTopico;
import desafio.alura.forum_hub.domain.topico.DadosTopico;
import desafio.alura.forum_hub.domain.topico.Topico;
import desafio.alura.forum_hub.repository.CursoRepository;
import desafio.alura.forum_hub.repository.TopicoRepository;
import desafio.alura.forum_hub.repository.UsuarioRepository;
import desafio.alura.forum_hub.service.TopicoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @GetMapping
    public ResponseEntity<Page<DadosTopico>> listarTopicos(
            @PageableDefault(size = 10, sort = { "id" }, direction = Sort.Direction.DESC) Pageable paginacao) {
        var page = topicoService.listarTopicos(paginacao);
        return ResponseEntity.ok(page);
    }

    @SuppressWarnings("rawtypes")
    @PostMapping
    @Transactional
    public ResponseEntity criarTopico(@RequestBody @Valid DadosCriarTopico dados, UriComponentsBuilder uriBuilder) {
        var topico = topicoService.criarTopico(dados);

        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.id()).toUri();

        return ResponseEntity.created(uri).body(topico);
    }

}
