package sample.controller;

import android.support.v7.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import sample.api.DebtApi;
import sample.dto.ResponseDTO;
import sample.model.Debt;
import sample.util.ConstantUtils;
import sample.util.NewtworkUtils;
import sample.util.ResourceUtils;

public class DebtController extends Controller {

    private final DebtApi debtApi = NewtworkUtils.createApi(ConstantUtils.SERVER_DEBTS_PATH, DebtApi.class);

    public DebtController(AppCompatActivity context) {
        super(context);
    }

    public void debts(Callback<ResponseDTO<Debt>> callback) {
        Call<ResponseDTO<Debt>> call = debtApi.debts(ResourceUtils.getBearerToken(getContext()));
        call.enqueue(callback);

    }
}
