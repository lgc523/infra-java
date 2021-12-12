package dev.spider.jvm.clint;

/**
 * @author spider
 */
public class StaticDeadLockMain extends Thread{
    private char flag;
    public StaticDeadLockMain(char flag){
        this.flag=flag;
        this.setName("Thread"+flag);
    }

    @Override
    public void run() {
        try {
            Class.forName("dev.spider.jvm.clint.Static"+flag);
        }catch (Exception e){

        }
        System.out.println(getName()+" over");
    }
}
