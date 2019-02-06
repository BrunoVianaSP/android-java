package sample.populator;

import sample.model.Debt;
import sample.view.DebtCreateView;
import sample.view.DebtItemView;

public class DebtPopulator {

    public Debt populate(DebtCreateView source) {
        Debt debt = new Debt();
//        debt.setDate(source.date.getDate());
        debt.setName(source.name.toString());
        debt.setCategory(source.category.toString());
        debt.setSubcategory(source.subcategory.toString());
        debt.setPrice(Float.parseFloat(source.price.toString()));
        debt.setSeller(source.seller.toString());
        debt.setStatus(source.status.toString());
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
