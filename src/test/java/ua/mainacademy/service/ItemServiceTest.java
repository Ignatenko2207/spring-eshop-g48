package ua.mainacademy.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import ua.mainacademy.dao.ItemDAO;
import ua.mainacademy.model.Item;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class ItemServiceTest {

    @Autowired
    private ItemService itemService;

    @MockBean
    private ItemDAO itemDAO;

    @Test
    void create() {
        Item testItem = new Item();
        testItem.setItemCode("test_code");
        testItem.setName("test_name");
        testItem.setInitPrice(54321);
        testItem.setPrice(54321);

        when(itemDAO.findFirstByItemCode(anyString())).thenReturn(Optional.empty());
        when(itemDAO.save(any(Item.class))).thenReturn(testItem);

        assertNotNull(itemService.create(testItem));
    }

}