package sample.debt;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import sample.dev.R;
import sample.model.Debt;
import sample.populator.DebtPopulator;
import sample.view.DebtItemView;

public class DebtRecyclerViewAdapter extends RecyclerView.Adapter<DebtItemView> {

    private final List<Debt> mValues;
    private final DebtViewFragment.DebtViewFragmentListener mListener;
    private final DebtPopulator populator = new DebtPopulator();

    public DebtRecyclerViewAdapter(List<Debt> items, DebtViewFragment.DebtViewFragmentListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public DebtItemView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_debt_view, parent, false);
        return new DebtItemView(view);
    }

    @Override
    public void onBindViewHolder(final DebtItemView holder, int position) {
        holder.mItem = mValues.get(position);

        populator.populate(holder.mItem, holder);


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.details(holder.mItem);
            }
        });


        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    mListener.delete(holder.mItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }
}
