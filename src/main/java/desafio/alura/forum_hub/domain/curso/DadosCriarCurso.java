package desafio.alura.forum_hub.domain.curso;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCriarCurso(
        @NotBlank String nome,
        @NotNull CursoCategoria categoria) {

}
