package app;

public class IRPF {

	public static final boolean TRIBUTAVEL = true;
	public static final boolean NAOTRIBUTAVEL = false;

	private String[] nomeRendimento;
	private boolean[] rendimentoTributavel;
	private float[] valorRendimento;
	private int numRendimentos;
	private float totalRendimentos;

	private int numContribuicaoPrevidenciaria;
	private float totalContribuicaoPrevidenciaria;

	private float totalPensaoAlimenticia;

	private final DependenteManager dependenteManager;
	private final DeducaoManager deducaoManager;

	public IRPF() {
		nomeRendimento = new String[0];
		rendimentoTributavel = new boolean[0];
		valorRendimento = new float[0];

		numContribuicaoPrevidenciaria = 0;
		totalContribuicaoPrevidenciaria = 0f;

		totalPensaoAlimenticia = 0f;

		dependenteManager = new DependenteManager();
		deducaoManager = new DeducaoManager();
	}

	/**
	 * Cadastra um rendimento na base do contribuinte, informando o nome do
	 * rendimento, seu valor e se ele é tributável ou não.
	 * @param nome nome do rendimento a ser cadastrado
	 * @param tributavel true caso seja tributável, false caso contrário
	 * @param valor valor do rendimento a ser cadastrado
	 */
	public void criarRendimento(String nome, boolean tributavel, float valor) {
		nomeRendimento = adicionarAoArray(nomeRendimento, nome);
		rendimentoTributavel = adicionarAoArray(rendimentoTributavel, tributavel);
		valorRendimento = adicionarAoArray(valorRendimento, valor);

		this.numRendimentos += 1;
		this.totalRendimentos += valor;
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
		for (int i = 0; i < rendimentoTributavel.length; i++) {
			if (rendimentoTributavel[i]) {
				totalRendimentosTributaveis += valorRendimento[i];
			}
		}
		return totalRendimentosTributaveis;
	}

	/**
	 * Método para realizar o cadastro de um dependente, informando seu grau
	 * de parentesco
	 * @param nome Nome do dependente
	 * @param parentesco Grau de parentesco
	 */
	public void cadastrarDependente(String nome, String parentesco) {
		dependenteManager.cadastrarDependente(nome, parentesco);
	}

	/**
	 * Método que retorna o numero de dependentes do contribuinte
	 * @return numero de dependentes
	 */
	public int getNumDependentes() {
		return dependenteManager.getNumDependentes();
	}

	/**
	 * Return o valor do total de deduções para o contribuinte
	 * @return valor total de deducoes
	 */
	public float getDeducao() {
		float total = 0;
		int numDependentes = dependenteManager.getNumDependentes();

		total += numDependentes * 189.59f; // Dedução por dependente
		total += totalContribuicaoPrevidenciaria; // Dedução por contribuição previdenciária
		total += totalPensaoAlimenticia; // Dedução por pensão alimentícia
		total += deducaoManager.getTotalOutrasDeducoes(); // Dedução por outras deduções

		return total;
	}

	/**
	 * Cadastra um valor de contribuição previdenciária oficial
	 * @param contribuicao valor da contribuição previdenciária oficial
	 */
	public void cadastrarContribuicaoPrevidenciaria(float contribuicao) {
		numContribuicaoPrevidenciaria++;
		totalContribuicaoPrevidenciaria += contribuicao;
	}

	/**
	 * Retorna o numero total de contribuições realizadas como contribuicao
	 * previdenciaria oficial
	 * @return numero de contribuições realizadas
	 */
	public int getNumContribuicoesPrevidenciarias() {
		return numContribuicaoPrevidenciaria;
	}

	/**
	 * Retorna o valor total de contribuições oficiais realizadas
	 * @return valor total de contribuições oficiais
	 */
	public float getTotalContribuicoesPrevidenciarias() {
		return totalContribuicaoPrevidenciaria;
	}

	/**
	 * Realiza busca do dependente no cadastro do contribuinte
	 * @param nome nome do dependente que está sendo pesquisado
	 * @return nome do dependente ou null, caso nao conste na lista de dependentes
	 */
	public String getDependente(String nome) {
		return dependenteManager.getDependente(nome);
	}

	/**
	 * Método que retorna o grau de parentesco para um dado dependente, caso ele
	 * conste na lista de dependentes
	 * @param dependente nome do dependente
	 * @return grau de parentesco, nulo caso nao exista o dependente
	 */
	public String getParentesco(String dependente) {
		return dependenteManager.getParentesco(dependente);
	}

