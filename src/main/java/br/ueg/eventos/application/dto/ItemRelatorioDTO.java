package br.ueg.eventos.application.dto;

public class ItemRelatorioDTO {
    
    private final String atividade;
    private final String participante;
    private final int totalPresencas;
    private final boolean aprovado;

    public ItemRelatorioDTO(String atividade, String participante, int totalPresencas, boolean aprovado) {
        this.atividade = atividade;
        this.participante = participante;
        this.totalPresencas = totalPresencas;
        this.aprovado = aprovado;
    }

    public String getAtividade() { return atividade; }
    public String getParticipante() { return participante; }
    public int getTotalPresencas() { return totalPresencas; }
    public boolean isAprovado() { return aprovado; }
}
