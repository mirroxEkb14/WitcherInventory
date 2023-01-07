package service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class CollectionServiceTest {

    @Test
    void getWolvenGearMapsString() {
        assertNotNull(CollectionService.getWolvenGearMapsString());
    }
}