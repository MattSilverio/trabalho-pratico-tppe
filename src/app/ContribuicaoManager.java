package app;

public class ContribuicaoManager {
    private int numContribuicoes;
    private float totalContribuicoes;

    public ContribuicaoManager() {
        numContribuicoes = 0;
        totalContribuicoes = 0f;
    }

    /**
     * Cadastra um valor de contribuição previdenciária oficial.
     * @param contribuicao valor da contribuição previdenciária oficial
     */
    public void cadastrarContribuicao(float contribuicao) {
        numContribuicoes++;
        totalContribuicoes += contribuicao;
    }

    /**
     * Retorna o número total de contribuições realizadas como contribuição
     * previdenciária oficial.
     * @return número de contribuições realizadas
     */
    public int getNumContribuicoes() {
        return numContribuicoes;
    }

    /**
     * Retorna o valor total de contribuições oficiais realizadas.
     * @return valor total de contribuições oficiais
     */
    public float getTotalContribuicoes() {
        return totalContribuicoes;
    }
}