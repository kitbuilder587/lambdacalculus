import com.kitsoft.lambdas.BetaReduction;
import com.kitsoft.lambdas.ExpressionCalculator;
import org.junit.Assert;
import org.junit.Test;

public class BetaReductionTest {
    @Test
    public void calculateTest() throws Exception {
        BetaReduction br = new BetaReduction(new ExpressionCalculator() {
            @Override
            public String calculateExpression(String s) {
                return s.substring(1);
            }
        });
        Assert.assertEquals(br.calculate("\\ab\\c"),"c");
    }
}
