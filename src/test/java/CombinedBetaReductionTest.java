import com.kitsoft.lambdas.BetaReduction;
import com.kitsoft.lambdas.ExpressionCalculator;
import com.kitsoft.lambdas.Lambda;
import org.junit.Assert;
import org.junit.Test;

public class CombinedBetaReductionTest {
    @Test
    public void combineTest() throws Exception {
        BetaReduction br = new BetaReduction(new ExpressionCalculator() {
            @Override
            public String calculateExpression(String s) throws Exception {
                return new Lambda(s).calculateExpression();
            }
        });
        Assert.assertEquals(br.calculate("(\\x.x) ((\\x.x) (\\z.(\\x.x) z))"),"\\x.x");
        Assert.assertEquals(br.calculate("(\\x.\\y.x)y"),"\\y@.y");
        Assert.assertThrows(Exception.class,()->br.calculate("\\x.(x x) \\x.(x x)"));

    }
}
