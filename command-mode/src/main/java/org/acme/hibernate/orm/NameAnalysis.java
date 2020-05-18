package org.acme.hibernate.orm;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class NameAnalysis extends PanacheEntity {

    @Column(length = 40, unique = true)
    public String name;

    @Column(length = 40, unique = true)
    public String anagram;

    public boolean executed;


    public void execute() {
        this.anagram = new StringBuilder(this.name).reverse().toString();
        this.executed = true;
        this.persist();
    }

    @Override
    public String toString() {
        return "NameAnalysis{" +
                "name='" + name + '\'' +
                ", anagram='" + anagram + '\'' +
                ", executed=" + executed +
                '}';
    }
}
