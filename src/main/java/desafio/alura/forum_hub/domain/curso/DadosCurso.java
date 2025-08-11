package desafio.alura.forum_hub.domain.curso;

public record DadosCurso(
        Long id,
        String nome,
        CursoCategoria categoria) {

    public DadosCurso(Curso curso) {
        this(curso.getId(), curso.getNome(), curso.getCategoria());
    }
}
