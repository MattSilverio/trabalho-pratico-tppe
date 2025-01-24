package app;

public class DeducaoManager {
    private String[] nomesDeducoes;
    private float[] valoresDeducoes;

    public DeducaoManager() {
        nomesDeducoes = new String[0];
        valoresDeducoes = new float[0];
    }

    public void cadastrarDeducaoIntegral(String nome, float valorDeducao) {
        nomesDeducoes = adicionarAoArray(nomesDeducoes, nome);
        valoresDeducoes = adicionarAoArray(valoresDeducoes, valorDeducao);
    }

    public float getTotalOutrasDeducoes() {
        float soma = 0;
        for (float f : valoresDeducoes) {
            soma += f;
        }
        return soma;
    }

    public String getOutrasDeducoes(String nome) {
        for (String d : nomesDeducoes) {
            if (d.toLowerCase().contains(nome.toLowerCase())) {
                return d;
            }
        }
        return null;
    }

    public float getDeducao(String nome) {
        for (int i = 0; i < nomesDeducoes.length; i++) {
            if (nomesDeducoes[i].toLowerCase().contains(nome.toLowerCase())) {
                return valoresDeducoes[i];
            }
        }
        return 0;
    }

    private String[] adicionarAoArray(String[] array, String valor) {
        String[] temp = new String[array.length + 1];
        System.arraycopy(array, 0, temp, 0, array.length);
        temp[array.length] = valor;
        return temp;
    }

    private float[] adicionarAoArray(float[] array, float valor) {
        float[] temp = new float[array.length + 1];
        System.arraycopy(array, 0, temp, 0, array.length);
        temp[array.length] = valor;
        return temp;
    }
}