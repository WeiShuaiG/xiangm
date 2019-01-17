package com.umeng.soexample.network;


import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.QueryName;
import retrofit2.http.Url;
import rx.Observable;

/**
 * 他用来处理接口
 */
public interface MyApiService {
    //Retrofit + Rxjava
    @GET
    Observable<ResponseBody> get(@Url String url,@QueryMap Map<String, String> map,@HeaderMap Map<String, String> headmap);

    @POST
    Observable<ResponseBody> post(@Url String url, @QueryMap Map<String, String> map,@HeaderMap Map<String, String> headmap);

    @PUT
    Observable<ResponseBody> put(@Url String url, @QueryMap Map<String, Object> map, @HeaderMap Map<String, String> headmap);

}
