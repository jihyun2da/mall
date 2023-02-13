package mall.domain;

import mall.domain.*;
import mall.infra.AbstractEvent;
import java.util.*;
import lombok.*;


@Data
@ToString
public class DeleveryStarted extends AbstractEvent {

    private Long id;
    private Long orderId;
    private Long productId;
    private Long qty;
    private String productName;
    private String status;

    public DeleveryStarted(Delivery aggregate){
        super(aggregate);
    }
    public DeleveryStarted(){
        super();
    }
}
