package lk.ijse.spring.service.impl;


import lk.ijse.spring.dto.ItemDTO;
import lk.ijse.spring.entity.Item;
import lk.ijse.spring.repo.ItemRepo;
import lk.ijse.spring.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Chanuka Weerakkody
 * @since : 20.1.1
 **/
@Service
@Transactional
public class ItemServiceImpl implements ItemService {
    /*@Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }*/

    @Autowired
    private ItemRepo itemRepo;

/*
    @Autowired
    ModelMapper mapper;
*/

    @Override
    public void saveItem(ItemDTO dto) {
        if(!itemRepo.existsById(dto.getCode())){
            //itemRepo.save(mapper.map(dto, Item.class));

            Item item = new Item(dto.getCode(), dto.getDescription(), dto.getQtyOnHand(), dto.getUnitPrice());
            System.out.println("Item Code is ========================"+dto.getCode());
            itemRepo.save(item);

        }else {
            throw new RuntimeException("Item code is already exists!!!");
        }
    }

    @Override
    public void deleteItem(String id) {
        if (itemRepo.existsById(id)) {
            itemRepo.deleteById(id);
        }else{
            throw new RuntimeException("Item code is not found to delete!");
        }
    }

    @Override
    public void updateItem(ItemDTO dto) {
        if (itemRepo.existsById(dto.getCode())) {
            //itemRepo.save(mapper.map(dto, Item.class));

            Item item = new Item(dto.getCode(), dto.getDescription(), dto.getQtyOnHand(), dto.getUnitPrice());
            itemRepo.save(item);
        }else{
            throw new RuntimeException("No Item found to update!");
        }
    }

    @Override
    public ItemDTO searchItem(String id) {
        if (itemRepo.existsById(id)) {
            Item item = itemRepo.findById(id).get();
            return new ItemDTO(item.getCode(), item.getDescription(), item.getQtyOnHand(), item.getUnitPrice());
            //return mapper.map(itemRepo.findById(id).get(), ItemDTO.class);
        }else{
            throw new RuntimeException("No Item "+id+" ..!");
        }
    }

    @Override
    public List<ItemDTO> getAllItem() {
        List<Item> all = itemRepo.findAll();

        List<ItemDTO> arrayList=new ArrayList<>();
        for (Item item : all) {
            arrayList.add(new ItemDTO(item.getCode(), item.getDescription(), item.getQtyOnHand(), item.getUnitPrice()));
        }
        return arrayList;
        //return mapper.map(all,new TypeToken<ItemDTO>(){}.getType());
    }
}
