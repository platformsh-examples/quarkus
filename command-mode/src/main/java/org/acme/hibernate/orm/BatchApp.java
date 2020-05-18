package org.acme.hibernate.orm;

import io.quarkus.runtime.Quarkus;

public class BatchApp {
    public static void main(String[] args) {
        Quarkus.run(AnagramApp.class, args);
    }
}
