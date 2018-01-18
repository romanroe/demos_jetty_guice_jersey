package de.romanroe.demos.jetty_guice_jersey.service;

import java.util.concurrent.atomic.AtomicInteger;

public class ValueService {

    private AtomicInteger value = new AtomicInteger(0);

    public int nextValue() {
        return value.incrementAndGet();
    }

}
