package br.ueg.eventos.application.port.out;
import java.util.Optional;
import br.ueg.eventos.domain.model.Atividade;
import br.ueg.eventos.domain.model.PoliticaFrequencia;

public interface AtividadeRepositoryPort {
    PoliticaFrequencia buscarPoliticaPorAtividade(String atividade);
    Optional<Atividade> buscarPorId(String id);
}
