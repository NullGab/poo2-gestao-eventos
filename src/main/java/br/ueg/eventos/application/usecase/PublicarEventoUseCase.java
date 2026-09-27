package br.ueg.eventos.application.usecase;

import br.ueg.eventos.application.port.in.PublicarEventoPort;
import br.ueg.eventos.application.port.out.EventoRepositoryPort;
import br.ueg.eventos.domain.exception.DomainRuleException;
import br.ueg.eventos.domain.model.Evento;

public class PublicarEventoUseCase implements PublicarEventoPort{
    private final EventoRepositoryPort eventoRepository;

    public PublicarEventoUseCase(EventoRepositoryPort eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    @Override
    public Evento executar(String idEvento, String idOrganizador) {
        Evento evento = eventoRepository.buscarPorId(idEvento)
                .orElseThrow(() -> new DomainRuleException("Evento não encontrado."));

        if (!evento.getOrganizador().getId().equals(idOrganizador)) {
            throw new DomainRuleException("Apenas o organizador do evento pode publicá-lo.");
        }

        evento.publicar();

        return eventoRepository.salvar(evento);
    }
}
