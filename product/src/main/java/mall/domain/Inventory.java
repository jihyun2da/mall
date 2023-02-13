package mall.domain;

import mall.domain.StockDecreased;
import mall.domain.StockIncreased;
import mall.ProductApplication;
import javax.persistence.*;
import java.util.List;
import lombok.Data;
import java.util.Date;


@Entity
@Table(name="Inventory_table")
@Data

public class Inventory  {


    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    
    
    
    
    
    private Long id;
    
    
    
    
    
    private String productName;
    
    
    
    
    
    private Integer stock;

    @PostUpdate
    public void onPostUpdate(){


        // StockDecreased stockDecreased = new StockDecreased(this);
        // stockDecreased.publishAfterCommit();



        // StockIncreased stockIncreased = new StockIncreased(this);
        // stockIncreased.publishAfterCommit();

    }

    public static InventoryRepository repository(){
        InventoryRepository inventoryRepository = ProductApplication.applicationContext.getBean(InventoryRepository.class);
        return inventoryRepository;
    }




    public static void stockDecrease(DeliveryStarted deliveryStarted){

        /** Example 1:  new item 
        Inventory inventory = new Inventory();
        repository().save(inventory);

        StockDecreased stockDecreased = new StockDecreased(inventory);
        stockDecreased.publishAfterCommit();
        */

        /** Example 2:  finding and process */
        
        repository().findById(deliveryStarted.getProductId()).ifPresent(inventory->{
            try {
                inventory.setStock(inventory.getStock() - deliveryStarted.getQty()); // do something
            } catch(Exception e) {
                inventory.setStock(-1); // do something
            }
            repository().save(inventory);

            StockDecreased stockDecreased = new StockDecreased(inventory);
            stockDecreased.publishAfterCommit();

         });
        

        
    }
    public static void stockIncrease(DeliveryCanceled deliveryCanceled){

        /** Example 1:  new item 
        Inventory inventory = new Inventory();
        repository().save(inventory);

        StockIncreased stockIncreased = new StockIncreased(inventory);
        stockIncreased.publishAfterCommit();
        */

        /** Example 2:  finding and process */
        
        repository().findById(deliveryCanceled.getProductId()).ifPresent(inventory->{
            
            inventory.setStock(inventory.getStock() + deliveryCanceled.getQty()); // do something
            repository().save(inventory);

            StockIncreased stockIncreased = new StockIncreased(inventory);
            stockIncreased.publishAfterCommit();

         });
        

        
    }


}
