package com.pixelatedslice.easyconfig.impl.utils;

import org.jspecify.annotations.NonNull;

import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Gatherer;

public class DeepRecursiveGatherer<V> implements Gatherer<V, Void, V> {

    private final Function<V, Collection<V>> recursion;

    public DeepRecursiveGatherer(@NonNull Function<V, Collection<V>> function) {
        this.recursion = function;
    }

    @Override
    public Integrator<Void, V, V> integrator() {
        return (_, v, downstream) -> {
            downstream.push(v);
            if(v instanceof Collection collection){
                next(collection, downstream);
            }else{
                next(v, downstream);
            }
            next(v, downstream);
            return false;
        };
    }

    private void next(@NonNull Collection<V> result, @NonNull Downstream<? super V> downstream){
        for (var nextNode : result) {
            downstream.push(nextNode);
            next(nextNode, downstream);
        }
    }

    private void next(@NonNull V node, Downstream<? super V> downstream) {
        var result = this.recursion.apply(node);
        next(result, downstream);
    }
}
