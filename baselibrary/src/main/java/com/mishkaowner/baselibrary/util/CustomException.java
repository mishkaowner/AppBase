package com.mishkaowner.baselibrary.util;

import java.io.IOException;

public class CustomException extends IOException {
    private String customMsg;

    public CustomException(String customMsg) {
        this.customMsg = customMsg;
    }

    @Override
    public String getMessage() {
        return customMsg;
    }
}
