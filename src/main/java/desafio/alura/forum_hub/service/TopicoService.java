package desafio.alura.forum_hub.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import desafio.alura.forum_hub.domain.topico.DadosAtualizarTopico;
import desafio.alura.forum_hub.domain.topico.DadosCriarTopico;
import desafio.alura.forum_hub.domain.topico.DadosTopico;
import desafio.alura.forum_hub.domain.topico.Topico;
import desafio.alura.forum_hub.repository.CursoRepository;
import desafio.alura.forum_hub.repository.TopicoRepository;
import desafio.alura.forum_hub.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;

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

    public Page<DadosTopico> listarTopicos(Pageable paginacao) {
        return topicoRepository.findAllByStatusTrue(paginacao).map(DadosTopico::new);
    }

    public DadosTopico detalharTopico(Long id) {
        return acharTopicoDTO(id);
    }

    public DadosTopico atualizarTopico(Long id, DadosAtualizarTopico dados) {
        var topico = acharTopico(id);

        if (dados.titulo() != null) {
            topico.setTitulo(dados.titulo());
        }
        if (dados.mensagem() != null) {
            topico.setMensagem(dados.mensagem());
        }

        topicoRepository.save(topico);
        return new DadosTopico(topico);
    }

    public void apagarTopico(Long id) {
        topicoRepository.deleteById(id);
    }

    public DadosTopico acharTopicoDTO(Long id) {
        var topico = topicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tópico não encontrado!"));

        return new DadosTopico(topico);
    }

    public Topico acharTopico(Long id) {
        return topicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tópico não encontrado!"));
    }

}
