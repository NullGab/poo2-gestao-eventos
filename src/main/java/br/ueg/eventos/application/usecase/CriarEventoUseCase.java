package br.ueg.eventos.application.usecase;

import br.ueg.eventos.application.port.in.CriarEventoPort;
import br.ueg.eventos.application.port.out.EventoRepositoryPort;
import br.ueg.eventos.application.port.out.UsuarioRepositoryPort;
import br.ueg.eventos.domain.exception.DomainRuleException;
import br.ueg.eventos.domain.model.*;

import java.time.ZonedDateTime;
import java.util.UUID;

public class CriarEventoUseCase implements CriarEventoPort{

    private final EventoRepositoryPort eventoRepository;
    private final UsuarioRepositoryPort usuarioRepository;

    public CriarEventoUseCase(EventoRepositoryPort eventoRepository, UsuarioRepositoryPort usuarioRepository) {
        this.eventoRepository = eventoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Evento executar(
            String idOrganizador,
            String titulo,
            String descricao,
            TipoEvento tipo,
            ModalidadeEvento modalidade,
            String endereco,
            ZonedDateTime dataInicio,
            ZonedDateTime dataFim
    ) {
        Usuario organizador = usuarioRepository.buscarPorId(idOrganizador)
                .orElseThrow(() -> new DomainRuleException("O usuário organizador não foi encontrado!"));

        String idEvento = UUID.randomUUID().toString();

        Evento novoEvento = Evento.criarNovo(
                idEvento,
                organizador,
                titulo,
                descricao,
                tipo,
                modalidade,
                endereco,
                dataInicio,
                dataFim
        );

        return eventoRepository.salvar(novoEvento);
    }
}