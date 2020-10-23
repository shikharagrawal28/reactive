package com.reactive.example;

import org.junit.Test;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class TestingZip {

    @Test
    public void zipTest() {
        System.out.println("Begin:" + LocalDateTime.now());
        Mono.zip(Mono.fromSupplier(() -> {
            try {
                return waits();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 2;
        }).doOnSubscribe(d -> System.out.println("Start: " + LocalDateTime.now())), Mono.fromSupplier(() -> {
            try {
                return waits();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 2;
        }).doOnSubscribe(d -> System.out.println("Start: " + LocalDateTime.now())), Mono.fromSupplier(() -> {
            try {
                return waits();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 2;
        }).doOnSubscribe(d -> System.out.println("Start: " + LocalDateTime.now())))
            .doOnSuccess(d -> System.out.println("End: " + LocalDateTime.now()))
            .subscribe();
    }

    int waits() throws InterruptedException {
        sleep(5000);
        return 1;
    }

    @Test
    public void zipTest2() {
        ArrayList<String> j = new ArrayList<>();
        System.out.println("Begin:" + LocalDateTime.now());
        Mono.zip(Mono.fromSupplier(() -> getInteger(j)).doOnSubscribe(d -> System.out.println("Start 1: " + LocalDateTime.now()))
                     .doOnSuccess(d -> System.out.println("END 1: " + LocalDateTime.now())),
                 Mono.fromSupplier(() -> getInteger(j)).doOnSubscribe(d -> System.out.println("Start 2: " + LocalDateTime.now()))
                     .doOnSuccess(d -> System.out.println("END 2: " + LocalDateTime.now())),
                 Mono.fromSupplier(() -> getInteger(j)).doOnSubscribe(d -> System.out.println("Start 3: " + LocalDateTime.now()))
                     .doOnSuccess(d -> System.out.println("END 3: " + LocalDateTime.now())))
            .doOnSuccess(d -> System.out.println("End: " + LocalDateTime.now()))
            .subscribe();
    }

    @Test
    public void zipTest3() {
        ArrayList<String> j = new ArrayList<>();
        System.out.println("Begin:" + LocalDateTime.now());
        Mono.zip(Mono.just(getInteger(j))
                     .doOnSubscribe(d -> System.out.println("Start 1: " + LocalDateTime.now()))
                     .doOnSuccess(d -> System.out.println("END 1: " + LocalDateTime.now())),
                 Mono.just(getInteger(j))
                     .doOnSubscribe(d -> System.out.println("Start 2: " + LocalDateTime.now()))
                     .doOnSuccess(d -> System.out.println("END 2: " + LocalDateTime.now())),
                 Mono.just(getInteger(j))
                     .doOnSubscribe(d -> System.out.println("Start 3: " + LocalDateTime.now()))
                     .doOnSuccess(d -> System.out.println("END 3: " + LocalDateTime.now())))
            .doOnSubscribe(d -> System.out.println("Start: " + LocalDateTime.now()))
            .doOnSuccess(d -> System.out.println("End: " + LocalDateTime.now()))
            .subscribe();
    }

    private Integer getInteger(ArrayList<String> j) {
        for (int i = 0; i < 100000; i++) {
            j.add("s");
        }
        return 2;
    }

    @Test
    public void zipTest1() throws InterruptedException {
        System.out.println("Begin:" + LocalDateTime.now());
        Mono.zip(
                Mono.delay(Duration.ofSeconds(5))
                    .doOnSubscribe((d -> System.out.println("Start: " + LocalDateTime.now())))
                    .map(f -> 1)
                    .doOnSuccess((d -> System.out.println("Success : " + d))),
                Mono.delay(Duration.ofSeconds(5))
                    .doOnSubscribe((d -> System.out.println("Start: " + LocalDateTime.now())))
                    .map(f -> 2)
                    .doOnSuccess((d -> System.out.println("Success : " + d))),
                Mono.delay(Duration.ofSeconds(5))
                    .doOnSubscribe((d -> System.out.println("Start: " + LocalDateTime.now())))
                    .map(f -> 3)
                    .doOnSuccess((d -> System.out.println("Success : " + d)))
        )
            .doOnSubscribe(d -> System.out.println("Zip Start: " + LocalDateTime.now()))
            .doOnSuccess(d -> System.out.println("ends: " + d))
            .subscribe();
        sleep(6000);
    }
}