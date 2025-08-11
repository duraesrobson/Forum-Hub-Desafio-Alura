package desafio.alura.forum_hub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import desafio.alura.forum_hub.domain.curso.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

}
