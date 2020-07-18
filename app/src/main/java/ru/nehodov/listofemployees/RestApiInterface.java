package ru.nehodov.listofemployees;

import retrofit2.Call;
import retrofit2.http.GET;
import ru.nehodov.listofemployees.models.Response;

public interface RestApiInterface {

    @GET("task.json")
    Call<Response> getAllEmployees();

}
