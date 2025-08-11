package desafio.alura.forum_hub.domain.usuario;

import jakarta.validation.constraints.NotBlank;

public record DadosCriarUsuario(
        @NotBlank String nome,
        @NotBlank String email,
        @NotBlank String senha) {

}
