package sample.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Debt {
    private long date;
    private String day;
    private String name;
    private String category;
    private String subcategory;
    private String seller;
    private float price;
    private String status;
}
