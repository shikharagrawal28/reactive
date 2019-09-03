package com.reactive.example;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static reactor.core.publisher.Mono.fromSupplier;

class TestingFluxDoOns {

    @Test
    void FluxDoOnsException() {
        Flux.just(1, 2, 3, 4)
            .doOnSubscribe(y -> System.out.println("doOnSubscribe 1" + y))
            .doOnNext(y -> System.out.println("doOnNext 1" + y))
            .doOnError(y -> System.out.println("doOnError 1" + y))
            .doOnComplete(() -> System.out.println("Flux completed..."))
            .map(t -> t/0)
            .doOnError(y -> System.out.println("doOnError 2" + y))
            .doOnNext(y -> System.out.println("doOnNext 2" + y))
            .doOnSubscribe(y -> System.out.println("doOnSubscribe 2" + y))
            .doOnComplete(() -> System.out.println("Flux completed"))
            .doOnEach(t -> System.out.println("I will be called every time"))
            .subscribe();
    }

    @Test
    void FluxDoOns() {
        Flux.just("1", "2", "3", "4")
            .doOnSubscribe(y -> System.out.println("doOnSubscribe 1" + y))
            .doOnNext(y -> System.out.println("doOnNext 1" + y))
            .doOnError(y -> System.out.println("doOnError 1" + y))
            .doOnComplete(() -> System.out.println("Flux completed..."))
            .map(t -> t + "1")
            .doOnError(y -> System.out.println("doOnError 2" + y))
            .doOnNext(y -> System.out.println("doOnNext 2" + y))
            .doOnSubscribe(y -> System.out.println("doOnSubscribe 2" + y))
            .doOnComplete(() -> System.out.println("Flux completed"))
            .doOnEach(t -> System.out.println("I will be called every time"))
            .subscribe();
    }

    @Test
    void FluxDoOnsWithEmpty() {
        Flux.just("1", "2", "3", "4")
            .doOnSubscribe(y -> System.out.println("doOnSubscribe 1" + y))
            .doOnNext(y -> System.out.println("doOnNext 1" + y))
            .doOnError(y -> System.out.println("doOnError 1" + y))
            .doOnComplete(() -> System.out.println("Flux completed..."))
            .flatMap(t -> Mono.empty())
            .doOnError(y -> System.out.println("doOnError 2" + y))
            .doOnNext(y -> System.out.println("doOnNext 2" + y))
            .doOnSubscribe(y -> System.out.println("doOnSubscribe 2" + y))
            .doOnComplete(() -> System.out.println("Flux completed"))
            .doOnEach(t -> System.out.println("I will be called every time"))
            .subscribe();
    }

}