package com.reactive.example;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static reactor.core.publisher.Mono.fromSupplier;
import static reactor.core.publisher.Mono.just;

public class DefaultOrSwitchIfEmpty {

    @Test
    public void FluxAfterEmpty2() {
        Flux.just(2, 3)
            .doOnNext(t -> System.out.println(" First Next " + t)) //2, 3
            .filter(t -> t > 2)
            .doOnNext(t -> System.out.println(" Next after filter " + t)) // 3
            .switchIfEmpty(Flux.just(5, 6)) // X
            .doOnNext(t -> System.out.println(" Second Next " + t)) // 3
            .filter(t -> t == 3 )
            .doOnNext(t -> System.out.println(" Next after Second filter " + t)) // 3
            .subscribe();
    }

    @Test
    public void FluxAfterEmpty() {
         Flux.just(2, 3)
            .doOnNext(t -> System.out.println(" First Next " + t)) //2, 3
            .filter(t -> t > 4)
            .doOnNext(t -> System.out.println(" Next after filter " + t)) //
            .switchIfEmpty(Flux.just(5, 6))
            .doOnNext(t -> System.out.println(" Second Next " + t)) //5, 6
            .filter(t -> t > 4)
            .doOnNext(t -> System.out.println(" Next after Second filter " + t)) //5, 6
            .subscribe();
    }

    @Test
    public void MonoAfterEmpty() {
        just(2)
            .doOnNext(t -> System.out.println(" First Next " + t)) // 2
            .filter(t -> t == 3)
            .doOnNext(t -> System.out.println(" Second Next " + t)) //
            .defaultIfEmpty(3)
            .filter(t -> t == 3)
            .doOnNext(t -> System.out.println(" Third Next " + t)) // 3
            .switchIfEmpty(just(4))
            .filter(t -> t == 3)
            .doOnNext(t -> System.out.println(" Fourth Next " + t)) // 3
            .subscribe();
    }

    @Test
    public void MonoAfterEmpty1() {
        just(2)
                .filter(t -> t == 2) //2
                .doOnNext(t -> System.out.println(" Second Next " + t)) // 2
                .defaultIfEmpty(3)
                .doOnNext(t -> System.out.println(" Third Next " + t)) // 2
                .subscribe();
    }
}