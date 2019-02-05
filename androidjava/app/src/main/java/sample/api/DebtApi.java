package sample.api;

import retrofit2.Call;
import retrofit2.http.GET;
import sample.dto.DebtDTO;

public interface DebtApi {
//    @GET()
//    Call<DebtDTO> debts(@Query("email") final String email);

    @GET("/")
    Call<DebtDTO> debts();
}
