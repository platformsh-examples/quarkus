package org.acme.hibernate.orm;


import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.logging.Logger;

@ApplicationScoped
public class AnagramService {

    private static final Logger LOGGER = Logger.getLogger(AnagramService.class.getName());


    @Transactional
    public void executeAnagram() {
        LOGGER.info("Starting the anagram analysis");

        PanacheQuery<NameAnalysis> living = NameAnalysis.find("executed", false);
        final PanacheQuery<NameAnalysis> page = living.page(Page.ofSize(20));

        LOGGER.info("The total number of entities returned by this query without paging: " + page.count());
        LOGGER.info("The number of pages: " + page.pageCount());
        boolean hasNext = true;

        while (hasNext) {
            page.stream().forEach(NameAnalysis::execute);
            if (hasNext = page.hasNextPage()) {
                page.nextPage();
            }
        }
        LOGGER.info("Finished the anagram analysis");
    }
}
