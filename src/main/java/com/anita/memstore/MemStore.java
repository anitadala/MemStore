package com.anita.memstore;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MemStore <K,V> {
    private final Map<K,Entry<V>> store;

    public MemStore(){
        this.store = new ConcurrentHashMap<>();
    }
    public void put(K key,V value){
        validateKey(key);
        store.put(key,new Entry<>(value));
    }
    //Inserts or updates a key-value pair with ttl given
    public void put(K key,V value, long ttlMillis){
        validateKey(key);
        if(ttlMillis<=0){
            throw new IllegalArgumentException("TTL must be greater than zero");
        }
        long expiryTime = System.currentTimeMillis()+ttlMillis;
        store.put(key,new Entry<>(value,expiryTime));
    }
    // Retrieves value for the given key, return null if key does not exist
    public V get(K key){
        validateKey(key);
        Entry<V> entry = store.computeIfPresent(key, (k, e) ->
                e.isExpired() ? null : e
        );
        return entry != null ? entry.getValue() : null;
    }
    //Deletes the given key from store.
    public boolean delete(K key){
        validateKey(key);
        return store.remove(key) != null;
    }
    //validate the key
    private void validateKey(K key){
        if(key == null){
            throw new IllegalArgumentException("Key can not be null");
        }
    }

    //ContainsKey() functionality
    public boolean containsKey(K key){
        validateKey(key);

        Entry<V> entry = store.get(key);
        if(entry==null){
            return false;
        }
        if(entry.isExpired()){
            store.remove(key);
            return false;
        }
        return true;
    }


}


