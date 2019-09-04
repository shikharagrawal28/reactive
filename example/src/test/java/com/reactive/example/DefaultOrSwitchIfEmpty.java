package com.reactive.example;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static reactor.core.publisher.Mono.fromSupplier;
import static reactor.core.publisher.Mono.just;

class DefaultOrSwitchIfEmpty {

    @Test
    void FluxAfterEmpty() {
        Flux.just(2, 3)
            .doOnNext(t -> System.out.println(" First Next " + t))
            .filter(t -> t > 4)
            .doOnNext(t -> System.out.println(" Next after filter " + t))
            .switchIfEmpty(Flux.just(5, 6))
            .doOnNext(t -> System.out.println(" Second Next " + t))
            .filter(t -> t > 4)
            .doOnNext(t -> System.out.println(" Next after Second filter " + t))
            .subscribe();
    }

    @Test
    void FluxAfterEmpty2() {
        Flux.just(2, 3)
            .doOnNext(t -> System.out.println(" First Next " + t))
            .filter(t -> t > 2)
            .doOnNext(t -> System.out.println(" Next after filter " + t))
            .switchIfEmpty(Flux.just(5, 6))
            .doOnNext(t -> System.out.println(" Second Next " + t))
            .filter(t -> t == 3 )
            .doOnNext(t -> System.out.println(" Next after Second filter " + t))
            .subscribe();
    }

    @Test
    void MonoAfterEmpty() {
        just(2)
            .doOnNext(t -> System.out.println(" First Next " + t))
            .filter(t -> t == 3)
            .doOnNext(t -> System.out.println(" Second Next " + t))
            .defaultIfEmpty(3)
            .filter(t -> t == 3)
            .doOnNext(t -> System.out.println(" Third Next " + t))
            .switchIfEmpty(just(4))
            .filter(t -> t == 3)
            .doOnNext(t -> System.out.println(" Fourth Next " + t))
            .subscribe();
    }

}