package dev.spider.classfile;

/**
 * @author spider
 */
public class Simple {
    public static final int TYPE = 1;
    private int id;
    private String name;

    public void setId(int id) {
        try {
            this.id = id;
        } catch (IllegalStateException e) {
            System.out.println(e.toString());
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
