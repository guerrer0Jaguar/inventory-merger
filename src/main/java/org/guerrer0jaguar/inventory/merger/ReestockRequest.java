package org.guerrer0jaguar.inventory.merger;

import lombok.Data;

@Data
public class ReestockRequest {
    private Long stock;
    private Long stockToFind;
}
