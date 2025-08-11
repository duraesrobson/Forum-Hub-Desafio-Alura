package desafio.alura.forum_hub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import desafio.alura.forum_hub.domain.resposta.Resposta;

@Service
public interface RespostaRepository extends JpaRepository<Resposta, Long> {

}
