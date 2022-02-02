import com.kitsoft.lambdas.Macros;
import org.junit.Assert;
import org.junit.Test;

public class MacrosTest {

    @Test
    public void applyMacrosTest() throws Exception {
        Macros macros = new Macros();
        String m = "#M := \\x.x";
        macros.addMacro(m);
        macros.addMacro("#S := M x");
        Assert.assertEquals(macros.applyMacros("M x"),"\\x.x x");
        Assert.assertEquals(macros.applyMacros("S"),"\\x.x x");
        Assert.assertEquals(macros.applyMacros("x"),"x");
    }
}
