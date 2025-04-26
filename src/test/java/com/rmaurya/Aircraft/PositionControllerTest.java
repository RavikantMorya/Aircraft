package com.rmaurya.Aircraft;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@WebFluxTest(controllers = {PositionController.class})
@AutoConfigureWebTestClient

class PositionControllerTest {
    @MockBean
    private  AircraftService aircraftService;

    private Aircraft ac1,ac2;
    @BeforeEach
    void setUp() {
        ac1 = new Aircraft(1L, "AB123", "7500", "A1", "Flight101", "RouteA", "TypeA", "Category1",
                        35000, 90, 450, 100, 36000, 37.7749, -122.4194, 1013.25,
                        50.0, 270.0, true, false, Instant.now(), Instant.now(), Instant.now());
        ac2= new Aircraft(2L, "CD456", "7600", "B2", "Flight202", "RouteB", "TypeB", "Category2",
                        36000, 180, 500, 200, 37000, 40.7128, -74.0060, 1012.50,
                        60.0, 180.0, true, true, Instant.now(), Instant.now(), Instant.now());

        Mockito.when(aircraftService.getAircraftPosition())
                .thenReturn(List.of(ac1,ac2));
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getCurrentAircraftPositions(@Autowired WebTestClient client) {

        final Iterable<Aircraft> aircrafts = client.get()
                .uri("/api/position")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(Aircraft.class)
                .returnResult()
                .getResponseBody();

        assertEquals(List.of(ac1,ac2),aircrafts);
    }
}