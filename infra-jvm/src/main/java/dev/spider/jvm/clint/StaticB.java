package dev.spider.jvm.clint;

/**
 * @author spider
 */
public class StaticB {
    static {
        try {
            Thread.sleep(1000);

        }catch (Exception e){

        }
        try {
            Class.forName("dev.spider.jvm.clint.StaticA");
        }catch (Exception e){

        }
        System.out.println("StaticB init OK");
    }
}
