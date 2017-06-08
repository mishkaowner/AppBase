package com.mishkaowner.baselibrary.util;

import java.io.IOException;

/**
 * Created by jhkim on 17. 4. 20.
 */

public class NoConnectivityException extends IOException {
    @Override
    public String getMessage() {
        return "サーバとの連結が切れました。ネットワークの確認の上、時間をおいて再度試してください。";
    }
}