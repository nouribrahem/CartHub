package com.order.OrderNotificationApp.repository;

import org.springframework.stereotype.Repository;

@Repository
interface BaseRepository {
    public boolean add(Object o);
    public boolean remove(Object o);
    public Object getByID(Object o);
    public Object getAll();
}
