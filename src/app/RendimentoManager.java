package app;

public class RendimentoManager {
    private String[] nomesRendimentos;
    private boolean[] rendimentosTributaveis;
    private float[] valoresRendimentos;
    private int numRendimentos;
    private float totalRendimentos;

    public RendimentoManager() {
        nomesRendimentos = new String[0];
        rendimentosTributaveis = new boolean[0];
        valoresRendimentos = new float[0];
        numRendimentos = 0;
        totalRendimentos = 0f;
    }

    /**
     * Cadastra um rendimento na base do contribuinte, informando o nome do
     * rendimento, seu valor e se ele é tributável ou não.
     * @param nome nome do rendimento a ser cadastrado
     * @param tributavel true caso seja tributável, false caso contrário
     * @param valor valor do rendimento a ser cadastrado
     */
    public void criarRendimento(String nome, boolean tributavel, float valor) {
        nomesRendimentos = adicionarAoArray(nomesRendimentos, nome);
        rendimentosTributaveis = adicionarAoArray(rendimentosTributaveis, tributavel);
        valoresRendimentos = adicionarAoArray(valoresRendimentos, valor);

        numRendimentos++;
        totalRendimentos += valor;
    }

    /**
     * Retorna o número de rendimentos já cadastrados para o contribuinte
     * @return numero de rendimentos
     */
    public int getNumRendimentos() {
        return numRendimentos;
    }

    /**
     * Retorna o valor total de rendimentos cadastrados para o contribuinte
     * @return valor total dos rendimentos
     */
    public float getTotalRendimentos() {
        return totalRendimentos;
    }

    /**
     * Retorna o valor total de rendimentos tributáveis do contribuinte
     * @return valor total dos rendimentos tributáveis
     */
    public float getTotalRendimentosTributaveis() {
        float totalRendimentosTributaveis = 0;
        for (int i = 0; i < rendimentosTributaveis.length; i++) {
            if (rendimentosTributaveis[i]) {
                totalRendimentosTributaveis += valoresRendimentos[i];
            }
        }
        return totalRendimentosTributaveis;
    }

    /**
     * Método auxiliar para adicionar um elemento a um array de Strings
     * @param array array original
     * @param valor valor a ser adicionado
     * @return novo array com o valor adicionado
     */
    private String[] adicionarAoArray(String[] array, String valor) {
        String[] temp = new String[array.length + 1];
        System.arraycopy(array, 0, temp, 0, array.length);
        temp[array.length] = valor;
        return temp;
    }

    /**
     * Método auxiliar para adicionar um elemento a um array de booleanos
     * @param array array original
     * @param valor valor a ser adicionado
     * @return novo array com o valor adicionado
     */
    private boolean[] adicionarAoArray(boolean[] array, boolean valor) {
        boolean[] temp = new boolean[array.length + 1];
        System.arraycopy(array, 0, temp, 0, array.length);
        temp[array.length] = valor;
        return temp;
    }

    /**
     * Método auxiliar para adicionar um elemento a um array de floats
     * @param array array original
     * @param valor valor a ser adicionado
     * @return novo array com o valor adicionado
     */
    private float[] adicionarAoArray(float[] array, float valor) {
        float[] temp = new float[array.length + 1];
        System.arraycopy(array, 0, temp, 0, array.length);
        temp[array.length] = valor;
        return temp;
    }
}