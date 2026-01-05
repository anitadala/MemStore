package com.anita.memstore;

public class Entry<V> {
    /*wrapped the Entry object to allow future extensions like TTL,metadata or versioning without changing the
    store API.*/
    private final V value;

    public Entry(V value){
        this.value = value;
    }
    public V getValue(){
        return value;
    }
}
