import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDateTime;

public class TestEmail {

    private SimpleClass simpleClass;
    private LocalDateTime localDateTime;
    private static int emailCount=0;

    @BeforeClass
    public static void beforeClass(){
        emailCount=Integer.parseInt(System.getProperty("com.sbg.emailcount"));
    }

    @Before
    public void setup(){
        simpleClass=new SimpleClass();
        localDateTime=LocalDateTime.now();
    }

    public String getEmail(){
        StringBuilder sb=new StringBuilder();
        sb.append("usqbo");
        sb.append(localDateTime.toString());
        sb.append("_");
        sb.append(emailCount++);
        sb.append("_iamtestpass@mailinator.com");
        return sb.toString();
    }

    @AfterClass
    public static void afterClass(){
        System.setProperty("com.sbg.emailcount", String.valueOf(emailCount));
    }

    @Test
    public void testMethodA(){
        simpleClass.methodA(getEmail());
    }

    @Test
    public void testMethodB(){
        simpleClass.methodB(getEmail());
    }
}
