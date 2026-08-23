package br.ueg.eventos.domain.model;

import br.ueg.eventos.domain.util.validator.Validador;
import br.ueg.eventos.domain.util.validator.RegraTextoObrigatorio;
import br.ueg.eventos.domain.util.validator.RegraObjetoNaoNulo;
import br.ueg.eventos.domain.util.validator.RegraEmailValido;
import br.ueg.eventos.domain.exception.DomainRuleException;
import java.util.Collections;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Usuario {
  private final String id;
  private String nome;
  private String email;
  private String senhaHash;

  protected Usuario (String id, String nome, String email, String senhaHash) {
    Validador.avaliar(
        new RegraTextoObrigatorio(id, "O ID do usuário não pode ser vazio."),
        new RegraTextoObrigatorio(nome, "O Nome é obrigatório."),
        new RegraEmailValido(email)
        );

    this.id = id; 
    this.nome = nome;
    this.email = email;   
  }
}
