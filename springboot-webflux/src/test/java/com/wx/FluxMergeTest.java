package com.wx;

import org.junit.Before;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;

/**
 * @author wxli
 * @date 2021/7/21 0:01
 */
public class FluxMergeTest {

    Flux<Integer> evenNumbers = Flux
            .range(1, 5)
            .filter(x -> x % 2 == 0); // i.e. 2, 4

    Flux<Integer> oddNumbers = Flux
            .range(1, 5)
            .filter(x -> x % 2 > 0);  // ie. 1, 3, 5

    /**
     * 多种组合流的方法。
     * <p>
     * concat、concatWith、merge、zip、combineLatest
     */
    @Test
    public void test8() {
        Flux<Integer> evenNumbers = Flux
                .range(1, 5)
                .filter(x -> x % 2 == 0); // i.e. 2, 4

        Flux<Integer> oddNumbers = Flux
                .range(1, 5)
                .filter(x -> x % 2 > 0);  // ie. 1, 3, 5

        evenNumbers.subscribe(System.out::println);
        oddNumbers.subscribe(System.out::println);
    }

    /**
     * concat
     * <p>
     * 顺序订阅第一个源然后等待它完成再订阅下一个源来实现的，
     * 依此类推，直到最后一个源完成。
     * 任何错误都会立即中断序列并转发到下游。
     */
    @Test
    public void givenFluxes_whenConcatIsInvoked_thenConcat() {
        Flux<Integer> fluxOfIntegers = Flux.concat(
                evenNumbers,
                oddNumbers);

        StepVerifier.create(fluxOfIntegers)
                .expectNext(2)
                .expectNext(4)
                .expectNext(1)
                .expectNext(3)
                .expectNext(5)
                .expectComplete()
                .verify();
    }

    /**
     * 参见concat
     */
    @Test
    public void givenFluxes_whenConcatWithIsInvoked_thenConcatWith() {
        Flux<Integer> fluxOfIntegers = evenNumbers.concatWith(oddNumbers);

        StepVerifier.create(fluxOfIntegers)
                .expectNext(2)
                .expectNext(4)
                .expectNext(1)
                .expectNext(3)
                .expectNext(5)
                .expectComplete()
                .verify();
        // same stepVerifier as in the concat example above
    }


    /**
     * combineLatest()
     * <p>
     * Flux 静态方法combineLatest将生成由来自每个 Publisher 源的最新发布值的组合提供的数据。
     * 函数combineLatest使用了evenNumbers ( 4 )的最新元素和oddNumbers (1,3,5)的元素应用了函数“a + b” ，
     * 从而生成了序列5,7,9。
     */
    @Test
    public void givenFluxes_whenCombineLatestIsInvoked_thenCombineLatest() {
        Flux<Integer> fluxOfIntegers = Flux.combineLatest(
                evenNumbers,
                oddNumbers,
                (a, b) -> a + b);

        fluxOfIntegers.subscribe(System.out::println);

        StepVerifier.create(fluxOfIntegers)
                .expectNext(5) // 4 + 1
                .expectNext(7) // 4 + 3
                .expectNext(9) // 4 + 5
                .expectComplete()
                .verify();
    }

    /**
     * merge()
     * <p>
     * 与concat（懒惰订阅）相反，源是热切订阅的。
     */
    @Test
    public void givenFluxes_whenMergeIsInvoked_thenMerge() {
        Flux<Integer> fluxOfIntegers = Flux.merge(
                evenNumbers,
                oddNumbers);

        StepVerifier.create(fluxOfIntegers)
                .expectNext(2)
                .expectNext(4)
                .expectNext(1)
                .expectNext(3)
                .expectNext(5)
                .expectComplete()
                .verify();
    }

    /**
     * merge()
     * 如果我们在 Publishers 的元素之间插入延迟，我们可以看到合并函数的不同结果
     */
    @Test
    public void givenFluxes_whenMergeWithDelayedElementsIsInvoked_thenMergeWithDelayedElements() {
        Flux<Integer> fluxOfIntegers = Flux.merge(
                evenNumbers.delayElements(Duration.ofMillis(800L)),
                oddNumbers.delayElements(Duration.ofMillis(300L)));

        StepVerifier.create(fluxOfIntegers)
                .expectNext(1)
                .expectNext(2)
                .expectNext(3)
                .expectNext(5)
                .expectNext(4)
                .expectComplete()
                .verify();
    }

    /**
     * mergeSequential()
     * <p>
     * 所述mergeSequential从方法合并数据发布序列以阵列设置成有序合并序列。
     * 与concat不同，源代码被热切地订阅。
     * 此外，与merge不同的是，它们发出的值按订阅顺序合并到最终序列中：
     */
    @Test
    public void testMergeSequential() {
        Flux<Integer> fluxOfIntegers = Flux.mergeSequential(
                evenNumbers,
                oddNumbers);

        StepVerifier.create(fluxOfIntegers)
                .expectNext(2)
                .expectNext(4)
                .expectNext(1)
                .expectNext(3)
                .expectNext(5)
                .expectComplete()
                .verify();
    }

    /**
     * mergeDelayError()
     *
     * mergeDelayError合并数组中包含的从Publisher序列数据转换成交织合并序列。
     * 与concat不同，源代码被热切地订阅。
     * 静态合并方法的这种变体将延迟任何错误，直到处理完合并积压的其余部分之后。
     */
    @Test
    public void givenFluxes_whenMergeWithDelayedElementsIsInvoked_thenMergeWithDelayedElements2() {
        Flux<Integer> fluxOfIntegers = Flux.mergeDelayError(1,
                evenNumbers.delayElements(Duration.ofMillis(800L)),
                oddNumbers.delayElements(Duration.ofMillis(300L)));

        StepVerifier.create(fluxOfIntegers)
                .expectNext(1)
                .expectNext(2)
                .expectNext(3)
                .expectNext(5)
                .expectNext(4)
                .expectComplete()
                .verify();
    }

    /**
     * 参考merge()
     */
    @Test
    public void givenFluxes_whenMergeWithIsInvoked_thenMergeWith() {
        Flux<Integer> fluxOfIntegers = evenNumbers.mergeWith(oddNumbers);

        StepVerifier.create(fluxOfIntegers)
                .expectNext(2)
                .expectNext(4)
                .expectNext(1)
                .expectNext(3)
                .expectNext(5)
                .expectComplete()
                .verify();
    }

    /**
     * zip()
     *
     * 等待所有源发出一个元素并将这些元素组合成一个输出值
     */
    @Test
    public void givenFluxes_whenZipIsInvoked_thenZip() {
        Flux<Integer> fluxOfIntegers = Flux.zip(
                evenNumbers,
                oddNumbers,
                (a, b) -> a + b);

        StepVerifier.create(fluxOfIntegers)
                .expectNext(3) // 2 + 1
                .expectNext(7) // 4 + 3
                .expectComplete()
                .verify();
    }

}
