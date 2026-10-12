package br.ueg.eventos.application.usecase;

import br.ueg.eventos.application.port.in.CadastrarAtividadePort;
import br.ueg.eventos.application.port.out.EventoRepositoryPort;
import br.ueg.eventos.domain.exception.DomainRuleException;
import br.ueg.eventos.domain.model.Atividade;
import br.ueg.eventos.domain.model.Evento;
import br.ueg.eventos.domain.model.Local;
import br.ueg.eventos.domain.model.TipoAtividade;

import java.time.ZonedDateTime;
import java.util.UUID;

public class CadastrarAtividadeUseCase implements CadastrarAtividadePort {
    private final EventoRepositoryPort eventoRepository;

    public CadastrarAtividadeUseCase(EventoRepositoryPort eventoRepository){
        this.eventoRepository = eventoRepository;
    }

    @Override
    public Atividade executar(
            String idEvento,
            String titulo,
            String descricao,
            TipoAtividade tipo,
            ZonedDateTime dataInicio,
            ZonedDateTime dataFim,
            Local local
    ) {
        Evento evento = eventoRepository.buscarPorId(idEvento)
                .orElseThrow(() -> new DomainRuleException("Evento não encontrado."));

        Local localEncontrado = evento.getLocaisDisponiveis().stream()
                .filter(l -> l.getId().equals(local.getId()))
                .findFirst()
                .orElseThrow(() -> new DomainRuleException("O local informado não pertence aos locais cadastrados para este evento."));

        String idAtividade = UUID.randomUUID().toString();
        Atividade novaAtividade = Atividade.criarNova(
                idAtividade,
                titulo,
                descricao,
                tipo,
                dataInicio,
                dataFim,
                localEncontrado
        );

        evento.adicionarAtividade(novaAtividade);

        eventoRepository.salvar(evento);

        return novaAtividade;
    }
}
