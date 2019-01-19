package sample.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sample.api.UserAPI;
import sample.controller.Controller;
import sample.controller.DebtController;
import sample.debt.DebtViewFragment;
import sample.debt.dummy.DummyContent;
import sample.dev.R;
import sample.model.Debt;
import sample.model.User;
import sample.util.FragmentUtils;
import sample.dto.DebtDTO;


public class DebtActivity extends AppCompatActivity implements DebtViewFragment.DebtViewFragmentListener {

    protected java.util.logging.Logger log = java.util.logging.Logger.getLogger(getClass().getName());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debt);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);
        configFloatButton();
        showDebtListFragment();
    }

    private void configFloatButton() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void showDebtListFragment() {

        final DebtController debtController = new DebtController();

        Callback<DebtDTO> callback = new Callback<DebtDTO>() {
            @Override
            public void onResponse(Call<DebtDTO> call, Response<DebtDTO> response) {
                log.info("onResponse");
                DebtDTO dto = response.body();
                List<Debt> debts = dto.getDebts();
                FragmentUtils.replace( DebtActivity.this, DebtViewFragment.newInstance(1, debts), R.id.debt_view_content);
            }

            @Override
            public void onFailure(Call<DebtDTO> call, Throwable t) {

            }

        };

        debtController.debts(callback);
    }

    @Override
    public void onListFragmentInteraction(Debt item) {

    }
}
