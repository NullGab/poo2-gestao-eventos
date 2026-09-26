package br.ueg.eventos.application.port.out;

import br.ueg.eventos.domain.model.Evento;
import br.ueg.eventos.domain.model.StatusEvento;

import java.util.List;
import java.util.Optional;

public interface EventoRepositoryPort {

    Evento salvar(Evento evento);

    Optional<Evento> buscarPorId(String id);

    List<Evento> buscarTodos();

    List<Evento> buscarPorOrganizadorId(String organizadorId);

    List<Evento> buscarPorStatus(StatusEvento status);

    void deletarPorId(String id);
}