package ua.mainacademy.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.mainacademy.dao.ItemDAO;
import ua.mainacademy.model.Item;
import ua.mainacademy.service.ItemService;

import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemDAO itemDAO;

    @Override
    public Item create(Item item) {
        if (isNull(item.getId()) && itemDAO.findFirstByItemCode(item.getItemCode()).isEmpty() ) {
            return itemDAO.save(item);
        }
        throw new RuntimeException("Such item can not be saved");
    }

    @Override
    public Item update(Item item) {
        if (nonNull(item.getId()) && itemDAO.findById(item.getId()).isPresent()) {
            return itemDAO.save(item);
        }
        throw new RuntimeException("Such item can not be updated");
    }

    @Override
    public Item findOneById(Integer id) {
        return itemDAO.findById(id).orElseThrow(() -> new RuntimeException("Item was not found"));
    }

    @Override
    public List<Item> findAll() {
        return itemDAO.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        itemDAO.deleteById(id);
    }
}
