package cn.tnar.msf.cm4.msg.dto.springhttp;

import org.junit.Test;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * https://www.baeldung.com/?s=restTemplate
 * <p>
 * 基本记录了restTemplate 操作
 * <p>
 * 采用模板模式封装了算法
 * <p>
 * 可以将模板的httpclient 通过 factory   替换成其他的http工具
 * <p>
 * 看源码结构
 *
 * @author wxli
 * @date 2021/7/14 23:17
 */
public class RestTemplateTest {
    @Test
    public void test() {
        RestTemplate restTemplate = new RestTemplate();
    }

}
