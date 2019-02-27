package sample.populator;

import java.time.LocalDateTime;

import sample.model.Debt;
import sample.view.DebtCreateView;
import sample.view.DebtItemView;

public class DebtPopulator {

    public Debt populate(DebtCreateView source) {
        Debt debt = new Debt();
//        debt.setDate(source.date.getDate());
        debt.setDate(LocalDateTime.now().toString());
        debt.setName(source.name.getText().toString());
        debt.setCategory(source.category.getText().toString());
        debt.setSubcategory(source.subcategory.getText().toString());
        debt.setPrice(Double.parseDouble(source.price.getText().toString()));
        debt.setSeller(source.seller.getText().toString());
        debt.setStatus(source.status.getText().toString());
        return debt;
    }

    public void populate(Debt source, DebtItemView target) {
//        target.date.setText(source.getDate().toString());
        target.name.setText(source.getName());
        target.category.setText(source.getCategory());
        target.subcategory.setText(source.getSubcategory());
        target.price.setText(String.valueOf(source.getPrice()) );
        target.seller.setText(source.getSeller());
//        target.status.setText(source.getStatus());
    }
}
