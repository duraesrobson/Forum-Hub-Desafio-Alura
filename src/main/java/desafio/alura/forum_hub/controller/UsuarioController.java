package desafio.alura.forum_hub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import desafio.alura.forum_hub.domain.usuario.DadosCriarUsuario;
import desafio.alura.forum_hub.domain.usuario.DadosUsuario;
import desafio.alura.forum_hub.service.UsuarioService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @SuppressWarnings("rawtypes")
    @PostMapping
    @Transactional
    public ResponseEntity criarUsuario(@RequestBody @Valid DadosCriarUsuario dados, UriComponentsBuilder uriBuilder) {
        var usuario = usuarioService.criarUsuario(dados);

        var uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosUsuario(usuario));
    }

    @GetMapping
    public ResponseEntity<Page<DadosUsuario>> listarUsuarios(
            @PageableDefault(size = 10, sort = { "nome" }) Pageable paginacao) {
        var page = usuarioService.listarUsuarios(paginacao);
        return ResponseEntity.ok(page);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity desativarUsuario(@PathVariable Long id) {
        usuarioService.desativarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<String> reativarUsuario(@PathVariable Long id) {
        usuarioService.reativarUsuario(id);
        return ResponseEntity.ok("Usuário reativado com sucesso!");
    }
}
