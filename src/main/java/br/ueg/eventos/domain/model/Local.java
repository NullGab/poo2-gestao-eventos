package br.ueg.eventos.domain.model;

import br.ueg.eventos.domain.exception.DomainRuleException;
import br.ueg.eventos.domain.util.validator.Validador;
import br.ueg.eventos.domain.util.validator.RegraTextoObrigatorio;

public class Local {
    private final String id;
    private String nome;
    private Integer capacidade;

    public Local (String id, String nome, Integer capacidade) {
        Validador.avaliar(
                new RegraTextoObrigatorio(id, "O ID não pode ser nulo!"),
                new RegraTextoObrigatorio(nome, "O local precisa ter um nome!")
        );

        if(capacidade != null && capacidade <= 0) {
            throw new DomainRuleException("A capacidade deve ser inteira e maior que 0! ");
        }
        this.id = id;
        this.nome = nome;
        this.capacidade = capacidade;
    }

    public String getId() { return id; }

    public String getNome() { return nome; }

    public Integer getCapacidade() { return capacidade; }
}
