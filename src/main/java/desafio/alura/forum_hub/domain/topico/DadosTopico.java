package desafio.alura.forum_hub.domain.topico;

import java.time.LocalDateTime;
import java.util.List;

import desafio.alura.forum_hub.domain.resposta.Resposta;

public record DadosTopico(
        Long id,
        String titulo,
        LocalDateTime dataCriacao,
        Boolean status,
        Long idAutor,
        Long idCurso,
        List<Resposta> respostas

) {

    public DadosTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getDataCriacao(), topico.getStatus(), topico.getAutor().getId(),
                topico.getCurso().getId(), topico.getRespostas());
    }
}