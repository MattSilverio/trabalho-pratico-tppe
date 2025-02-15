package app;

public class DependenteManager {
    String[] nomesDependentes;
    String[] parentescosDependentes;
    int numDependentes;

    public DependenteManager() {
        nomesDependentes = new String[0];
        parentescosDependentes = new String[0];
        numDependentes = 0;
    }

    public void cadastrarDependente(String nome, String parentesco) {
        new CadastroDependente(nome, parentesco, this).executar();
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
}
