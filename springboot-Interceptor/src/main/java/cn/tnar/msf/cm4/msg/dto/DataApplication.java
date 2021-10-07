package cn.tnar.msf.cm4.msg.dto;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wx.demo.dao")
public class DataApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataApplication.class, args);
    }


}