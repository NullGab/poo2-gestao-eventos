package br.ueg.eventos.domain.model;

import br.ueg.eventos.domain.util.validator.Validador;
import br.ueg.eventos.domain.util.validator.RegraObjetoNaoNulo;

public class VinculoPessoaAtividade {

  private final Usuario pessoa;
  private final PapelNaAtividade papel;
  private final Integer cargaHoraria; 

  public VinculoPessoaAtividade(Usuario pessoa, PapelNaAtividade papel, Integer cargaHoraria) {
    Validador.avaliar(
        new RegraObjetoNaoNulo(pessoa, "A pessoa vinculada nao pode ser nula."),
        new RegraObjetoNaoNulo(papel, "O papel da pessoa na atividade é obrigatorio.")
        );

    this.pessoa = pessoa;
    this.papel = papel;
    this.cargaHoraria = cargaHoraria;
  }

  public Usuario getPessoa() {
    return pessoa;
  }

  public PapelNaAtividade getPapel() {
    return papel;
  }

  public Integer getCargaHoraria() {
    return cargaHoraria;
  }
}
