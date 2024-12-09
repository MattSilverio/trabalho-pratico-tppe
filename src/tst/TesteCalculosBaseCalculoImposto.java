package tst;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import app.IRPF;

@RunWith(Parameterized.class)
public class TesteCalculosBaseCalculoImposto {

	private double rendimentos;
	private double deducoes;
	private double baseEsperada;
	private IRPF calculadora;

	public TesteCalculosBaseCalculoImposto(double rendimentos, double deducoes, double baseEsperada) {
			this.rendimentos = rendimentos;
			this.deducoes = deducoes;
			this.baseEsperada = baseEsperada;
	}
	
	@Before
	public void setup() {
			calculadora = new IRPF();
			calculadora.criarRendimento("Rendimento 1", IRPF.TRIBUTAVEL, (float) rendimentos);
			calculadora.cadastrarContribuicaoPrevidenciaria((float) deducoes);
	}

	@Parameterized.Parameters
	public static Collection<Object[]> data() {
			return Arrays.asList(new Object[][] {
					{100000, 20000, 80000}, 
					{50000, 10000, 40000}, 
					{70000, 0, 70000} 
			});
	}

	@Test
	public void testCalculoBaseDeCalculo() {
			double baseCalculada = calculadora.calcularBaseCalculoImposto();
			assertEquals(baseEsperada, baseCalculada, 0.01);
	}
}
