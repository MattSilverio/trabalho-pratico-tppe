package app;

public class DependenteManager {
    private String[] nomesDependentes;
    private String[] parentescosDependentes;
    private int numDependentes;

    public DependenteManager() {
        nomesDependentes = new String[0];
        parentescosDependentes = new String[0];
        numDependentes = 0;
    }

    public void cadastrarDependente(String nome, String parentesco) {
        nomesDependentes = adicionarAoArray(nomesDependentes, nome);
        parentescosDependentes = adicionarAoArray(parentescosDependentes, parentesco);
        numDependentes++;
    }

    public int getNumDependentes() {
        return numDependentes;
    }

    public String getDependente(String nome) {
        for (String d : nomesDependentes) {
            if (d.contains(nome))
                return d;
        }
        return null;
    }

    public String getParentesco(String dependente) {
        for (int i = 0; i < nomesDependentes.length; i++) {
            if (nomesDependentes[i].equalsIgnoreCase(dependente))
                return parentescosDependentes[i];
        }
        return null;
    }

    private String[] adicionarAoArray(String[] array, String valor) {
        String[] temp = new String[array.length + 1];
        System.arraycopy(array, 0, temp, 0, array.length);
        temp[array.length] = valor;
        return temp;
    }
}