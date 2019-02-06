package sample.controller;

import retrofit2.Call;
import retrofit2.Callback;
import sample.api.DebtApi;
import sample.dto.DebtDTO;
import sample.util.ConstantUtils;
import sample.util.NewtworkUtils;

public class DebtController {

    private final DebtApi debtApi = NewtworkUtils.createApi(ConstantUtils.SERVER_DEBTS_PATH, DebtApi.class);

    public void debts(Callback<DebtDTO> callback) {
        Call<DebtDTO> call = debtApi.debts();
        call.enqueue(callback);
    }

}
