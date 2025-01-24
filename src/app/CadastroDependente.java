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
        dependenteManager.adicionarDependente(nome, parentesco);
    }
}