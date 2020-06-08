package com.example.shortvideo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity  implements MyAdapter.OnItemClickListener {
    private  ViewPager2 vp;
    private MyAdapter myAdapter;
    private List<VideoResponse>myVideos;
    public static final String TAG = "zlj";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vp = findViewById(R.id.ViewPager);
        myVideos = new ArrayList<>();
        myAdapter = new MyAdapter(this);
        getData();
        vp.setAdapter(myAdapter);
    }
    @Override
    public void onItemClick(int clickedItemIndex) {
        Bundle bundle = new Bundle();
        Log.i(TAG, "url:" + myVideos.get(clickedItemIndex).url);
        bundle.putString("url", myVideos.get(clickedItemIndex).url);
        bundle.putString("avatar", myVideos.get(clickedItemIndex).avatar);
        bundle.putString("describe", "@" + myVideos.get(clickedItemIndex).userName + "\n"+myVideos.get(clickedItemIndex).description);
        Intent intent = new Intent();
        intent.putExtras(bundle);
        intent.setClass(this, videoActivity.class);
        startActivity(intent);
    }

    private void getData() {
        Log.i(TAG, "In get data");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://beiyou.bytedance.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        videoAPI apiService = retrofit.create(videoAPI.class);
        apiService.getVideo().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String responseStr = response.body().string();
                    Log.i(TAG, "in on response"+ responseStr);
                    JSONArray responseArr = JSON.parseArray(responseStr);
                    Log.i(TAG, "JsonArray size" + responseArr.size());
                    for(int i = 0;i < responseArr.size();i++){
                        JSONObject obj = responseArr.getJSONObject(i);
                        VideoResponse tmp = new VideoResponse();
                        tmp.avatar = obj.getString("avatar");
                        Log.i(TAG, "avatar:" + tmp.avatar);
                        tmp.id = obj.getString("_id");
                        Log.i(TAG, "id:" + tmp.id);
                        tmp.description = obj.getString("description");
                        Log.i(TAG, "des:" + tmp.description);
                        tmp.likeCount = obj.getInteger("likecount");
                        Log.i(TAG, "like:" + tmp.likeCount);
                        tmp.url = obj.getString("feedurl");
                        Log.i(TAG, "url" + tmp.url);
                        tmp.userName = obj.getString("nickname");
                        Log.i(TAG, "name:" + tmp.userName);
                        myVideos.add(tmp);
                        Log.i(TAG, "list size:" + myVideos.size());
                    }
                    Log.i(TAG, "out for");
                    myAdapter.setData(myVideos);
                    myAdapter.notifyDataSetChanged();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG,"retrofit failure:"+ t.getMessage());
                StackTraceElement[] lst=t.getStackTrace();
                for (int i = 0; i < lst.length; i++) {
                    Log.d(TAG, lst[i].toString());
                }
            }
        });
    }
}
