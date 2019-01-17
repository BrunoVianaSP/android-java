package sample.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Debt {
    private LocalDateTime date;
    private String day;
    private String item;
    private String category;
    private String subcategory;
    private String seller;
    private float price;
    private String status;
}
