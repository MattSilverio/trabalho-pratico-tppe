package tst;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import app.IRPF;

public class TesteCalculoImposto {

    IRPF irpf;

    @Before
    public void setup() {
        irpf = new IRPF();
    }

    @Test
    public void testCalculoImpostoFaixas() {
        irpf.criarRendimento("Salário", IRPF.TRIBUTAVEL, 5000.0f);
        irpf.criarRendimento("Investimentos", IRPF.TRIBUTAVEL, 2000.0f);

        irpf.cadastrarContribuicaoPrevidenciaria(500.0f);
        irpf.cadastrarDependente("Filho", "Filho"); 

        float baseCalculo = irpf.calcularBaseCalculoImposto();
        assertEquals(6310.41f, baseCalculo, 0.01f);

        float imposto = calcularImposto(baseCalculo);
        assertEquals(1420.08f, imposto, 0.01f);
    }

    private float calcularImposto(float baseCalculo) {
        float imposto = 0;

        if (baseCalculo <= 22847.76f) {
            return 0;
        }

        if (baseCalculo > 22847.76f && baseCalculo <= 33919.80f) {
            imposto += (baseCalculo - 22847.76f) * 0.075f;
        } else {
            imposto += (33919.80f - 22847.76f) * 0.075f;
        }

        if (baseCalculo > 33919.80f && baseCalculo <= 45012.60f) {
            imposto += (baseCalculo - 33919.80f) * 0.15f;
        } else if (baseCalculo > 45012.60f) {
            imposto += (45012.60f - 33919.80f) * 0.15f;
        }

        if (baseCalculo > 45012.60f && baseCalculo <= 55976.16f) {
            imposto += (baseCalculo - 45012.60f) * 0.225f;
        } else if (baseCalculo > 55976.16f) {
            imposto += (55976.16f - 45012.60f) * 0.225f;
        }

        if (baseCalculo > 55976.16f) {
            imposto += (baseCalculo - 55976.16f) * 0.275f;
        }

        return imposto;
    }
}
