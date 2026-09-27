package br.ueg.eventos.application.usecase;

import br.ueg.eventos.application.port.in.CalcularSituacaoPresencaPort;
import br.ueg.eventos.application.port.out.AtividadeRepositoryPort;
import br.ueg.eventos.application.port.out.FrequenciaRepositoryPort;
import br.ueg.eventos.domain.model.PoliticaFrequencia;
import br.ueg.eventos.domain.model.RegistroFrequencia;

import java.util.List;

public class CalcularSituacaoPresencaUseCase implements CalcularSituacaoPresencaPort {

    private final FrequenciaRepositoryPort frequenciaRepository;
    private final AtividadeRepositoryPort atividadeRepository;

    public CalcularSituacaoPresencaUseCase(
            FrequenciaRepositoryPort frequenciaRepository,
            AtividadeRepositoryPort atividadeRepository) {
        this.frequenciaRepository = frequenciaRepository;
        this.atividadeRepository = atividadeRepository;
    }

    @Override
    public boolean executar(String participante, String atividade) {
        List<RegistroFrequencia> registros = frequenciaRepository.buscarPorParticipanteEAtividade(participante, atividade);
        PoliticaFrequencia politica = atividadeRepository.buscarPoliticaPorAtividade(atividade);
        
        return politica.validarFrequencia(registros);
    }
}
