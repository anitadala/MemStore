package com.anita.memstore;

import java.util.HashMap;
import java.util.Map;

public class MemStore <K,V> {
    private final Map<K,Entry<V>> store;

    public MemStore(){
        this.store = new HashMap<>;
    }
    //Inserts or updates a key-value pair.
    public void put(K key,V value){
        validateKey(key);
        store.put(key,new Entry<>(value));
    }
    // Retrieves value for the given key, return null if key does not exist
    public V get(K key){
        validateKey(key);
        Entry<V> entry = store.get(key);
        return entry!=null ? entry.getValue() : null;
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
}
public static void main(String[] args) {
    MemStore<String, String> store = new MemStore<>();

    store.put("user1", "Anita");
    System.out.println(store.get("user1")); // Anita

//    store.delete("user1");
//    System.out.println(store.get("user1")); // null
}

