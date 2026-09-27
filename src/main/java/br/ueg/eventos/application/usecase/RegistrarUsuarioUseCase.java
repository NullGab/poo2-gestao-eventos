package br.ueg.eventos.application.usecase;

import br.ueg.eventos.application.port.in.RegistrarUsuarioPort;
import br.ueg.eventos.application.port.out.PasswordEncoderPort;
import br.ueg.eventos.application.port.out.UsuarioRepositoryPort;
import br.ueg.eventos.domain.exception.DomainRuleException;
import br.ueg.eventos.domain.model.Usuario;

public class RegistrarUsuarioUseCase implements RegistrarUsuarioPort {

  private final UsuarioRepositoryPort usuarioRepository;
  private final PasswordEncoderPort passwordEncoder;

  public RegistrarUsuarioUseCase(UsuarioRepositoryPort usuarioRepository,
      PasswordEncoderPort passwordEncoder) {
    this.usuarioRepository = usuarioRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public void executar(String id, String nome, String email, String senhaLimpa) {
    boolean emailEmUso = usuarioRepository.buscarPorEmail(email).isPresent();
    if (emailEmUso) {
      throw new DomainRuleException("Já existe um usuário cadastrado com esse e-mail.");
    }

    String senhaHash = passwordEncoder.encode(senhaLimpa);

    Usuario novoUsuario = Usuario.registrarNovo(id, nome, email, senhaHash);

    usuarioRepository.salvar(novoUsuario);
  }
}

