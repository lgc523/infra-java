package dev.spider.jvm.clint;

/**
 * @author spider
 */
public class StaticA {
    static {
        try {
            Thread.sleep(1000);

        }catch (Exception e){

        }
        try {
            Class.forName("dev.spider.jvm.clint.StaticB");
        }catch (Exception e){

        }
        System.out.println("StaticB init OK");
    }
}
