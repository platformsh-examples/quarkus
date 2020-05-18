package org.acme.hibernate.orm;

import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

import javax.inject.Inject;

@QuarkusMain(name = "anagram")
public class AnagramApp  implements QuarkusApplication {

    @Inject
    AnagramService service;

    @Override
    public int run(String... args){
        service.executeAnagram();
        return 0;
    }
}
