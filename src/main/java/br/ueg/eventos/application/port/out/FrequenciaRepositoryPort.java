package br.ueg.eventos.application.port.out;

import br.ueg.eventos.domain.model.RegistroFrequencia;
import java.util.List;

public interface FrequenciaRepositoryPort {
    void salvar(RegistroFrequencia registro);
    List<RegistroFrequencia> buscarPorParticipanteEAtividade(String participante, String atividade);
}
