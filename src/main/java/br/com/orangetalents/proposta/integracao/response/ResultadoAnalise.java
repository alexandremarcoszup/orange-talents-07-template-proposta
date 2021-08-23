package br.com.orangetalents.proposta.integracao.response;

public class ResultadoAnalise {

    private String documento;
    private String nome;
    private String idProposta;
    private String resultadoSolicitacao;

    public ResultadoAnalise(String documento, String nome, String idProposta, String resultadoSolicitacao) {
        this.documento = documento;
        this.nome = nome;
        this.idProposta = idProposta;
        this.resultadoSolicitacao = resultadoSolicitacao;
    }

    public String getResultadoSolicitacao() {
        return resultadoSolicitacao;
    }
}
