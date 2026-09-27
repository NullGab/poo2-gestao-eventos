package br.ueg.eventos.application.port.out;

public interface PasswordEncoderPort {
    String encode(String senhaLimpa);
    boolean matches(String senhaPura, String senhaHash);
}
