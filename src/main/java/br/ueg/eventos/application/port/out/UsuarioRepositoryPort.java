package br.ueg.eventos.application.port.out;

import br.ueg.eventos.domain.model.Usuario;
import java.util.Optional;

public interface UsuarioRepositoryPort {
    Optional<Usuario> buscarPorId(String id);
    Optional<Usuario> buscarPorEmail(String email);
    Usuario salvar(Usuario usuario);
}