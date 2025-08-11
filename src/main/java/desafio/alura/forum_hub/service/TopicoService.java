package desafio.alura.forum_hub.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import desafio.alura.forum_hub.domain.topico.DadosCriarTopico;
import desafio.alura.forum_hub.domain.topico.DadosTopico;
import desafio.alura.forum_hub.domain.topico.Topico;
import desafio.alura.forum_hub.repository.CursoRepository;
import desafio.alura.forum_hub.repository.TopicoRepository;
import desafio.alura.forum_hub.repository.UsuarioRepository;
import jakarta.validation.Valid;

@Service
public class TopicoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    public DadosTopico criarTopico(DadosCriarTopico dados) {
        var dataCriado = LocalDateTime.now();
        var usuario = usuarioRepository.getReferenceById(dados.idAutor());
        var curso = cursoRepository.getReferenceById(dados.idCurso());

        var topico = new Topico(null, dados.titulo(), dados.mensagem(), dataCriado, true, usuario, curso, null);
        topicoRepository.save(topico);

        return new DadosTopico(topico);
    }

}
