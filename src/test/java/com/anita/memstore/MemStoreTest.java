package com.anita.memstore;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MemStoreTest{

    @Test
    void shouldReturnValueBeforeTTLExpires(){
        MemStore<String,String> store = new MemStore<>();
        store.put("token", "valid", 2000);
        String value = store.get("token");
        assertEquals("valid",value);
    }

    @Test
    void shouldReturnNullAfterTTLExpires() throws InterruptedException{
        MemStore<String,String> store = new MemStore<>();
        store.put("token", "valid", 2000);
        Thread.sleep(3000);
        String value = store.get("token");
        assertNull(value);
    }

    @Test
    void shouldLazyEvictExpiredEntry() throws InterruptedException{
        MemStore<String,String> store = new MemStore<>();
        store.put("session", "active", 2000);
        Thread.sleep(3000);
        // First access triggers eviction
        assertNull(store.get("session"));

        // Second access confirms eviction
        assertNull(store.get("session"));
    }

    @Test
    void nonTTLValueShouldNeverExpire() throws InterruptedException{
        MemStore<String,String> store = new MemStore<>();
        store.put("user", "Anita");
        Thread.sleep(2000);

        assertEquals("Anita", store.get("user"));
    }

    @Test
    void shouldThrowExceptionForInvalidTTL(){
        MemStore<String, String> store = new MemStore<>();
        assertThrows(IllegalArgumentException.class,
                () -> store.put("key", "value", 0));
    }
}
