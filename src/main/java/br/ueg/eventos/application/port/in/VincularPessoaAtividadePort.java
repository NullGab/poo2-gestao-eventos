package br.ueg.eventos.application.port.in;

import br.ueg.eventos.domain.model.PapelNaAtividade;

public interface VincularPessoaAtividadePort {
    void executar(
            String idEvento,
            String idAtividade,
            String idUsuario,
            PapelNaAtividade papelNaAtividade,
            Integer cargoHoraria
    );
}
