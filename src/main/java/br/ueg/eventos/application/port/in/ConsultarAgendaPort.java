package br.ueg.eventos.application.port.in;

import br.ueg.eventos.domain.model.Atividade;
import java.util.List;

public interface ConsultarAgendaPort {
  List<Atividade> executar(String idInscricao);
}
