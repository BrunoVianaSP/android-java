package sample.api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;
import sample.dto.DebtDTO;
import sample.model.Debt;

public interface DebtApi {
//    @GET()
//    Call<DebtDTO> debts(@Query("email") final String email);

    @GET("/")
    Call<DebtDTO> debts();
}
