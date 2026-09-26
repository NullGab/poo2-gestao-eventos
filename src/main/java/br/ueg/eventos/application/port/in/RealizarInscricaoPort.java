package br.ueg.eventos.application.port.in;

import br.ueg.eventos.domain.model.Inscricao;

public interface RealizarInscricaoPort {
    Inscricao executar(String usuarioId, String eventoId);
}
