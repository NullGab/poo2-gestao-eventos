package br.ueg.eventos.domain.model;

import br.ueg.eventos.domain.exception.DomainRuleException;
import java.util.Collections;
import java.util.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Usuario {
  private final String id;
  private String nome;
  private String email;
  private String senhaHash;

  protected Usuario (String id, String nome, String email, String senhaHash) {
    
  }
}
