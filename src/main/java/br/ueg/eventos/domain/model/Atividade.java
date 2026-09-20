package br.ueg.eventos.domain.model;

import br.ueg.eventos.domain.util.validator.Validador;
import br.ueg.eventos.domain.util.validator.RegraTextoObrigatorio;
import br.ueg.eventos.domain.util.validator.RegraObjetoNaoNulo;
import br.ueg.eventos.domain.util.validator.RegraInicioFim;
import br.ueg.eventos.domain.exception.DomainRuleException;
import java.util.Collections;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class Atividade {
    private final String id;
    private final TipoAtividade tipo; 
    private String titulo;
    private String descricao;
    private ZonedDateTime dataInicio;
    private ZonedDateTime dataFim;
    private Local local;
    private List<VinculoPessoaAtividade> pessoasVinculadas;

    protected Atividade(String id, String titulo, String descricao, TipoAtividade tipo, ZonedDateTime dataInicio, ZonedDateTime dataFim, Local local) {
      Validador.avaliar(
          new RegraTextoObrigatorio(id, "Id da entidade não pode ser nulo ou vazio."),
          new RegraTextoObrigatorio(titulo, "O título da atividade é obrigatória."),
          new RegraTextoObrigatorio(descricao, "Informe a descrição da atividade."),
          new RegraObjetoNaoNulo(tipo, "Informe o tipo da atividade."),
          new RegraObjetoNaoNulo(local, "O local não pode estar vazio."),
          new RegraObjetoNaoNulo(dataInicio, "A data de inicio nao pode estar vazia."),
          new RegraObjetoNaoNulo(dataFim, "A data final nao pode estar vazia."),
          new RegraInicioFim(dataInicio, dataFim)
      );

        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.tipo = tipo;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.local = local;
        this.pessoasVinculadas = new ArrayList<>();

    }

    public static Atividade criarNova(String id, String titulo, String descricao, TipoAtividade tipo, ZonedDateTime dataInicio, ZonedDateTime dataFim, Local local){
      return new Atividade(id, titulo, descricao, tipo, dataInicio, dataFim, local);
    }

    public void vincularPessoa(VinculoPessoaAtividade vinculo) {
        if(vinculo == null) {
            throw new DomainRuleException("O vínculo de pessoa não pode ser nulo.");
        }
        this.pessoasVinculadas.add(vinculo);
    } 

    public boolean conflitaCom(Atividade outra) {
        if (!this.local.getId().equals(outra.local.getId())) {
            return false;
        }

        return this.dataInicio.isBefore(outra.dataFim) && outra.dataInicio.isBefore(this.dataFim);
    }

    public String getId() { return id; }

    public String getTitulo() { return titulo; }

    public String getDescricao() { return descricao; }

    public TipoAtividade getTipo() { return tipo; }

    public ZonedDateTime getDataInicio() { return dataInicio; }

    public ZonedDateTime getDataFim() { return dataFim; }

    public Local getLocal() { return local; }

    protected Boolean textOuVazio(String valor) {
        return valor == null || valor.isBlank();
    }

}
