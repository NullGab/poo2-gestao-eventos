package br.ueg.eventos.infrastructure.adapter.in.web;

import br.ueg.eventos.application.ports.in.GerarRelatorioFrequenciaPort;
import br.ueg.eventos.application.dto.ItemRelatorioDTO;
import io.javalin.http.Context;

import java.util.List;

public class RelatorioController {

    private final GerarRelatorioFrequenciaPort gerarRelatorioFrequenciaPort;

    public RelatorioController(GerarRelatorioFrequenciaPort gerarRelatorioFrequenciaPort) {
        this.gerarRelatorioFrequenciaPort = gerarRelatorioFrequenciaPort;
    }

    public void gerarRelatorioFrequencia(Context ctx) {
        String atividade = ctx.pathParam("atividade");
        
        List<ItemRelatorioDTO> relatorio = gerarRelatorioFrequenciaPort.executar(atividade);
        
        ctx.status(200).json(relatorio);
    }
}
