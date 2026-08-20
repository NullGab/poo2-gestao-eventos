public class RegraEmailValido implements RegraDeNegocio {
  private final String email;

  public RegraEmailValido(String email) {
    this.email = email;
  }

  @Override
  public void validar() {
    if (email == null || !email.contains("@")) {
      throw new DomainRuleException("O formato do e-mail é inválido.");
    }
  }
}
