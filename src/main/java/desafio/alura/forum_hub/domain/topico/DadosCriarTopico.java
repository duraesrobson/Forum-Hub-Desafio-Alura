package desafio.alura.forum_hub.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCriarTopico(
        @NotBlank String titulo,
        @NotBlank String mensagem,
        @NotNull Long idAutor,
        @NotNull Long idCurso) {
}
