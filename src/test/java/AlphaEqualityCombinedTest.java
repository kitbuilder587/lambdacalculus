import com.kitsoft.lambdas.AlphaEquality;
import com.kitsoft.lambdas.LambdaExpression;
import com.kitsoft.lambdas.LambdaExpressionFactory;
import com.kitsoft.lambdas.LambdaFactory;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class AlphaEqualityCombinedTest {
    @Test
    public void areEqualTest() throws Exception {
        AlphaEquality ae = new AlphaEquality("\\x.x","\\xxzz.xxzz", new LambdaFactory());
        Assert.assertTrue(ae.areEqual());
        ae = new AlphaEquality("(\\x.x) ((\\x.x) (\\z. (\\x.x) z))","\\z.z (\\z.z) (\\bib. (\\z.z) bib)", new LambdaFactory());
        Assert.assertTrue(ae.areEqual());
        ae = new AlphaEquality("(\\x.x) ((\\x.x) (\\z. (\\x.x) z))","\\z.z \\z.z (\\bib. (\\z.z) bib)", new LambdaFactory());
        Assert.assertTrue(ae.areEqual());
        ae = new AlphaEquality("(\\x.x) ((\\x.x) (\\z. (\\x.x) z))","\\z.z (()() \\z.z (\\bib. (\\z.z) bib))", new LambdaFactory());
        Assert.assertTrue(ae.areEqual());
    }
}
