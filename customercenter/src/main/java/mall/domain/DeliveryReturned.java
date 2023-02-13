package mall.domain;

import mall.infra.AbstractEvent;
import lombok.Data;
import java.util.*;


@Data
public class DeliveryReturned extends AbstractEvent {

    private Long id;
    private Long orderId;
    private Long productId;
    private Long qty;
    private String productName;
    private String status;
}
