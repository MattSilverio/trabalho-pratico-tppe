package tst;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import app.IRPF;

@RunWith(Parameterized.class)
public class TesteCalculoImposto {

    private IRPF irpf;
    private final float rendimento;
    private final float esperado;

    // Construtor para receber os parâmetros
    public TesteCalculoImposto(float rendimento, float esperado) {
        this.rendimento = rendimento;
        this.esperado = esperado;
    }

    @Before
    public void setup() {
        irpf = new IRPF();
        irpf.criarRendimento("Salário", IRPF.TRIBUTAVEL, rendimento);
    }

    // Parâmetros para os testes
    @Parameterized.Parameters
    public static Collection<Object[]> parametros() {
        return Arrays.asList(new Object[][] {
                {0, 0},
                { 1900.0f, 0.0f },                           // Isento
                { 2500.0f, (2500.0f - 1903.98f) * 0.075f },  // Faixa 1
                { 3500.0f, (2826.65f - 1903.98f) * 0.075f + (3500.0f - 2826.65f) * 0.15f }, // Faixa 2
                { 4000.0f, (2826.65f - 1903.98f) * 0.075f + (3751.05f - 2826.65f) * 0.15f + (4000.0f - 3751.05f) * 0.225f }, // Faixa 3
                { 6000.0f, (2826.65f - 1903.98f) * 0.075f + (3751.05f - 2826.65f) * 0.15f + (4664.68f - 3751.05f) * 0.225f + (6000.0f - 4664.68f) * 0.275f } // Faixa 4
        });
    }

    @Test
    public void testCalculoImposto() {
        float imposto = irpf.calcularImposto();
        assertEquals(esperado, imposto, 0.01f);
    }
}
