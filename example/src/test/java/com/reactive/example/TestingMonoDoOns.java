package com.reactive.example;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import static reactor.core.publisher.Mono.fromSupplier;

class TestingMonoDoOns {

    @Test
    void MonoDoOnsOnException() {
        fromSupplier(this::induceException)
                .doOnSubscribe(y -> System.out.println("doOnSubscribe 1"))
                .doOnNext(y -> System.out.println("doOnNext 1"))
                .doOnSuccess(y -> System.out.println("doOnSuccess 1"))
                .doOnError(y -> System.out.println("doOnError 1"))
                .map(t -> "")
                .doOnSuccess(y -> System.out.println("doOnSuccess 2"))
                .doOnError(y -> System.out.println("doOnError 2"))
                .doOnNext(y -> System.out.println("doOnNext 2"))
                .doOnSubscribe(y -> System.out.println("doOnSubscribe 2"))
                .subscribe();
    }

    @Test
    void MonoDoOns() {
        fromSupplier(() -> "data")
                .doOnSubscribe(y -> System.out.println("doOnSubscribe 1" + y))
                .doOnNext(y -> System.out.println("doOnNext 1" + y))
                .doOnSuccess(y -> System.out.println("doOnSuccess 1" + y))
                .doOnError(y -> System.out.println("doOnError 1" + y))
                .map(t -> "new data")
                .doOnSuccess(y -> System.out.println("doOnSuccess 2" + y))
                .doOnError(y -> System.out.println("doOnError 2" + y))
                .doOnNext(y -> System.out.println("doOnNext 2" + y))
                .doOnSubscribe(y -> System.out.println("doOnSubscribe 2" + y))
                .subscribe();
    }

    @Test
    void MonoDoOnsWithEmpty() {
        fromSupplier(() -> "data")
                .doOnSubscribe(y -> System.out.println("doOnSubscribe 1" + y))
                .doOnNext(y -> System.out.println("doOnNext 1" + y))
                .doOnSuccess(y -> System.out.println("doOnSuccess 1" + y))
                .doOnError(y -> System.out.println("doOnError 1" + y))
                .flatMap(t -> Mono.empty())
                .doOnSuccess(y -> System.out.println("doOnSuccess 2" + y))
                .doOnError(y -> System.out.println("doOnError 2" + y))
                .doOnNext(y -> System.out.println("doOnNext 2" + y))
                .doOnSubscribe(y -> System.out.println("doOnSubscribe 2" + y))
                .subscribe();
    }

    private String induceException() {
        int i = 9 / 0;
        return "";
    }

}