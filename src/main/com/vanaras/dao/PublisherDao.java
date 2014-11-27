package com.vanaras.dao;

import com.vanaras.model.Publisher;

public interface PublisherDao {
    public void save(Publisher publisher);
    public void update(Publisher publisher);
    public void delete(Publisher publisher);
    public Publisher findByName(String name);

}
