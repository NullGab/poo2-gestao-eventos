package br.ueg.eventos.domain.util.validator;
public final class Validador {
    
    private Validador() {} 
    public static void avaliar(RegraDeNegocio... regras) {
        for (RegraDeNegocio regra : regras) {
            regra.validar(); 
        }
    }
}
