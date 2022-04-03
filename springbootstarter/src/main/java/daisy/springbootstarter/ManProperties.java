package daisy.springbootstarter;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("man")
public class ManProperties {
    String name;
    int age;

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
}
