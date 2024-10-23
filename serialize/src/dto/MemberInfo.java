package dto;

import java.io.Serializable;

public class MemberInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String name;
    private final int age;

    public MemberInfo(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "dto.MemberInfo{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

}
