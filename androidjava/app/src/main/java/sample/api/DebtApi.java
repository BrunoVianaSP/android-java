package sample.api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import sample.dto.ResponseDTO;
import sample.model.Debt;

public interface DebtApi {
    @GET(" ")
    Call<ResponseDTO<Debt>> debts(@Header("Authorization") String token, @Query("email") String email);
    @POST(" ")
    Call<ResponseDTO<Debt>> create(@Body Debt entity, @Header("Authorization") String token, @Query("email") String email);
    @DELETE("{id}")
    Call<ResponseDTO<Debt>> delete(@Path("id") String id, @Header("Authorization") String token);
}
