// application/ports/out/PasswordHasherPort.java
package br.ueg.eventos.application.ports.out;

public interface PasswordHasherPort {
  String gerarHash(String senhaPura);
}