package com.rmaurya.Aircraft;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AircraftRepositoryTest {

    private Aircraft ac1,ac2;

    @Autowired
    private AircraftRepository aircraftRepository;

    @BeforeEach
    void setUp() {
        ac1 = new Aircraft(1L, "AB123", "7500", "A1", "Flight101", "RouteA", "TypeA", "Category1",
                35000, 90, 450, 100, 36000, 37.7749, -122.4194, 1013.25,
                50.0, 270.0, true, false, Instant.now(), Instant.now(), Instant.now());
        ac2= new Aircraft(2L, "CD456", "7600", "B2", "Flight202", "RouteB", "TypeB", "Category2",
                36000, 180, 500, 200, 37000, 40.7128, -74.0060, 1012.50,
                60.0, 180.0, true, true, Instant.now(), Instant.now(), Instant.now());

        aircraftRepository.saveAll(List.of(ac1,ac2));
    }

    @AfterEach
    void tearDown() {
        aircraftRepository.deleteAll();
    }

    @Test
    void testFindAll(){
        assertEquals(List.of(ac1,ac2),aircraftRepository.findAll());
    }

    @Test
    void testFindById(){
        assertEquals(Optional.of(ac1),aircraftRepository.findById(ac1.getId()));
        assertEquals(Optional.of(ac2),aircraftRepository.findById(ac2.getId()));
    }



}