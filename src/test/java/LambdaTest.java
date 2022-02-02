import com.kitsoft.lambdas.Lambda;
import org.junit.Assert;
import org.junit.Test;

public class LambdaTest {

    @Test
    public void parseExpressionTest() throws Exception {
        Lambda lambda = new Lambda("\\x.x 3");
        Assert.assertEquals(lambda.getVar(),"x");
        Assert.assertEquals(lambda.getTerm(),"x");
        Assert.assertEquals(lambda.getArgument(),"3");
        lambda = new Lambda("\\x.(x^2    ()  +2x + 1) 3");
        Assert.assertEquals(lambda.getVar(),"x");
        Assert.assertEquals(lambda.getTerm(),"(x^2    ()  +2x + 1)");
        Assert.assertEquals(lambda.getArgument(),"3");
    }

    @Test
    public void calculateExpressionTest() throws Exception {
        Lambda lambda = new Lambda("\\x.x 3");
        Assert.assertEquals(lambda.calculateExpression(),"3");
        lambda = new Lambda("\\x.(2x  3xx) 3");
        Assert.assertEquals(lambda.calculateExpression(),"23  333");
        lambda = new Lambda("\\x.(x x) \\x.(x x)");
        Assert.assertEquals(lambda.calculateExpression(),"\\x.(x x) \\x.(x x)");
        lambda = new Lambda("(\\x.x) ((\\x.x) (\\z. (\\x.x) z))");
        lambda = new Lambda(lambda.calculateExpression());
        Assert.assertEquals(lambda.calculateExpression(),"\\z. (\\x.x) z");
    }
}
