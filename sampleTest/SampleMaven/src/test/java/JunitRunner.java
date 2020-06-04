import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class JunitRunner {
    public static void main(String[] args) {
        for(int i=0;i<3;i++) {
            JUnitCore junit = new JUnitCore();
            junit.addListener(new TextListener(System.out));
            Result result = junit.run(new Class[]{TestEmail.class});
        }
    }
}
