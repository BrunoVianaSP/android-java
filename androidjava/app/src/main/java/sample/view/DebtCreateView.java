package sample.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CalendarView;

import butterknife.BindView;
import butterknife.ButterKnife;
import sample.dev.R;

public class DebtCreateView extends RecyclerView.ViewHolder {
    public final View mView;

    @BindView(R.id.date)
    public CalendarView date;
    @BindView(R.id.name)
    public AutoCompleteTextView name;
    @BindView(R.id.category)
    public AutoCompleteTextView category;
    @BindView(R.id.subcategory)
    public AutoCompleteTextView subcategory;
    @BindView(R.id.seller)
    public AutoCompleteTextView seller;
    @BindView(R.id.price)
    public AutoCompleteTextView price;
    @BindView(R.id.status)
    public AutoCompleteTextView status;
    @BindView(R.id.saveButton)
    public Button saveButton;

    public DebtCreateView(View view) {
        super(view);
        mView = view;
        ButterKnife.bind(this, view);
    }
}
