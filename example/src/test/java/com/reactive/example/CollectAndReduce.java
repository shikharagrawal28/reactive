package com.reactive.example;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static reactor.core.publisher.Mono.just;

class CollectAndReduce {

    @Test
    void collectWhenNotEmpty() {
        Flux.just("1", "2", "3", "4")
            .collect(toList())
            .doOnNext(f -> System.out.println("List from collect(Collector) "+f))
            .subscribe();
    }

    @Test
    void collectWhenEmpty() {
        Flux.just("1", "2", "3", "4")
            .filter(t -> t.equals("21"))
            .collect(toList())
            .doOnNext(f -> System.out.println("List from collect(Collector) when flux is empty "+f))
            .map(t -> "2")
            .doOnSuccess(f -> System.out.println("List from collect(Collector) when flux is empty "+f))
            .subscribe();
    }

    @Test
    void collectWhenEmptyOperators() {
        Flux.just("1", "2", "3", "4")
            .filter(t -> t.equals("21"))
            .defaultIfEmpty("2")
            .collect(toList())
            .doOnNext(f -> System.out.println("List from collect(Collector) when flux is empty "+f))
            .map(t -> "2")
            .doOnSuccess(f -> System.out.println("List from collect(Collector) when flux is empty "+f))
            .subscribe();
    }

    @Test
    void collectWhenEmptyOperators2() {
        Flux.just("1", "2", "3", "4")
            .filter(t -> t.equals("21"))
            .collect(toList())
            .defaultIfEmpty(asList("2"))
            .doOnNext(f -> System.out.println("List from collect(Collector) when flux is empty "+f))
            .map(t -> "2")
            .doOnSuccess(f -> System.out.println("List from collect(Collector) when flux is empty "+f))
            .subscribe();
    }
    @Test
    void collectListWhenNotEmpty() {
        Flux.just("1", "2", "3", "4")
            .collectList()
            .doOnNext(f -> System.out.println("List from collectList "+f))
            .subscribe();
    }

    @Test
    void collectListWhenEmpty() {
        Flux.just("1", "2", "3", "4")
            .filter(t -> t.equals("21"))
            .collectList()
            .doOnNext(f -> System.out.println("List from collectList when flux is empty "+f))
            .map(t -> "2")
            .doOnSuccess(f -> System.out.println("List from collectList when flux is empty "+f))
            .subscribe();
    }

    @Test
    void collectSecondTypeWhenNotEmpty() {
        Flux.just("1", "2", "3", "4")
            .collect(ArrayList::new, ArrayList::add)
            .doOnNext(f -> System.out.println("List from collect(Supplier, BiConsumer) "+f))
            .subscribe();
    }

    @Test
    void collectSecondTypeWhenEmpty() {
        Flux.just("1", "2", "3", "4")
            .filter(t -> t.equals("21"))
            .collect(ArrayList::new, ArrayList::add)
            .doOnNext(f -> System.out.println("List from collect(Supplier, BiConsumer) "+f))
            .map(t -> "2")
            .doOnSuccess(f -> System.out.println("List from collect(Supplier, BiConsumer) when flux is empty "+f))
            .subscribe();
    }

    @Test
    void reduce() {
        Flux.just("1", "2", "3", "4")
            .reduce(new ArrayList<>(), this::makeList)
            .doOnNext(f -> System.out.println("List from reduce "+f))
            .subscribe();
        //OR
        Flux.just("1", "2", "3", "4")
            .map(f -> f +"1")
            .collectList()
            .doOnNext(f -> System.out.println("List from without reduce "+f))
            .subscribe();

    }

    @Test
    void reduceWhenEmpty() {
        Flux.just("1", "2", "3", "4")
            .filter(g -> g.equals("23"))
            .reduce(new ArrayList<>(), this::makeList)
            .doOnNext(f -> System.out.println("List from reduce "+f))
            .map(t -> "2")
            .doOnSuccess(f -> System.out.println("List from reduce when flux is empty "+f))
            .subscribe();
    }

    private ArrayList<String> makeList(ArrayList<String> a, String b) {
        a.add(b + "1");
        return a;
    }
}