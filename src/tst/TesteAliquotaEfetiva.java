package tst;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import app.IRPF;

public class TesteAliquotaEfetiva {

    IRPF irpf;

    @Before
    public void setup() {
        irpf = new IRPF();
    }

    @Test
    public void testAliquotaEfetivaSemDeducoes() {
        irpf.criarRendimento("Salário", IRPF.TRIBUTAVEL, 5000);
        irpf.criarRendimento("Bolsas", IRPF.NAOTRIBUTAVEL, 2000);

        float baseCalculo = irpf.calcularBaseCalculoImposto();
        float impostoDevido = calcularImposto(baseCalculo);
        float aliquotaEfetiva = impostoDevido / irpf.getTotalRendimentosTributaveis();

        assertEquals(0.15, aliquotaEfetiva, 0.01); 
    }

    @Test
    public void testAliquotaEfetivaComDeducoes() {
        irpf.criarRendimento("Salário", IRPF.TRIBUTAVEL, 10000);
        irpf.cadastrarContribuicaoPrevidenciaria(1500);
        irpf.cadastrarDependente("Miguel", "Filho");

        float baseCalculo = irpf.calcularBaseCalculoImposto();
        float impostoDevido = calcularImposto(baseCalculo);
        float aliquotaEfetiva = impostoDevido / irpf.getTotalRendimentosTributaveis();

        assertEquals(0.10, aliquotaEfetiva, 0.01); 
    }

    @Test
    public void testAliquotaEfetivaComOutrasDeducoes() {
        irpf.criarRendimento("Pro-labore", IRPF.TRIBUTAVEL, 8000);
        irpf.cadastrarDeducaoIntegral("Doações", 500);

        float baseCalculo = irpf.calcularBaseCalculoImposto();
        float impostoDevido = calcularImposto(baseCalculo);
        float aliquotaEfetiva = impostoDevido / irpf.getTotalRendimentosTributaveis();

        assertEquals(0.12, aliquotaEfetiva, 0.01);
    }

    private float calcularImposto(float baseCalculo) {
        if (baseCalculo <= 1903.98) {
            return 0;
        } else if (baseCalculo <= 2826.65) {
            return (baseCalculo - 1903.98f) * 0.075f;
        } else if (baseCalculo <= 3751.05) {
            return (baseCalculo - 2826.65f) * 0.15f + 69.20f;
        } else if (baseCalculo <= 4664.68) {
            return (baseCalculo - 3751.05f) * 0.225f + 207.86f;
        } else {
            return (baseCalculo - 4664.68f) * 0.275f + 413.42f;
        }
    }
}
