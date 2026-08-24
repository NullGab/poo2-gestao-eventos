package br.ueg.eventos.domain.model;

import br.ueg.eventos.domain.util.validator.Validador;
import br.ueg.eventos.domain.util.validator.RegraObjetoNaoNulo;
import br.ueg.eventos.domain.util.validator.RegraTextoObrigatorio;
import br.ueg.eventos.domain.exception.DomainRuleException;

import java.time.ZonedDateTime;
import java.util.UUID;

public class Certificado {

  private final String codigoValidacao;
  private final Inscricao inscricao;
  private final Integer cargaHoraria;
  private final ZonedDateTime dataEmissao;

  protected Certificado(String codigoValidacao, Inscricao inscricao, Integer cargaHoraria, ZonedDateTime dataEmissao) {
    Validador.avaliar(
        new RegraTextoObrigatorio(codigoValidacao, "O código de validação do certificado é obrigatório."),
        new RegraObjetoNaoNulo(inscricao, "O certificado deve estar vinculado a uma inscrição válida."),
        new RegraObjetoNaoNulo(cargaHoraria, "A carga horária é obrigatória."),
        new RegraObjetoNaoNulo(dataEmissao, "A data de emissão é obrigatória.")
        );

    if (cargaHoraria <= 0) {
      throw new DomainRuleException("A carga horária do certificado deve ser maior que zero.");
    }

    this.codigoValidacao = codigoValidacao;
    this.inscricao = inscricao;
    this.cargaHoraria = cargaHoraria;
    this.dataEmissao = dataEmissao;
  }

  public static Certificado emitir(Inscricao inscricao, Integer cargaHoraria) {
    String codigoGerado = UUID.randomUUID().toString();
    ZonedDateTime momentoDaEmissao = ZonedDateTime.now();

    return new Certificado(codigoGerado, inscricao, cargaHoraria, momentoDaEmissao);
  }

  public String getCodigoValidacao() {
    return codigoValidacao;
  }

  public Inscricao getInscricao() {
    return inscricao;
  }

  public Integer getCargaHoraria() {
    return cargaHoraria;
  }

  public ZonedDateTime getDataEmissao() {
    return dataEmissao;
  }
}
