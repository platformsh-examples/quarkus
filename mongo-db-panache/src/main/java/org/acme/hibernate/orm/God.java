package org.acme.hibernate.orm;

import io.quarkus.mongodb.panache.PanacheMongoEntity;

import java.util.Set;

public class God extends PanacheMongoEntity {

    public String name;

    public Set<String> powers;

}
