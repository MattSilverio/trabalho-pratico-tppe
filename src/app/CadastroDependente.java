package app;

public class CadastroDependente {
    private String nome;
    private String parentesco;
    private DependenteManager dependenteManager;

    public CadastroDependente(String nome, String parentesco, DependenteManager dependenteManager) {
        this.nome = nome;
        this.parentesco = parentesco;
        this.dependenteManager = dependenteManager;
    }

    public void executar() {
        dependenteManager.nomesDependentes = adicionarAoArray(dependenteManager.nomesDependentes, nome);
        dependenteManager.parentescosDependentes = adicionarAoArray(dependenteManager.parentescosDependentes, parentesco);
        dependenteManager.numDependentes++;
    }

    private String[] adicionarAoArray(String[] array, String valor) {
        String[] temp = new String[array.length + 1];
        System.arraycopy(array, 0, temp, 0, array.length);
        temp[array.length] = valor;
        return temp;
    }
}
