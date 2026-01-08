🧩 TTL (Time-To-Live) Design
Overview

MemStore supports optional Time-To-Live (TTL) for entries, allowing keys to automatically expire after a specified duration.
TTL is useful for scenarios such as:

session management

caching

temporary tokens

rate-limiting metadata

TTL is optional—entries without TTL never expire.

TTL Representation

Each value is wrapped inside an Entry object that stores:

the actual value

an absolute expiration timestamp

Entry<V>
├── value
└── expiryTime (milliseconds since epoch)


An expiryTime of -1 indicates that the entry does not expire.

Eviction Strategy: Lazy Eviction

MemStore uses a lazy eviction strategy.

How it works:

Entries are checked for expiration only when accessed

If an entry is expired:

it is removed immediately

null / false is returned to the caller

Why lazy eviction?

Avoids background cleanup threads

Reduces CPU overhead

Keeps the design simple and deterministic

Commonly used in real systems (e.g., Redis)

Expired entries that are never accessed may remain in memory until touched—this is an intentional trade-off.

TTL-Aware Operations
put(key, value, ttl)

TTL is provided in milliseconds

TTL must be greater than zero

Expiry time is calculated as:

currentTime + ttl

get(key)

Returns value if key exists and is not expired

Removes and returns null if the key is expired

Returns null if key does not exist

containsKey(key)

Returns true only if the key exists and is not expired

Triggers lazy eviction for expired keys

Ensures logical consistency with get()

delete(key)

Explicitly removes the key

TTL does not affect delete behavior

Thread-Safety Considerations

For concurrent environments, TTL eviction must be atomic to avoid race conditions.

The design supports thread-safe TTL by:

Using ConcurrentHashMap as the storage engine

Performing expiration checks and removals using atomic map operations
(e.g., computeIfPresent)

This avoids:

check-then-act race conditions

double eviction

global locks (synchronized)

Design Trade-offs
Advantages

Simple and predictable behavior

No background threads

Low runtime overhead

Easy to reason about and test

Limitations

Expired entries may occupy memory until accessed

No proactive memory reclamation

Single-node, in-memory only

These trade-offs are acceptable for a lightweight in-memory store.

Summary

TTL in MemStore is implemented using per-entry expiration timestamps and lazy eviction, ensuring correctness, simplicity, and extensibility.
The design favors clarity and safety over premature optimization, making it suitable for both learning and real-world system discussions.