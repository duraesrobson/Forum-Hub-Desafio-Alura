package desafio.alura.forum_hub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import desafio.alura.forum_hub.domain.topico.DadosAtualizarTopico;
import desafio.alura.forum_hub.domain.topico.DadosCriarTopico;
import desafio.alura.forum_hub.domain.topico.DadosTopico;
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

    @SuppressWarnings("rawtypes")
    @GetMapping("/{id}")
    public ResponseEntity detalharTopico(@PathVariable Long id) {
        var topico = topicoService.detalharTopico(id);

        return ResponseEntity.ok(topico);
    }

    @SuppressWarnings("rawtypes")
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizarTopico(@PathVariable @RequestBody Long id, DadosAtualizarTopico dados) {
        var topico = topicoService.atualizarTopico(id, dados);
        return ResponseEntity.ok(topico);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity apagarTopico(@PathVariable @RequestBody Long id) {
        topicoService.apagarTopico(id);
        return ResponseEntity.ok("Tópico apagado com sucesso!");
    }

}
