package br.ueg.eventos.infrastructure.adapter.in.web;

import br.ueg.eventos.application.port.in.CadastrarAtividadePort;
import br.ueg.eventos.application.port.in.PublicarEventoPort;
import br.ueg.eventos.application.port.in.CriarEventoPort;
import br.ueg.eventos.application.dto.EventoDTO;
import br.ueg.eventos.application.dto.CadastrarAtividadeDTO;
import io.javalin.http.Context;

import br.ueg.eventos.domain.model.Atividade;
import br.ueg.eventos.domain.model.Evento;


public class EventoController {
    private final CriarEventoPort criarEventoPort;
    private final PublicarEventoPort publicarEventoPort;
    private final CadastrarAtividadePort cadastrarAtividadePort;


    public EventoController(CriarEventoPort criarEventoPort,
                            PublicarEventoPort publicarEventoPort,
                            CadastrarAtividadePort cadastrarAtividadePort) {

        this.criarEventoPort = criarEventoPort;
        this.publicarEventoPort = publicarEventoPort;
        this.cadastrarAtividadePort = cadastrarAtividadePort;

    }

    public void criar(Context ctx) {
        EventoDTO dto = ctx.bodyAsClass(EventoDTO.class);

        Evento eventoCriado = criarEventoPort.executar(
                dto.getIdOrganizador(),
                dto.getTitulo(),
                dto.getDescricao(),
                dto.getTipo(),
                dto.getModalidade(),
                dto.getEndereco(),
                dto.getDataInicio(),
                dto.getDataFim()
        );
        ctx.status(201).json(eventoCriado);

    }

    public void publicar(Context ctx) {
        String idEvento = ctx.pathParam("id");
        publicarEventoPort.executar(idEvento);
        ctx.status(200).result("Evento publicado com sucesso!");
    }

    public void CadastrarAtividade(Context ctx) {
        String idEvento = ctx.pathParam("id");

        CadastrarAtividadeDTO dto = ctx.bodyAsClass(CadastrarAtividadeDTO.class);

        Atividade atividadeCriada = cadastrarAtividadePort.executar(
                idEvento,
                dto.getTitulo(),
                dto.getDescricao(),
                dto.getTipo(),
                dto.getDataInicio(),
                dto.getDataFim(),
                dto.getLocal()
        );

        ctx.status(201).json(atividadeCriada);
    }
}
