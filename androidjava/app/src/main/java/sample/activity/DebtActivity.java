package sample.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sample.controller.DebtController;
import sample.debt.DebtViewFragment;
import sample.dev.R;
import sample.dto.ResponseDTO;
import sample.model.Debt;
import sample.util.FragmentUtils;


public class DebtActivity extends AppCompatActivity implements DebtViewFragment.DebtViewFragmentListener {

    protected java.util.logging.Logger log = java.util.logging.Logger.getLogger(getClass().getName());

    private final DebtController debtController = new DebtController(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        log.info("DebtActivity onCreate");
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
        log.info("showDebtListFragment");

        final Callback<ResponseDTO<Debt>> callback = new Callback<ResponseDTO<Debt>>() {
            @Override
            public void onResponse(Call<ResponseDTO<Debt>> call, Response<ResponseDTO<Debt>> response) {
                log.info("onResponse");
                log.info("response: " + response);

                if (response.isSuccessful()) {
                    ResponseDTO<Debt> res = response.body();
                    List<Debt> debts = res.getBody().getList();
                    FragmentUtils.replace(DebtActivity.this, DebtViewFragment.newInstance(1, debts), R.id.debt_view_content);
                }
            }

            @Override
            public void onFailure(Call<ResponseDTO<Debt>> call, Throwable t) {
                log.info("onFailure: showDebtListFragment");
                log.info("call: " + call);
                t.printStackTrace();
            }

        };

        debtController.debts(callback);
    }

    @Override
    public void onListFragmentInteraction(Debt item) {

    }
}
