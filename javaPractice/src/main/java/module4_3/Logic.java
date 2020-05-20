package module4_3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Logic {

    private static final Logger logger = LogManager.getLogger();

    private static void privateMethod() {
        logger.info("!!!privateMethod called!!!");
        System.out.println("!!!privateMethod called!!!");
    }

    public static void publicMethod() {
        privateMethod();
        logger.info("!!!publicMethod called!!!");
        System.out.println("!!!publicMethod called!!!");
    }
}
