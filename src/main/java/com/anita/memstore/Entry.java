package com.anita.memstore;

import java.security.Key;
import java.time.LocalDateTime;

public class Entry<V> {
    /*wrapped the Entry object to allow future extensions like TTL,metadata or versioning without changing the
    store API.*/
    private final V value;
    private final long expiryTime;

    public Entry(V value){
        this.value = value;
        this.expiryTime = -1;
    }
    public Entry(V value,long expiryTime){
        this.value = value;
        this.expiryTime = expiryTime;
    }
    public V getValue(){
        return value;
    }
    public boolean isExpired(){
        if(expiryTime == -1){
            return false;
        }
        return System.currentTimeMillis() > expiryTime;
    }
}
