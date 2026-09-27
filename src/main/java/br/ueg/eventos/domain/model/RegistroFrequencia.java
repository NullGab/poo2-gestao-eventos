package br.ueg.eventos.domain.model;

import br.ueg.eventos.domain.exception.DomainRuleException;
import br.ueg.eventos.domain.util.validator.RegraTextoObrigatorio;
import br.ueg.eventos.domain.util.validator.RegraObjetoNaoNulo;
import br.ueg.eventos.domain.util.validator.Validador;
import java.time.ZonedDateTime;

public class RegistroFrequencia { 

  private String participante;
  private String atividade;
  private ZonedDateTime dataHoraExata; 
  private MetodoFrequencia metodoFrequencia;
  private StatusFrequencia statusFrequencia; 
  private String responsavel; 

  protected RegistroFrequencia(String participante, String atividade, MetodoFrequencia metodoFrequencia, String responsavel) {
    Validador.avaliar(
        new RegraTextoObrigatorio(participante, "O participante é obrigatório."),
        new RegraTextoObrigatorio(atividade, "A atividade é obrigatória."),
        new RegraObjetoNaoNulo(metodoFrequencia, "O método de frequência é obrigatório.")
    );

    this.participante = participante;
    this.atividade = atividade;
    this.metodoFrequencia = metodoFrequencia;
    this.responsavel = responsavel;
    this.dataHoraExata = ZonedDateTime.now(); 

    this.statusFrequencia = StatusFrequencia.PRESENTE; 
  }

  public static RegistroFrequencia registrarManual(String participante, String atividade, String responsavel) {
    Validador.avaliar(
        new RegraTextoObrigatorio(responsavel, "A identificação do responsável é obrigatória no registro manual.")
    );
    return new RegistroFrequencia(participante, atividade, MetodoFrequencia.MANUAL, responsavel);
  }

  public static RegistroFrequencia registrarPorQRCode(String participante, String atividade) {
    return new RegistroFrequencia(participante, atividade, MetodoFrequencia.QRCODE, null);
  }

  public String getParticipante() {
    return this.participante;
  }

  public String getAtividade() {
    return this.atividade;
  }

  public MetodoFrequencia getMetodoFrequencia() {
    return this.metodoFrequencia;
  }

  public StatusFrequencia getStatusFrequencia() {
    return this.statusFrequencia;
  }

  public String getResponsavel() {
    return this.responsavel;
  }

  public ZonedDateTime getDataHoraExata() {
    return this.dataHoraExata;
  }
}
