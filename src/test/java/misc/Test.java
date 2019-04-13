package misc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @author Samuel Nuttall
 */
public class Test {

    private static Logger logger = LoggerFactory.getLogger(Test.class);

    public static void main(String[] args) {
        int a = 9;
        int b = 2;

        int c = a / b;
        logger.info("C: {}", c);
    }
}
