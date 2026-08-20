package br.ueg.eventos.domain.util.validator;

import br.ueg.eventos.domain.exception.DomainRuleException;

public class RegraObjetoNaoNulo implements RegraDeNegocio {
  private final Object objeto; 
  private final String mensagemErro;

  public RegraObjetoNaoNulo(Object objeto, String mensagemErro) {
    this.objeto = objeto;
    this.mensagemErro = mensagemErro;
  }

  @Override
  public void validar() {
    if (objeto == null) {
      throw new DomainRuleException(mensagemErro);
    }
  }
}
