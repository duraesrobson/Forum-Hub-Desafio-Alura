package desafio.alura.forum_hub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import desafio.alura.forum_hub.domain.usuario.DadosCriarUsuario;
import desafio.alura.forum_hub.domain.usuario.Usuario;
import desafio.alura.forum_hub.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public Usuario criarUsuario(DadosCriarUsuario dados) {
        String senhaCriptografada = passwordEncoder.encode(dados.senha());
        Usuario usuario = new Usuario(dados.nome(), dados.email(), senhaCriptografada);

        return usuarioRepository.save(usuario);
    }
}
