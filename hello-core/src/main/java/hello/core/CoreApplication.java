package hello.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoreApplication.class, args);
    }

    // 하지만, 스프링 부트에서는 수동 빈 등록과 자동 빈 등록이 충돌나면 오류가 발생하도록 기본 값을 바꾸었다.
    // The bean 'memoryMemberRepository', defined in class path resource could not be registered.
    // A bean with that name has already been defined in file and overriding is disabled.

    // application.properties에서 overriding을 true로 해주면 동작한다.
    // 기본값은 false
}
