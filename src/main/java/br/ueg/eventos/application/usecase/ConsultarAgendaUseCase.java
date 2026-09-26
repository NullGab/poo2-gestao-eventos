package br.ueg.eventos.application.usecase;

import br.ueg.eventos.application.port.in.ConsultarAgendaPort;
import br.ueg.eventos.application.port.out.InscricaoRepositoryPort;
import br.ueg.eventos.domain.exception.DomainRuleException;
import br.ueg.eventos.domain.model.Atividade;
import br.ueg.eventos.domain.model.Inscricao;

import java.util.List;

public class ConsultarAgendaUseCase implements ConsultarAgendaPort {

  private final InscricaoRepositoryPort inscricaoRepository;

  public ConsultarAgendaUseCase(InscricaoRepositoryPort inscricaoRepository) {
    this.inscricaoRepository = inscricaoRepository;
  }

  @Override
  public List<Atividade> executar(String idInscricao) {
    Inscricao inscricao = inscricaoRepository.buscarPorId(idInscricao)
      .orElseThrow(() -> new DomainRuleException("Inscrição não encontrada."));

    return inscricao.getAtividadesSelecionadas();
  }
}
