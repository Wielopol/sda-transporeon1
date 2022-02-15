package pl.sda.java.adv.school;

import java.math.BigDecimal;
import java.math.BigInteger;

public class MainBigDecimal {
    public static void main(String[] args) {

        System.out.println("\nLet's add double & BigDecimals");
        double v1 = 0.1;
        double v2 = 0.2;
        System.out.println(v1+v2);

        BigDecimal bdV1 = new BigDecimal("0.1"); //never: new BigDecimal(0.1);
        BigDecimal bdV2 = new BigDecimal("0.2"); //never: new BigDecimal(0.2);
        System.out.println(bdV1.add(bdV2));

        System.out.println("\nLet's compare BigDecimals");
        BigDecimal bd100a = new BigDecimal(100);
        BigDecimal bd100b = new BigDecimal(100);
        BigDecimal bd100c = new BigDecimal("100.00");
        System.out.printf("values: %s, %s, %s\n", bd100a, bd100b, bd100c);

        System.out.println(bd100a == bd100b);
        System.out.println(bd100a.equals(bd100b));
        System.out.println(bd100a.equals(bd100c));
        System.out.println(bd100a.compareTo(bd100c) == 0);
    }
}
