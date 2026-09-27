package br.ueg.eventos.application.usecase;

import br.ueg.eventos.application.port.in.RegistrarFrequenciaManualPort;
import br.ueg.eventos.application.port.out.FrequenciaRepositoryPort;
import br.ueg.eventos.domain.model.RegistroFrequencia;
import br.ueg.eventos.domain.model.MetodoFrequencia;

public class RegistrarFrequenciaManualUseCase implements RegistrarFrequenciaManualPort {

    private final FrequenciaRepositoryPort frequenciaRepository;

    public RegistrarFrequenciaManualUseCase(FrequenciaRepositoryPort frequenciaRepository) {
        this.frequenciaRepository = frequenciaRepository;
    }

    @Override
    public void executar(String participante, String atividade, String responsavel) {
        
      RegistroFrequencia registro = RegistroFrequencia.registrarManual(
          participante, 
          atividade, 
          responsavel
          );

        frequenciaRepository.salvar(registro);
    }
}
