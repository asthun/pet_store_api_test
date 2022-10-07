package petstore.entities;

import lombok.*;

@Getter
@Setter


public class Order {
    private Long id;
    private Integer petId;
    private Integer quantity;
    private String shipDate;
    private String status;
    private Boolean complete;
    private String message;
}
