package sample.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class BaseDTO implements Serializable {
    private int status;
}
