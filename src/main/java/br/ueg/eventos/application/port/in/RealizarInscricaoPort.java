package br.ueg.eventos.application.ports.in;

import br.ueg.eventos.domain.model.Inscricao;

public interface RealizarInscricaoPort {
  Inscricao executar(String usuarioId, String eventoId);
}
