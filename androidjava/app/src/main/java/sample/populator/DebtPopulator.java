package sample.populator;

import java.time.LocalDateTime;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

import sample.model.Debt;
import sample.view.DebtCreateView;

public class DebtPopulator {

    public Debt populate(DebtCreateView source) {
        Debt debt = new Debt();
        debt.setDate(source.date.getDate());
        debt.setName(source.name.toString());
        debt.setCategory(source.category.toString());
        debt.setSubcategory(source.subcategory.toString());
        debt.setPrice(Float.parseFloat(source.price.toString()));
        debt.setSeller(source.seller.toString());
        debt.setStatus(source.status.toString());
        return debt;
    }
}
