package lk.ijse.spring.service;

import lk.ijse.spring.dto.CustomerDTO;
import lk.ijse.spring.dto.ItemDTO;

import java.util.List;

/**
 * @author : Chanuka Weerakkody
 * @since : 20.1.1
 **/

public interface ItemService {
    void saveItem(ItemDTO dto);
    void deleteItem(String id);
    void updateItem(ItemDTO dto);
    ItemDTO searchItem(String id);
    List<ItemDTO> getAllItem();
}
