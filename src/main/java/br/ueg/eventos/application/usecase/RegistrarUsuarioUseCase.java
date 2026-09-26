// application/usecases/RegistrarUsuarioUseCase.java
package br.ueg.eventos.application.usecases;

import br.ueg.eventos.application.ports.in.RegistrarUsuarioPort;
import br.ueg.eventos.application.ports.out.PasswordHasherPort;
import br.ueg.eventos.application.ports.out.UsuarioRepositoryPort;
import br.ueg.eventos.domain.exception.DomainRuleException;
import br.ueg.eventos.domain.model.Usuario;

import java.util.UUID;

public class RegistrarUsuarioUseCase implements RegistrarUsuarioPort {

    private final UsuarioRepositoryPort usuarioRepository;
    private final PasswordHasherPort passwordHasher;

    public RegistrarUsuarioUseCase(UsuarioRepositoryPort usuarioRepository,
                                    PasswordHasherPort passwordHasher) {
        this.usuarioRepository = usuarioRepository;
        this.passwordHasher = passwordHasher;
    }

    @Override
    public Usuario executar(String nome, String email, String senhaPura) {
        if (usuarioRepository.existePorEmail(email)) {
            throw new DomainRuleException("Já existe um usuário cadastrado com este e-mail.");
        }

        String idGerado = UUID.randomUUID().toString();
        String senhaHash = passwordHasher.gerarHash(senhaPura);

        Usuario novoUsuario = Usuario.registrarNovo(idGerado, nome, email, senhaHash);

        usuarioRepository.salvar(novoUsuario);

        return novoUsuario;
    }
}