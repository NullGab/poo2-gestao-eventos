public class RegraTextoObrigatorio implements RegraDeNegocio {
  private final String texto;
  private final String mensagemErro;

  public RegraTextoObrigatorio(String texto, String mensagemErro) {
    this.texto = texto;
    this.mensagemErro = mensagemErro;
  }

  @Override
  public void validar() {
    if (texto == null || texto.isBlank()) {
      throw new DomainRuleException(mensagemErro);
    }
  }
}
