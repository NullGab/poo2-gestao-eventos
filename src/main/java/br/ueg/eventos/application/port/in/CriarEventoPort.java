package br.ueg.eventos.application.port.in;

import br.ueg.eventos.domain.model.Evento;
import br.ueg.eventos.domain.model.ModalidadeEvento;
import br.ueg.eventos.domain.model.TipoEvento;

import java.time.ZonedDateTime;

public interface CriarEventoPort {
    Evento executar(
            String idOrganizador,
            String titulo,
            String descricao,
            TipoEvento tipo,
            ModalidadeEvento modalidade,
            String endereco,
            ZonedDateTime dataInicio,
            ZonedDateTime dataFim
    );
}