	/**
	 * Realiza o cadastro de uma pensao alimenticia para um dos dependentes do
	 * contribuinte, caso ele seja um filho ou alimentando.
	 * @param dependente nome do dependente
	 * @param valor valor da pensao alimenticia
	 */
	public void cadastrarPensaoAlimenticia(String dependente, float valor) {
		String parentesco = getParentesco(dependente);
		if (parentesco.toLowerCase().contains("filh") ||
				parentesco.toLowerCase().contains("alimentand")) {
			totalPensaoAlimenticia += valor;
		}
	}

	/**
	 * Retorna o valor total pago em pensões alimentícias pelo contribuinte.
	 * @return valor total de pensoes alimenticias
	 */
	public float getTotalPensaoAlimenticia() {
		return totalPensaoAlimenticia;
	}

	/**
	 * Metodo para cadastrar deduções integrais para o contribuinte. Para cada
	 * dedução é informado seu nome e valor.
	 * @param nome nome da deducao
	 * @param valorDeducao valor da deducao
	 */
	public void cadastrarDeducaoIntegral(String nome, float valorDeducao) {
		deducaoManager.cadastrarDeducaoIntegral(nome, valorDeducao);
	}

	/**
	 * Método para pesquisar uma deducao pelo seu nome.
	 * @param nome nome da deducao a ser pesquisada
	 * @return nome da deducao, ou null caso na esteja cadastrada
	 */
	public String getOutrasDeducoes(String nome) {
		return deducaoManager.getOutrasDeducoes(nome);
	}

	/**
	 * Obtem o valor da deducao à partir de seu nome
	 * @param nome nome da deducao para a qual se busca seu valor
	 * @return valor da deducao
	 */
	public float getDeducao(String nome) {
		return deducaoManager.getDeducao(nome);
	}

	/**
	 * Obtem o valor total de todas as deduções que nao sao do tipo
	 * contribuicoes previdenciarias ou por dependentes
	 * @return valor total das outras deducoes
	 */
	public float getTotalOutrasDeducoes() {
		return deducaoManager.getTotalOutrasDeducoes();
	}

	/**
	 * Método que calcula a base de cálculo do imposto, considerando rendimentos tributáveis e deduções.
	 * @return a base de cálculo para o imposto
	 */
	public float calcularBaseCalculoImposto() {
		return getTotalRendimentosTributaveis() - getDeducao();
	}

	private static final float[] FAIXAS = {1903.98f, 2826.65f, 3751.05f, 4664.68f};
	private static final float[] ALIQUOTAS = {0.075f, 0.15f, 0.225f, 0.275f};

	/**
	 * Calcula o imposto devido com base nas faixas de imposto.
	 * @return valor do imposto devido
	 */
	public float calcularImposto() {
		float baseCalculo = calcularBaseCalculoImposto();

		if (baseCalculo <= FAIXAS[0]) {
			return 0;
		}

		float imposto = 0;
		for (int i = FAIXAS.length - 1; i >= 0; i--) {
			if (baseCalculo > FAIXAS[i]) {
				imposto += (baseCalculo - FAIXAS[i]) * ALIQUOTAS[i];
				baseCalculo = FAIXAS[i];
			}
		}

		return imposto;
	}

	/**
	 * Calcula a alíquota efetiva do imposto de renda.
	 * A alíquota efetiva é a porcentagem do imposto devido em relação à base de cálculo.
	 * @return alíquota efetiva em percentual
	 */
	public float calcularAliquotaEfetiva() {
		float baseCalculo = calcularBaseCalculoImposto();
		if (baseCalculo <= 0) {
			return 0; // Se a base for zero ou negativa, a alíquota é zero
		}

		float impostoDevido = calcularImposto();
		return (impostoDevido / baseCalculo) * 100;
	}

	// Métodos auxiliares para adicionar elementos aos arrays
	private String[] adicionarAoArray(String[] array, String valor) {
		String[] temp = new String[array.length + 1];
		System.arraycopy(array, 0, temp, 0, array.length);
		temp[array.length] = valor;
		return temp;
	}

	private boolean[] adicionarAoArray(boolean[] array, boolean valor) {
		boolean[] temp = new boolean[array.length + 1];
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