package br.ueg.eventos.domain.util.validator;
import br.ueg.eventos.domain.exception.DomainRuleException;
import java.time.ZonedDateTime;

public class RegraInicioFim implements RegraDeNegocio {
  private final  ZonedDateTime dataInicio;
  private final ZonedDateTime dataFim;

  public RegraInicioFim(ZonedDateTime dataInicio, ZonedDateTime dataFim) {
    this.dataInicio = dataInicio;
    this.dataFim = dataFim;
  }

  @Override
  public void validar() {
    if (dataFim.isBefore(dataInicio)) {
      throw new DomainRuleException("A data de término não pode ser anterior à data de início.");
    }
  }
}

