package app;

public class PensaoAlimenticiaManager {
    private float totalPensaoAlimenticia;

    public PensaoAlimenticiaManager() {
        totalPensaoAlimenticia = 0f;
    }

    /**
     * Cadastra um valor de pensão alimentícia para um dependente.
     * @param valor valor da pensão alimentícia
     */
    public void cadastrarPensaoAlimenticia(float valor) {
        totalPensaoAlimenticia += valor;
    }

    /**
     * Retorna o valor total de pensões alimentícias cadastradas.
     * @return valor total de pensões alimentícias
     */
    public float getTotalPensaoAlimenticia() {
        return totalPensaoAlimenticia;
    }
}