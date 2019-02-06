package sample.dto;

import java.util.List;

import lombok.Data;

@Data
public class BodyDTO<T> {
    private List<T> list;
    private double total;
    private double paid;
    private double unpaid;
    private long items;
}
