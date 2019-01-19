package sample.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import sample.model.Debt;

@Data
public class DebtDTO implements Serializable {
    private final List<Debt> debts;
}
