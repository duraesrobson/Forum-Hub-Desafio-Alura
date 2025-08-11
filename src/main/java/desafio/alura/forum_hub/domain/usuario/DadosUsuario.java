package desafio.alura.forum_hub.domain.usuario;

public record DadosUsuario(
        Long id,
        String nome,
        String email) {

    public DadosUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getEmail());
    }
}
