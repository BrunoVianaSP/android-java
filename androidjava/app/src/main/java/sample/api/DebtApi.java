package sample.api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import sample.dto.ResponseDTO;
import sample.model.Debt;

public interface DebtApi {
    @GET(" ")
    Call<ResponseDTO<Debt>> debts(@Header("Authorization") String token);

    @POST(" ")
    Call<ResponseDTO<Debt>> create(@Body Debt entity, @Header("Authorization") String token);
}
