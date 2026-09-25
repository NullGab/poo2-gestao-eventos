package br.ueg.eventos.application.usecases;

import br.ueg.eventos.application.ports.in.CancelarInscricaoPort;
import br.ueg.eventos.application.ports.out.InscricaoRepositoryPort;
import br.ueg.eventos.domain.exception.DomainRuleException;
import br.ueg.eventos.domain.model.Inscricao;

public class CancelarInscricaoUseCase implements CancelarInscricaoPort {

  private final InscricaoRepositoryPort inscricaoRepository;

  public CancelarInscricaoUseCase(InscricaoRepositoryPort inscricaoRepository) {
    this.inscricaoRepository = inscricaoRepository;
  }

  @Override
  public void executar(String idInscricao) {
    Inscricao inscricao = inscricaoRepository.buscarPorId(idInscricao)
      .orElseThrow(() -> new DomainRuleException("Inscrição não encontrada."));

    inscricao.cancelar();
    inscricaoRepository.atualizar(inscricao);
  }
}
