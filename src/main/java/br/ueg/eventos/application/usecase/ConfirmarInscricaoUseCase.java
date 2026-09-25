package br.ueg.eventos.application.usecases;

import br.ueg.eventos.application.ports.in.ConfirmarInscricaoPort;
import br.ueg.eventos.application.ports.out.InscricaoRepositoryPort;
import br.ueg.eventos.domain.exception.DomainRuleException;
import br.ueg.eventos.domain.model.Inscricao;

public class ConfirmarInscricaoUseCase implements ConfirmarInscricaoPort {

  private final InscricaoRepositoryPort inscricaoRepository;

  public ConfirmarInscricaoUseCase(InscricaoRepositoryPort inscricaoRepository) {
    this.inscricaoRepository = inscricaoRepository;
  }

  @Override
  public void executar(String idInscricao) {
    Inscricao inscricao = inscricaoRepository.buscarPorId(idInscricao)
      .orElseThrow(() -> new DomainRuleException("Inscrição não encontrada."));

    inscricao.confirmar();

    inscricaoRepository.atualizar(inscricao);
  }
}
