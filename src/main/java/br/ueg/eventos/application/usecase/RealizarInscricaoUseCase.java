package br.ueg.eventos.application.usecase;

import br.ueg.eventos.application.port.in.RealizarInscricaoPort;
import br.ueg.eventos.application.port.out.EventoRepositoryPort;
import br.ueg.eventos.application.port.out.InscricaoRepositoryPort;
import br.ueg.eventos.application.port.out.UsuarioRepositoryPort;
import br.ueg.eventos.domain.exception.DomainRuleException;
import br.ueg.eventos.domain.model.Evento;
import br.ueg.eventos.domain.model.Inscricao;
import br.ueg.eventos.domain.model.StatusEvento;
import br.ueg.eventos.domain.model.Usuario;

import java.time.ZonedDateTime;
import java.util.UUID;

public class RealizarInscricaoUseCase implements RealizarInscricaoPort {

    private final InscricaoRepositoryPort inscricaoRepository;
    private final UsuarioRepositoryPort usuarioRepository;
    private final EventoRepositoryPort eventoRepository;

    public RealizarInscricaoUseCase(InscricaoRepositoryPort inscricaoRepository,
            UsuarioRepositoryPort usuarioRepository,EventoRepositoryPort eventoRepository) {
      this.inscricaoRepository = inscricaoRepository;
      this.usuarioRepository = usuarioRepository;
      this.eventoRepository = eventoRepository;
    }

    @Override
    public Inscricao executar(String usuarioId, String eventoId) {
        Usuario usuario = usuarioRepository.buscarPorId(usuarioId)
                .orElseThrow(() -> new DomainRuleException("Usuário não encontrado."));
        
        Evento evento = eventoRepository.buscarPorId(eventoId)
                .orElseThrow(() -> new DomainRuleException("Evento não encontrado."));

        if (evento.getSituacao() != StatusEvento.PUBLICADO) {
            throw new DomainRuleException("Não é possível se inscrever em um evento que não está publicado.");
        }
        
        if (inscricaoRepository.existeInscricaoParaEvento(usuarioId, eventoId)) {
            throw new DomainRuleException("O usuário já está inscrito neste evento.");
        }

        String idGerado = UUID.randomUUID().toString();
        
        Inscricao novaInscricao = Inscricao.criarNova(idGerado, usuario, evento, ZonedDateTime.now());

        inscricaoRepository.salvar(novaInscricao);

        return novaInscricao;
    }
}
