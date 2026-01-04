# MemStore
MemStore – A Thread-Safe In-Memory Key-Value Storage Engine
A lightweight, in-memory key-value storage engine built from scratch to understand how modern storage systems work under the hood.
This project focuses on data structures, performance, correctness, and thread safety, rather than external databases or frameworks.

🚀 Why This Project?

Most applications rely heavily on databases and caches, but very few engineers understand how they actually work internally.

This project is built to:

  - Understand core storage concepts

  - Explore design trade-offs

  - Practice writing clean, testable backend code

  - Simulate real-world components like caches and embedded databases

🧩 Features
✅ Core Functionality

  - put(key, value) – Insert or update a value

  - get(key) – Retrieve a value

  - delete(key) – Remove a key

⏳ Time-To-Live (TTL)

  - Optional expiration time per key

  - Lazy eviction on read

  - Automatic cleanup of expired entries

🧵 Thread Safety (Optional / Advanced)

  - Safe concurrent reads and writes

  - Designed to work correctly under multi-threaded access


🛠️ Design Overview

  - At its core, MemStore uses:

  - A hash-based data structure for O(1) average access

  - Wrapper objects to store:

    - Value

    - Expiration timestamp (if any)

  - High-level design:

    - Key → Hash Map → Entry(value, expiryTime)

  TTL is validated on read/write to ensure expired data is never returned.

⚖️ Design Decisions & Trade-offs
  Decision	Reason
  In-memory storage	Fast access, simpler design
  Lazy TTL eviction	Avoids background thread overhead
  Hash-based lookup	O(1) average performance
  No persistence	Focus on core storage logic

🧪 Testing Strategy

The project includes unit tests covering:

  - Basic CRUD operations

  - TTL expiration behavior

  - Edge cases (null keys, overwrites, deletes)

  - Concurrent access scenarios (if thread safety enabled)

Testing ensures correctness and prevents regressions.

📦 Project Structure
## 📦 Project Structure

```text
memstore/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── anita/
│   │               └── memstore/
│   │                   ├── MemStore.java
│   │                   ├── Entry.java
│   │                   └── exception/
│   └── test/
│       └── java/
│           └── com/
│               └── anita/
│                   └── memstore/
│                       └── MemStoreTest.java
├── README.md
├── DESIGN.md
├── .gitignore
└── pom.xml

```
▶️ Usage Example
  MemStore<String, String> store = new MemStore<>();

  store.put("user1", "Anita");
  store.put("session", "active", 5000); // TTL in ms

  String value = store.get("user1");
  store.delete("session");

📈 Performance Characteristics

  Time Complexity

  - put → O(1) average

  - get → O(1) average

  - delete → O(1) average

Space Complexity

  - O(n), where n is the number of stored keys

🔮 Future Improvements (Optional)

- Background eviction thread

- Size-based eviction (LRU / LFU)

- Persistence to disk

- Metrics & monitoring hooks

- Distributed version using consistent hashing

🎯 What This Project Demonstrates

- Strong grasp of data structures

- Understanding of storage internals

- Ability to reason about performance and trade-offs

- Writing clean, testable backend code

- Readiness for system design discussions
