package com.example.shortvideo;
import android.animation.Animator;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import java.util.Timer;
import java.util.TimerTask;
public class videoActivity extends AppCompatActivity {
    private MyVideo video;
    private TextView text;
    private LottieAnimationView anim;
    private ImageView avaImage;
    private ImageView whiteHeart;
    private ImageView redHeart;
    public int flag = 0;
    public int twice = 0;
    public static final String TAG = "zlj";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_item);
        Bundle bundle = getIntent().getExtras();
        String url = bundle.getString("url");
        String describe  = bundle.getString("describe");
        String avatar = bundle.getString("avatar");
        Log.i(TAG, "url:" + url);
        video = findViewById(R.id.videoView);
        text = findViewById(R.id.textView);
        anim = findViewById(R.id.animation_view);
        avaImage = findViewById(R.id.iv_avatar);
        whiteHeart = findViewById(R.id.whiteHeart);
        redHeart = findViewById(R.id.redHeart);
        anim.setVisibility(View.GONE);
        redHeart.setVisibility(View.GONE);
        text.setText(describe);
        Glide.with(this).load(avatar)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(avaImage);
        video.setVideoPath(url);
        video.start();
        video.setOnClickListener(v -> {
            new Timer().schedule(new TimerTask() {
                public void run() {
                    if(flag == 0 && twice == 0){
                        Log.i(TAG, "in single click");
                        if (video.isPlaying()){
                            Log.i(TAG, "in videoPlay");
                            video.pause();
                        }else {
                            video.start();
                        }
                    }
                    else if(flag == 0 && twice == 1){
                        Log.i(TAG, "in set twice 0");
                        twice = 0;
                    }
                    else{
                        Log.i(TAG, "in set 0");
                        flag = 0;
                    }
                }
            }, 500);
        });
        video.setOnCompletionListener(mp -> {
            mp.start();
            mp.setLooping(true);
        });
        video.setOnDoubleClickListener(v -> {
            Log.i(TAG, "in double click");
            flag = 1;
            twice = 1;
            whiteHeart.setVisibility(View.GONE);
            redHeart.setVisibility(View.VISIBLE);
            anim.setVisibility(View.VISIBLE);
            anim.addAnimatorListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    anim.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            anim.playAnimation();
        });
    }
}
