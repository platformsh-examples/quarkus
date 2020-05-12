package org.acme.hibernate.orm;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import org.hibernate.search.engine.backend.types.Sortable;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.KeywordField;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Objects;

@Entity
@Indexed
public class Music extends PanacheEntity {

    @Column(length = 250, unique = true)
    @FullTextField(analyzer = "english")
    private String lyric;

    @Column(length = 40, unique = true)
    @FullTextField(analyzer = "name")
    @KeywordField(name = "singer_sort", sortable = Sortable.YES, normalizer = "sort")
    private String singer;

    @Column(length = 40, unique = true)
    @FullTextField(analyzer = "name")
    @KeywordField(name = "author_sort", sortable = Sortable.YES, normalizer = "sort")
    private String author;


    public String getLyric() {
        return lyric;
    }

    public void setLyric(String plate) {
        this.lyric = plate;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String make) {
        this.singer = make;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String model) {
        this.author = model;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Music music = (Music) o;
        return Objects.equals(lyric, music.lyric);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(lyric);
    }

    @Override
    public String toString() {
        return "Music{" +
                "lyric='" + lyric + '\'' +
                ", singer='" + singer + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
