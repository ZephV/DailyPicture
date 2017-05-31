package com.zeph.dailypicture;


import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class HttpUtil {

  public static void sendOkHttpRequest(String address, okhttp3.Callback callback) throws IOException {
    OkHttpClient client = new OkHttpClient();
    Request request = new Request.Builder().url(address).build();
    client.newCall(request).enqueue(callback);
  }
}
