package cn.tnar.msf.cm4.msg.dto;

import cn.tnar.msf.cm4.msg.dto.entity.CustomizeException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

/**
 * @author wxli
 * @date 2021/7/7 21:09
 */
@Slf4j
public class MonoTest {
    @Test
    public void test() {
        Mono.just(1)
                .doOnSuccess(a -> {                                     //成功后执行
                    System.out.println(a);
                    throw new RuntimeException("nihao");
                })
                //
                .onErrorResume(e -> {                                   //处理异常，发生错误后恢复
                    if (e instanceof CustomizeException) {
                        return Mono.just(3);
                    } else if (e instanceof RuntimeException) {
                        return Mono.just(4);
                    } else {
                        return Mono.just(5);
                    }
                })
                .doOnSuccess(b -> {
                    System.out.println(b);
                    throw new RuntimeException("nihao");
                })
                .doOnError(Throwable::printStackTrace)                  //错误后打印
                .onErrorReturn(2)                                       //错误后返回
                .subscribe(System.out::println, System.err::println);
    }

    //
    @Test
    public void test2() {
        Mono.just(false)
                .doOnSuccess(a -> {
                    if (a) {
                        System.out.println(123);
                    } else {
                        throw new CustomizeException("密钥错误");
                    }
                })
                .zipWith(Mono.just(2)                                   //合并多个mono，顺序执行
                        .doOnSuccess(c -> {
                            System.out.println(c);
                            throw new CustomizeException("nihao");
                        }))
                .subscribe(System.out::println);
    }

    /**
     * 发生错误后继续
     */
    @Test
    public void test3() {
        Mono.just(1)
                .doOnSuccess(a -> {
                    throw new CustomizeException("nihao");
                })
                .onErrorContinue((throwable, o) -> {
                    throwable.printStackTrace();
                    System.out.println(o);
                }).subscribe(System.out::println, System.err::println);
    }

    /**
     * concatWith()   mono  转   flux
     * 后面的发布者中的  元组--->  得继承  前面的元组   否则不满足
     */
    @Test
    public void test4() {
        Mono.just(1)
                .concatWith(Mono.just(2))
                .subscribe(System.out::println);
    }

    /**
     * mono1抛异常之后 mono2 不执行
     * concatWith() 两个mono顺序执行，
     */
    @Test
    public void test5() {
        Mono.just(1)
                .doOnSuccess(integer -> {
                    if (integer > 0) {
                        try {
                            Thread.sleep(10000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        throw new RuntimeException("yichang");
                    }
                })
                .concatWith(Mono.just(2).doOnSuccess(integer -> {
//                    if (integer > 0) {
//                        throw new RuntimeException("yichang");
//                    }
                }))
                .subscribe(System.out::println);

    }

    @Test
    public void test6() {
        Flux<Integer> integerFlux = Flux.just(1, 2)
                .concatWith(Mono.just(3).doOnSuccess(integer -> {
                }));

        integerFlux.flatMap(integer -> {
            //System.out.println(integer);   将一个个data拿出来使用
            return Flux.just(integer);
        })
                .subscribe(System.out::println);
        StepVerifier.create(integerFlux)
                .expectNext(1, 2, 3)
                .expectComplete()
                .verify();

    }

    /**
     * mono1抛异常之后 mono2 不执行
     * concatWith() 两个mono顺序执行，
     */
    @Test
    public void test7() {
        Mono.just(1)
                .doOnSuccess(integer -> {
                    if (integer>0){
                        throw new RuntimeException("yichang");
                    }
                })
                .zipWith(Mono.justOrEmpty(2)
                        .doOnSuccess(integer -> {
                                    if (integer > 0) {
                                        throw new RuntimeException("yichang");
                                    }
                                }
                        )
                )
                .doOnError(Throwable::printStackTrace)
                .subscribe(objects -> objects.forEach(System.out::println));

    }

}
