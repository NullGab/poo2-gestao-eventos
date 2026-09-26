package br.ueg.eventos.application.port.out;

import br.ueg.eventos.domain.model.Inscricao;
import java.util.Optional;

public interface InscricaoRepositoryPort {
  void salvar(Inscricao inscricao);
  boolean existeInscricaoParaEvento(String usuarioId, String eventoId);
  Optional<Inscricao> buscarPorId(String idInscricao);
}
