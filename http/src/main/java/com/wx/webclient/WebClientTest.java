package com.wx.webclient;

import org.junit.Test;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * https://www.baeldung.com/?s=webClient
 * <p>
 * <S extends list>  上界      [list子类或自身,list]
 * <S super list>    下界    [list,list父类或自身]
 * https://mp.weixin.qq.com/s?__biz=MzI4Njg5MDA5NA==&mid=2247484109&idx=1&sn=ed48fa9216c260fb9b622d9f383d8c25&chksm=ebd743ccdca0cadad9e8e4a5cd9a7ce96b595ddaf6fb2e817a9a0d49d4d54c50bb93a97e56eb&scene=21###wechat_redirect

 * 可在编译时编写
 * 采用建造者模式   ==> 属于  创建型模式
 * 将建造者的属性传递给真正的对象
 *
 * @author wxli
 * @date 2021/7/14 23:56
 */
public class WebClientTest {
    @Test
    public void test() {
        WebClient webClient = WebClient.create();
    }

}
