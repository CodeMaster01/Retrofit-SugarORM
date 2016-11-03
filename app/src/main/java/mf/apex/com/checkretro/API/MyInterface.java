package mf.apex.com.checkretro.API;

import mf.apex.com.checkretro.DB.PostResponce;
import retrofit.http.GET;
import rx.Observable;

/**
 * Created by user on 05/07/2016.
 */
public interface MyInterface {

    @GET("users.php?uid=15") //users web service,return all users base on id=15 (add your web service here)
   Observable<PostResponce> getFiller();

}
