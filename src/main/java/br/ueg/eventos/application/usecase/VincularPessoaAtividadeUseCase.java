package br.ueg.eventos.application.usecase;

import br.ueg.eventos.application.port.in.VincularPessoaAtividadePort;
import br.ueg.eventos.application.port.out.EventoRepositoryPort;
import br.ueg.eventos.application.port.out.UsuarioRepositoryPort;
import br.ueg.eventos.domain.exception.DomainRuleException;
import br.ueg.eventos.domain.model.*;

public class VincularPessoaAtividadeUseCase implements VincularPessoaAtividadePort {

    private final EventoRepositoryPort eventoRepository;
    private final UsuarioRepositoryPort usuarioRepository;

    public VincularPessoaAtividadeUseCase(
            EventoRepositoryPort eventoRepository,
            UsuarioRepositoryPort usuarioRepository
    ) {
        this.eventoRepository = eventoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void executar(
            String idEvento,
            String idAtividade,
            String idUsuario,
            PapelNaAtividade papelNaAtividade,
            Integer cargoHoraria
    ) {
        Evento evento = eventoRepository.buscarPorId(idEvento)
                .orElseThrow(() -> new DomainRuleException("Evento não encontrado."));

        Atividade atividade = evento.getAtividades().stream()
                .filter(a -> a.getId().equals(idAtividade))
                .findFirst()
                .orElseThrow(() -> new DomainRuleException("Atividade não encontrada neste evento."));

        Usuario usuario = usuarioRepository.buscarPorId(idUsuario)
                .orElseThrow(() -> new DomainRuleException("Usuário não encontrado."));

        VinculoPessoaAtividade vinculo = new VinculoPessoaAtividade(usuario, papelNaAtividade, cargoHoraria);
        atividade.vincularPessoa(vinculo);

        eventoRepository.salvar(evento);
    }

}
