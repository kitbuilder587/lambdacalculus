import com.kitsoft.lambdas.AlphaEquality;
import com.kitsoft.lambdas.LambdaExpression;
import com.kitsoft.lambdas.LambdaExpressionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class AlphaEqualityTest {
    @Test
    public void areEqualTest() throws Exception {
        LambdaExpressionFactory lefMock = Mockito.mock(LambdaExpressionFactory.class);
        LambdaExpression expA = Mockito.mock(LambdaExpression.class);
        expA.var = "x";
        expA.term = "x";
        Mockito.when(expA.renameCollisions("x","@")).thenReturn("@");
        LambdaExpression expB = Mockito.mock(LambdaExpression.class);
        expB.var = "xzxz";
        expB.term = "xzxz";
        Mockito.when(expB.renameCollisions("xzxz","@")).thenReturn("@");
        Mockito.when(lefMock.getInstance("\\x.x")).thenReturn(expA);
        Mockito.when(lefMock.getInstance("\\xzxz.xzxz")).thenReturn(expB);
        AlphaEquality equality = new AlphaEquality("\\x.x","\\xzxz.xzxz",lefMock);
        Assert.assertTrue(equality.areEqual());
    }
}
