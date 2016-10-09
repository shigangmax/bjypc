package api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by root on 16-10-6.
 */

public interface ApiService {
    @GET("/todayOnhistory/queryEvent.php")
    Call<ResponseBody> contributorsBySimpleGetCall(@Query("key") String key,@Query("date") String date);
}
