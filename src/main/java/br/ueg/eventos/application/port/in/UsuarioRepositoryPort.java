// application/ports/in/RegistrarUsuarioPort.java
package br.ueg.eventos.application.ports.in;

import br.ueg.eventos.domain.model.Usuario;

public interface RegistrarUsuarioPort {
  Usuario executar(String nome, String email, String senhaPura);
}