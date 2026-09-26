package br.ueg.eventos.application.port.out;

import br.ueg.eventos.domain.model.Usuario;
import java.util.Optional;

public interface UsuarioRepositoryPort {
    Optional<Usuario> buscarPorEmail(String email);
    Usuario salvar(Usuario usuario);
}