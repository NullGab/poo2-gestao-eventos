package br.ueg.eventos.domain.model;

import br.ueg.eventos.domain.util.validator.Validador;
import br.ueg.eventos.domain.exception.DomainRuleException;
import br.ueg.eventos.domain.util.validator.RegraTextoObrigatorio;
import br.ueg.eventos.domain.util.validator.RegraEmailValido;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Usuario {
  private final String id;
  private String nome;
  private String email;
  private String senhaHash;

  private Set<FuncaoUsuario> funcoes = new HashSet<>();

  protected Usuario (String id, String nome, String email, String senhaHash) {
    Validador.avaliar(
      new RegraTextoObrigatorio(id, "O ID do usuário não pode ser vazio."),
      new RegraTextoObrigatorio(nome, "O Nome é obrigatório."),
      new RegraEmailValido(email));
    this.senhaHash = senhaHash;
    this.id = id; 
    this.nome = nome;
    this.email = email;
    this.funcoes.add(FuncaoUsuario.PARTICIPANTE);
  }

  public static Usuario registrarNovo(String id, String nome, String email, String senhaHash) {
    return new Usuario(id, nome, email, senhaHash);
  }

  public void atribuiFuncao(FuncaoUsuario funcao) {
    if (funcao != null) {
      this.funcoes.add(funcao);
    }
  }

  public void removerFuncao(FuncaoUsuario funcao) {
    if (funcao == FuncaoUsuario.PARTICIPANTE && this.funcoes.size() == 1) {
      throw new DomainRuleException("O usuário precisa ter pelo menos um papel");
    }
    this.funcoes.remove(funcao);
  }

  public boolean possuiFuncao(FuncaoUsuario funcao) {
    return this.funcoes.contains(funcao);
  }

  public String getId() { return id ;}

  public String getNome() { return nome; }

  public String getEmail() { return email; }

  public String getSenhaHash() {return senhaHash; }

  public Set<FuncaoUsuario> getFuncoes() {
    return Collections.unmodifiableSet(this.funcoes);
  }
}
