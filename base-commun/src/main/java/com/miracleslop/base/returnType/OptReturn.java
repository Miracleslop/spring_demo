package com.miracleslop.base.returnType;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class OptReturn<T> {
    private Optional<T> data;

    public static <T> OptReturn<T> of(T data) {
        return new OptReturn<>(data);
    }

    private OptReturn(T data) {
        this.data = Optional.of(data);
    }

    public OptReturn<T> setData(T data) {
        this.data = Optional.of(data);
        return this;
    }

    public T get() {
        return this.data.get();
    }

    public boolean isPresent() {
        return this.data.isPresent();
    }

    public T orElse(T other) {
        return this.data.orElse(other);
    }

    public <X extends Throwable> T orElseThrow(Supplier<? extends X> supplier) throws X {
        return this.data.orElseThrow(supplier);
    }

    public void ifPresent(Consumer<? super T> consumer) {
        this.data.ifPresent(consumer);
    }

}
