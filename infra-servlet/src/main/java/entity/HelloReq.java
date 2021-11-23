package entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * @author spider
 */
public class HelloReq implements Serializable {
    private String name;
    private int age;
    private String city;
    private String email;
    private List<String> programming;
    private List<String> middleAware;

    public HelloReq() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HelloReq helloReq = (HelloReq) o;
        return age == helloReq.age && Objects.equals(name, helloReq.name) && Objects.equals(city, helloReq.city) && Objects.equals(email, helloReq.email) && Objects.equals(programming, helloReq.programming) && Objects.equals(middleAware, helloReq.middleAware);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, city, email, programming, middleAware);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getProgramming() {
        return programming;
    }

    public void setProgramming(List<String> programming) {
        this.programming = programming;
    }

    public List<String> getMiddleAware() {
        return middleAware;
    }

    public void setMiddleAware(List<String> middleAware) {
        this.middleAware = middleAware;
    }


}
