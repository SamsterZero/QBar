package in.vvm.qbar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class QBarApplication {

    public static void main(String[] args) {
        SpringApplication.run(QBarApplication.class, args);
    }

}
