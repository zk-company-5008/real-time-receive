package cn.piesat.realtimereceive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class RealTimeReceiveApplication {

    public static void main(String[] args) {
        SpringApplication.run(RealTimeReceiveApplication.class, args);
    }

}
