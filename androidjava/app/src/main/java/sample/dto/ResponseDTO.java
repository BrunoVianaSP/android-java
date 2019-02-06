package sample.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class ResponseDTO<T> implements Serializable {
    private long status;
    private String message;
    private BodyDTO<T> body;
}
