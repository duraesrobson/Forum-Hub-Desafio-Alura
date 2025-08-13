package desafio.alura.forum_hub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
}
