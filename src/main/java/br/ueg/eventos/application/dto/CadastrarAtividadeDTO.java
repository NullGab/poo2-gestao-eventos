package br.ueg.eventos.application.dto;

import br.ueg.eventos.domain.model.Local;
import br.ueg.eventos.domain.model.TipoAtividade;

import java.time.ZonedDateTime;

public class CadastrarAtividadeDTO {
    private final String titulo;
    private final String descricao;
    private final TipoAtividade tipo;
    private final ZonedDateTime dataInicio;
    private final ZonedDateTime dataFim;
    private final Local local;

    public CadastrarAtividadeDTO(
            String titulo,
            String descricao,
            TipoAtividade tipo,
            ZonedDateTime dataInicio,
            ZonedDateTime dataFim,
            Local local
    ) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.tipo = tipo;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.local = local;
    }

    public String getTitulo() { return titulo; }
    public String getDescricao() { return descricao; }
    public TipoAtividade getTipo () { return tipo; }
    public ZonedDateTime getDataInicio() { return dataInicio; }
    public ZonedDateTime getDataFim() { return dataFim; }
    public Local getLocal() { return local; }
}
