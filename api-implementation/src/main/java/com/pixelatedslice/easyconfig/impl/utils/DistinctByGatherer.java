package com.pixelatedslice.easyconfig.impl.utils;

import org.jspecify.annotations.NullMarked;


import java.util.Collection;
import java.util.Objects;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Gatherer;

@NullMarked
public class DistinctByGatherer<V, K> implements Gatherer<V, LinkedBlockingQueue<V>, V> {

    private final Function<V, K> by;

    public DistinctByGatherer(Function<V, K> by) {
        this.by = Objects.requireNonNull(by);
    }

    @Override
    public Supplier<LinkedBlockingQueue<V>> initializer() {
        return LinkedBlockingQueue::new;
    }

    @Override
    public Integrator<LinkedBlockingQueue<V>, V, V> integrator() {
        return (vs, v, downstream) -> {
            if (this.check(vs, v)) {
                vs.add(v);
            }
            return true;
        };
    }

    private boolean check(Collection<V> compare, V checking) {
        final var incomingBy = this.by.apply(checking);
        return compare.stream().map(this.by).noneMatch(value -> value.equals(incomingBy));
    }

    @Override
    public BinaryOperator<LinkedBlockingQueue<V>> combiner() {
        return (vs, vs2) -> {
            final var vs1Filter = vs2.stream().filter(value -> this.check(vs, value)).toList();
            vs.addAll(vs1Filter);
            return vs;
        };
    }

    @Override
    public BiConsumer<LinkedBlockingQueue<V>, Downstream<? super V>> finisher() {
        return (vs, downstream) -> {
            vs.forEach(downstream::push);
        };
    }
}
