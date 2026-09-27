package br.ueg.eventos.application.usecase;

import br.ueg.eventos.application.port.in.RegistrarFrequenciaQRCodePort;
import br.ueg.eventos.application.port.out.FrequenciaRepositoryPort;
import br.ueg.eventos.domain.model.RegistroFrequencia;
import br.ueg.eventos.domain.model.MetodoFrequencia;

public class RegistrarFrequenciaQRCodeUseCase implements RegistrarFrequenciaQRCodePort {

    private final FrequenciaRepositoryPort frequenciaRepository;

    public RegistrarFrequenciaQRCodeUseCase(FrequenciaRepositoryPort frequenciaRepository) {
        this.frequenciaRepository = frequenciaRepository;
    }

    @Override
    public void executar(String participante, String atividade) {
      RegistroFrequencia registro = RegistroFrequencia.registrarPorQRCode(
          participante, 
          atividade
          );
        
        frequenciaRepository.salvar(registro);
    }
}
