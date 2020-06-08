package com.example.shortvideo;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
public interface videoAPI {
    //https://beiyou.bytedance.com/api/invoke/video/invoke/video
    @GET("api/invoke/video/invoke/video")
    Call<ResponseBody> getVideo();




}
