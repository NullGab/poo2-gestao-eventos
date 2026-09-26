package br.ueg.eventos.application.usecase;

import br.ueg.eventos.application.port.in.SelecionarAtividadePort;
import br.ueg.eventos.application.port.out.AtividadeRepositoryPort;
import br.ueg.eventos.application.port.out.InscricaoRepositoryPort;
import br.ueg.eventos.domain.exception.DomainRuleException;
import br.ueg.eventos.domain.model.Atividade;
import br.ueg.eventos.domain.model.Inscricao;

public class SelecionarAtividadeUseCase implements SelecionarAtividadePort {

  private final InscricaoRepositoryPort inscricaoRepository;
  private final AtividadeRepositoryPort atividadeRepository;

  public SelecionarAtividadeUseCase(InscricaoRepositoryPort inscricaoRepository,
          AtividadeRepositoryPort atividadeRepository) {
    this.inscricaoRepository = inscricaoRepository;
    this.atividadeRepository = atividadeRepository;
  }

  @Override
  public void executar(String idInscricao, String idAtividade) {
    Inscricao inscricao = inscricaoRepository.buscarPorId(idInscricao)
      .orElseThrow(() -> new DomainRuleException("Inscrição não encontrada."));

    Atividade atividade = atividadeRepository.buscarPorId(idAtividade)
      .orElseThrow(() -> new DomainRuleException("Atividade não encontrada."));

    inscricao.adicionarAtividade(atividade);

    inscricaoRepository.atualizar(inscricao);
  }
}
