package tst;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import app.IRPF;

public class TesteBaseCalculoImposto {

    IRPF irpf;

    @Before
    public void setup() {
        irpf = new IRPF();
    }

    @Test
    public void testBaseCalculoSemDeducoes() {
        irpf.criarRendimento("Salário", IRPF.TRIBUTAVEL, 5000);
        irpf.criarRendimento("Aluguel", IRPF.TRIBUTAVEL, 2000);
        
        float baseCalculo = irpf.calcularBaseCalculoImposto();
        assertEquals(7000, baseCalculo, 0.01);
    }

    @Test
    public void testBaseCalculoComDependentes() {
        irpf.criarRendimento("Salário", IRPF.TRIBUTAVEL, 5000);
        irpf.criarRendimento("Aluguel", IRPF.TRIBUTAVEL, 2000);

        irpf.cadastrarDependente("Filho 1", "Filho");
        irpf.cadastrarDependente("Filho 2", "Filho");

        float baseCalculo = irpf.calcularBaseCalculoImposto();
        float deducaoPorDependentes = 2 * 189.59f;
        assertEquals(7000 - deducaoPorDependentes, baseCalculo, 0.01);
    }

    @Test
    public void testBaseCalculoComContribuicoesPrevidenciarias() {
        irpf.criarRendimento("Salário", IRPF.TRIBUTAVEL, 5000);

        irpf.cadastrarContribuicaoPrevidenciaria(500);

        float baseCalculo = irpf.calcularBaseCalculoImposto();
        assertEquals(5000 - 500, baseCalculo, 0.01);
    }

    @Test
    public void testBaseCalculoComOutrasDeducoes() {
        irpf.criarRendimento("Salário", IRPF.TRIBUTAVEL, 5000);

        irpf.cadastrarDeducaoIntegral("Plano de Saúde", 1000);

        float baseCalculo = irpf.calcularBaseCalculoImposto();
        assertEquals(5000 - 1000, baseCalculo, 0.01);
    }

    @Test
    public void testBaseCalculoComTodasDeducoes() {
        irpf.criarRendimento("Salário", IRPF.TRIBUTAVEL, 5000);

        irpf.cadastrarDependente("Filho 1", "Filho");
        
        irpf.cadastrarContribuicaoPrevidenciaria(500);

        irpf.cadastrarDeducaoIntegral("Plano de Saúde", 1000);

        float deducaoPorDependentes = 1 * 189.59f;
        float baseCalculo = irpf.calcularBaseCalculoImposto();
        assertEquals(5000 - deducaoPorDependentes - 500 - 1000, baseCalculo, 0.01);
    }
}
