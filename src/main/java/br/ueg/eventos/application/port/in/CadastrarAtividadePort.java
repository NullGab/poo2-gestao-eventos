package br.ueg.eventos.application.port.in;

import br.ueg.eventos.domain.model.Atividade;
import br.ueg.eventos.domain.model.Local;
import br.ueg.eventos.domain.model.TipoAtividade;

import java.time.ZonedDateTime;

public interface  CadastrarAtividadePort {
    Atividade executar(
            String idEvento,
            String titulo,
            String descricao,
            TipoAtividade tipo,
            ZonedDateTime dataInicio,
            ZonedDateTime dataFim,
            Local local
    );
}
