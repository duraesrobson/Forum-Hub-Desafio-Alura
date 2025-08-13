package desafio.alura.forum_hub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import desafio.alura.forum_hub.domain.usuario.DadosAutenticacaoUsuario;
import desafio.alura.forum_hub.domain.usuario.Usuario;
import desafio.alura.forum_hub.infra.security.DadosTokenJWT;
import desafio.alura.forum_hub.infra.security.TokenService;
import desafio.alura.forum_hub.infra.security.UsuarioSecurity;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @SuppressWarnings("rawtypes")
    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacaoUsuario dados) {
        var authToken = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());
        var authentication = manager.authenticate(authToken);

        UsuarioSecurity usuarioSecurity = (UsuarioSecurity) authentication.getPrincipal();
        Usuario usuario = usuarioSecurity.getUsuario();

        var tokenJWT = tokenService.gerarToken(usuario);

        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }

}
