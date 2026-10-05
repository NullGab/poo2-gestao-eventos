package br.ueg.eventos.application.usecase;

import br.ueg.eventos.application.dto.ItemRelatorioDTO;
import br.ueg.eventos.application.port.in.CalcularSituacaoPresencaPort;
import br.ueg.eventos.application.port.in.GerarRelatorioFrequenciaPort;
import br.ueg.eventos.application.port.out.FrequenciaRepositoryPort;
import br.ueg.eventos.application.port.out.InscricaoRepositoryPort;
import br.ueg.eventos.domain.model.RegistroFrequencia;

import java.util.ArrayList;
import java.util.List;

public class GerarRelatorioFrequenciaUseCase implements GerarRelatorioFrequenciaPort {

    private final InscricaoRepositoryPort inscricaoRepository;
    private final FrequenciaRepositoryPort frequenciaRepository;
    private final CalcularSituacaoPresencaPort calcularSituacaoPort;

    public GerarRelatorioFrequenciaUseCase(
            InscricaoRepositoryPort inscricaoRepository,
            FrequenciaRepositoryPort frequenciaRepository,
            CalcularSituacaoPresencaPort calcularSituacaoPort) {
        this.inscricaoRepository = inscricaoRepository;
        this.frequenciaRepository = frequenciaRepository;
        this.calcularSituacaoPort = calcularSituacaoPort;
    }

    @Override
    public List<ItemRelatorioDTO> executar(String atividade) { 
        
        List<String> participantesInscritos = inscricaoRepository.buscarParticipantesPorAtividade(atividade);
        List<ItemRelatorioDTO> itensRelatorio = new ArrayList<>();

        for (String participante : participantesInscritos) {
            
            List<RegistroFrequencia> registros = frequenciaRepository.buscarPorParticipanteEAtividade(participante, atividade);
            
            boolean aprovado = calcularSituacaoPort.executar(participante, atividade);

            itensRelatorio.add(new ItemRelatorioDTO(atividade, participante, registros.size(), aprovado));
        }

        return itensRelatorio;
    }
}
