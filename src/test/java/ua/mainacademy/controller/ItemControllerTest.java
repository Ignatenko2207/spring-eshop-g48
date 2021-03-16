package ua.mainacademy.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import ua.mainacademy.model.Item;
import ua.mainacademy.service.ItemService;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class ItemControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @MockBean
    private ItemService itemService;

    @Test
    void createSuccess() throws URISyntaxException {
        Item testItem = new Item();
        testItem.setItemCode("test_code");
        testItem.setName("test_name");
        testItem.setInitPrice(54321);
        testItem.setPrice(54321);

        when(itemService.create(any(Item.class))).thenReturn(testItem);

        RequestEntity<Item> requestEntity = new RequestEntity<>(testItem, HttpMethod.PUT, new URI("/item"));

        ResponseEntity<Item> responseEntity = testRestTemplate.exchange(requestEntity, Item.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void createFailed() throws URISyntaxException {
        Item testItem = new Item();
        testItem.setItemCode("test_code");
        testItem.setName("test_name");
        testItem.setInitPrice(54321);
        testItem.setPrice(54321);

        when(itemService.create(any(Item.class))).thenThrow(new RuntimeException());

        RequestEntity<Item> requestEntity = new RequestEntity<>(testItem, HttpMethod.PUT, new URI("/item"));

        ResponseEntity<Item> responseEntity = testRestTemplate.exchange(requestEntity, Item.class);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }
}