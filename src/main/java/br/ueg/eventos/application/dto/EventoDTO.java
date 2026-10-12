package br.ueg.eventos.application.dto;

import br.ueg.eventos.domain.model.ModalidadeEvento;
import br.ueg.eventos.domain.model.TipoEvento;

import java.time.ZonedDateTime;

public class EventoDTO {

    private final String idOrganizador;
    private final String titulo;
    private final String descricao;
    private final TipoEvento tipo;
    private final ModalidadeEvento modalidade;
    private final String endereco;
    private final ZonedDateTime dataInicio;
    private final ZonedDateTime dataFim;

    public EventoDTO(
            String idOrganizador,
            String titulo,
            String descricao,
            TipoEvento tipo,
            ModalidadeEvento modalidade,
            String endereco,
            ZonedDateTime dataInicio,
            ZonedDateTime dataFim
    ) {
        this.idOrganizador = idOrganizador;
        this.titulo = titulo;
        this.descricao = descricao;
        this.tipo = tipo;
        this.modalidade = modalidade;
        this.endereco = endereco;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public String getIdOrganizador() { return idOrganizador; }
    public String getTitulo() { return titulo; }
    public String getDescricao() { return descricao; }
    public TipoEvento getTipo() { return tipo; }
    public ModalidadeEvento getModalidade() { return modalidade; }
    public String getEndereco() { return endereco; }
    public ZonedDateTime getDataInicio() { return dataInicio; }
    public ZonedDateTime getDataFim() { return dataFim; }
}
