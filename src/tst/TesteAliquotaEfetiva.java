package tst;

import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.Collection;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import app.IRPF;

@RunWith(Parameterized.class)
public class TesteAliquotaEfetiva {

    private IRPF irpf;
    private float rendimento;
    private float deducao;
    private float aliquotaEsperada;

    public TesteAliquotaEfetiva(float rendimento, float deducao, float aliquotaEsperada) {
        this.rendimento = rendimento;
        this.deducao = deducao;
        this.aliquotaEsperada = aliquotaEsperada;
    }

    @Before
    public void setUp() {
        irpf = new IRPF();
        irpf.criarRendimento("Rendimento", IRPF.TRIBUTAVEL, rendimento);
        irpf.cadastrarDeducaoIntegral("Dedução", deducao);
    }

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { 2500, 416.67f, 0.64f },
                { 3333.33f, 833.33f, 1.78f },
                { 1666.67f, 2083.33f, 0.0f }, // Caso: dedução maior que rendimento
                { 0, 0, 0.0f } // Caso: sem rendimentos
        });
    }

    @Test
    public void testCalcularAliquotaEfetiva() {
        assertEquals(aliquotaEsperada, irpf.calcularAliquotaEfetiva(), 0.01);
    }
}