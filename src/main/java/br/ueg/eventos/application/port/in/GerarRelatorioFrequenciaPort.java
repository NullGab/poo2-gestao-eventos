package br.ueg.eventos.application.port.in;

import br.ueg.eventos.application.dto.ItemRelatorioDTO;
import java.util.List;

public interface GerarRelatorioFrequenciaPort {
    List<ItemRelatorioDTO> executar(String atividade);
}
