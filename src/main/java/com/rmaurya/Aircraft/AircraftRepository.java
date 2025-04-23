package com.rmaurya.Aircraft;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface AircraftRepository extends ReactiveCrudRepository<Aircraft,Long> {
}
