package org.guerrer0jaguar.inventory.merger.canonic;

import lombok.Data;

@Data
public class Reviews {
    private Integer rating;

    private String comment;

    private String date;

    private String reviewerName;

    private String reviewerEmail;

}