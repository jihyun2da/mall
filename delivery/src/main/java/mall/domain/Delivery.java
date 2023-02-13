package mall.domain;

import mall.domain.DeleveryStarted;
import mall.domain.DeliveryCompleted;
import mall.domain.DeliveryCanceled;
import mall.domain.DeliveryReturned;
import mall.DeliveryApplication;
import javax.persistence.*;
import java.util.List;
import lombok.Data;
import java.util.Date;


@Entity
@Table(name="Delivery_table")
@Data

public class Delivery  {


    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    
    
    
    
    
    private Long id;
    
    
    
    
    
    private Long orderId;
    
    
    
    
    
    private Long productId;
    
    
    
    
    
    private Long qty;
    
    
    
    
    
    private String productName;
    
    
    
    
    
    private String status;

    @PostPersist
    public void onPostPersist(){


        DeleveryStarted deleveryStarted = new DeleveryStarted(this);
        deleveryStarted.publishAfterCommit();

    }
    @PostUpdate
    public void onPostUpdate(){


        DeliveryCompleted deliveryCompleted = new DeliveryCompleted(this);
        deliveryCompleted.publishAfterCommit();



        DeliveryCanceled deliveryCanceled = new DeliveryCanceled(this);
        deliveryCanceled.publishAfterCommit();



        DeliveryReturned deliveryReturned = new DeliveryReturned(this);
        deliveryReturned.publishAfterCommit();

    }
    @PreUpdate
    public void onPreUpdate(){
    }

    public static DeliveryRepository repository(){
        DeliveryRepository deliveryRepository = DeliveryApplication.applicationContext.getBean(DeliveryRepository.class);
        return deliveryRepository;
    }




    public static void deliveryStart(OrderPlaced orderPlaced){

        /** Example 1:  new item 
        Delivery delivery = new Delivery();
        repository().save(delivery);

        DeleveryStarted deleveryStarted = new DeleveryStarted(delivery);
        deleveryStarted.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(orderPlaced.get???()).ifPresent(delivery->{
            
            delivery // do something
            repository().save(delivery);

            DeleveryStarted deleveryStarted = new DeleveryStarted(delivery);
            deleveryStarted.publishAfterCommit();

         });
        */

        
    }
    public static void deliveryCancel(OrderCanceled orderCanceled){

        /** Example 1:  new item 
        Delivery delivery = new Delivery();
        repository().save(delivery);

        DeliveryCanceled deliveryCanceled = new DeliveryCanceled(delivery);
        deliveryCanceled.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(orderCanceled.get???()).ifPresent(delivery->{
            
            delivery // do something
            repository().save(delivery);

            DeliveryCanceled deliveryCanceled = new DeliveryCanceled(delivery);
            deliveryCanceled.publishAfterCommit();

         });
        */

        
    }


}
