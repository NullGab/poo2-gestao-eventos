package br.ueg.eventos.application.port.in;

import br.ueg.eventos.domain.model.Evento;

public interface PublicarEventoPort {
    Evento executar(String idEvento, String idOrganizador);
}
