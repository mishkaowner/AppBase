package com.mishkaowner.baselibrary.util;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by jhkim on 17. 4. 20.
 */

public class ConnectivityInterceptor implements Interceptor {
    private ConnectivityManager cm;

    public ConnectivityInterceptor(ConnectivityManager cm) {
        this.cm = cm;
    }

    private boolean isNetworkAvailable(){
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        if (!isNetworkAvailable()) {
            throw new NoConnectivityException();
        } else {
            Response response = chain.proceed(chain.request());
            return response;
        }
    }
}