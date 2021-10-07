package cn.tnar.msf.cm4.msg.dto;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.function.Function;

/**
 * @author wxli
 * @date 2021/6/25 16:30
 */
@Slf4j
public class FluxTest {
    /**
     * flux  基本构造器使用
     * @throws InterruptedException
     */
    @Test
    public void fluxConstructorTest() throws InterruptedException {
        Flux.just("hello", "world").subscribe(System.out::println);
        Flux.fromArray(new Integer[]{1, 2, 3}).subscribe(System.out::println);
        Flux.empty().subscribe(System.out::println);
        Flux.range(1, 5).subscribe(System.out::println);
        Flux.interval(Duration.of(2, ChronoUnit.SECONDS)).subscribe(System.out::println);
        Thread.sleep(10000);

        Flux.generate(sink -> {
            sink.next("Echo");
            sink.complete();
        }).subscribe(System.out::println);

        Flux.create(sink -> {
            for (char i = 'a'; i <= 'z'; i++) {
                sink.next(i);
            }
            sink.complete();
        }).subscribe(System.out::print);
    }

    /**
     * flux   流操作
     */
    @Test
    public void fluxStream() {
        Flux.range(1, 100).buffer(20).subscribe(System.out::println);

        Flux.interval(Duration.of(0, ChronoUnit.SECONDS),
                Duration.of(1, ChronoUnit.SECONDS))
                .buffer(Duration.of(3, ChronoUnit.SECONDS))
                .take(2)
                .toStream()
                .forEach(System.out::println);

        Flux.range(1, 10).bufferUntil(i -> i % 2 == 0).subscribe(System.out::println);
    }
    @Test
    public void fluxException(){

        //将正常消息和错误消息分别打印
        log.info("将正常消息和错误消息分别打印");
        Flux.just(1, 2)
                .concatWith(Mono.error(new IllegalStateException()))
                .subscribe(System.out::println, System.err::println);

        //当产生错误时默认返回0
        log.info("当产生错误时默认返回0");
        Flux.just(1, 2)
                .concatWith(Mono.error(new IllegalStateException()))
                .onErrorReturn(0)
                .subscribe(System.out::println);

        //自定义异常时的处理
        log.info("自定义异常时的处理");
        Flux.just(1, 2)
                .concatWith(Mono.error(new IllegalArgumentException()))
                .onErrorResume(e -> {
                    if (e instanceof IllegalStateException) {
                        return Mono.just(0);
                    } else if (e instanceof IllegalArgumentException) {
                        return Mono.just(-1);
                    }
                    return Mono.empty();
                })
                .subscribe(System.out::println);

        //当产生错误时重试
        log.info("当产生错误时重试");
        Flux.just(1, 2)
                .concatWith(Mono.error(new IllegalStateException()))
                .retry(1)
                .subscribe(System.out::println);
    }

    /**
     * flux  map()
     */
    @Test
    public void test6(){
        Function<String,String> mapper= String::toLowerCase;

        Flux<String> inFlux = Flux.just("baeldung", ".", "com");
        Flux<String> outFlux = inFlux.map(mapper);
        outFlux.subscribe(System.out::println);

        StepVerifier.create(outFlux)
                .expectNext("BAELDUNG", ".", "COM")
                .expectComplete()
                .verify();
    }

    /**
     * flux flatMap()
     */
    @Test
    public void test7(){
        Function<String, Publisher<String>> mapper= s -> Flux.just(s.toUpperCase().split(""));

        Flux<String> inFlux = Flux.just("baeldung", ".", "com");
        Flux<String> outFlux = inFlux.flatMap(mapper);

        ArrayList<String> output = new ArrayList<>();
        outFlux.subscribe(output::add);
        System.out.println(output);

        StepVerifier.create(outFlux)
                .expectNext("BAELDUNG", ".", "COM")
                .expectComplete()
                .verify();
    }





}
