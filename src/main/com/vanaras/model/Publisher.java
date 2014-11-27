package com.vanaras.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "publisher")
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "publisher")
    private Set<Book> books = new HashSet<>();

    Publisher() {
    }

    public Publisher(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Publisher publisher = (Publisher) o;

//        if (id != publisher.id) return false;
//        if (books != null ? !books.equals(publisher.books) : publisher.books != null) return false;
        if (!name.equals(publisher.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
//        int result = id;
        int result = 0;
        result = 31 * result + name.hashCode();
        result = 31 * result + (books != null ? books.hashCode() : 0);
        return result;
    }
}
