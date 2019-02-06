package sample.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import sample.dev.R;
import sample.model.Debt;

public class DebtItemView extends RecyclerView.ViewHolder {
    public final View mView;

    @BindView(R.id.date)
    public TextView date;
    @BindView(R.id.name)
    public TextView name;
    @BindView(R.id.category)
    public TextView category;
    @BindView(R.id.subcategory)
    public TextView subcategory;
    @BindView(R.id.seller)
    public TextView seller;
    @BindView(R.id.price)
    public TextView price;
//    @BindView(R.id.status)
//    public TextView status;

    public Debt mItem;

    public DebtItemView(View view) {
        super(view);
        mView = view;
        ButterKnife.bind(this, view);
    }
}
