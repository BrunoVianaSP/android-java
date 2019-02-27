package sample.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Debt implements Serializable {
    private String id;
    private String date;
    private String day;
    private String name;
    private String category;
    private String subcategory;
    private String seller;
    private Double price;
    private String status;
}
