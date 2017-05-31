package com.zeph.dailypicture;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

  private ImageView mImageView;

  private BottomNavigationView navigation;



  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    // 初始化界面
    initView();
    // 加载每日一图
    bingPicture();
    // 给navigation设置监听
    navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);



  }

  /**
   * 初始化界面方法
   */
  private void initView() {
    mImageView = (ImageView) findViewById(R.id.picture);
    navigation = (BottomNavigationView) findViewById(R.id.navigation);
  }


  /**
   * bing每日一图方法
   */
  private void bingPicture() {
    String bingUrl = "http://guolin.tech/api/bing_pic";
    final OkHttpClient client = new OkHttpClient();
    final Request request = new Request.Builder().url(bingUrl).build();
    client.newCall(request).enqueue(new Callback() {
      @Override
      public void onFailure(Call call, IOException e) {
        e.printStackTrace();
      }

      @Override
      public void onResponse(Call call, Response response) throws IOException {
        final String picture = response.body().string();
        runOnUiThread(new Runnable() {
          @Override
          public void run() {
            Glide.with(MainActivity.this).load(picture).into(mImageView);
          }
        });
      }
    });
  }

  /**
   * Navigation监听方法
   */
  private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
      = new BottomNavigationView.OnNavigationItemSelectedListener() {

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
      switch (item.getItemId()) {
        case R.id.navigation_home:
          return true;
        case R.id.navigation_dashboard:
          return true;
        case R.id.navigation_notifications:
          return true;
      }
      return false;
    }

  };

}
