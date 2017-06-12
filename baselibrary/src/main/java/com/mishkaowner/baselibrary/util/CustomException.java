package com.mishkaowner.baselibrary.util;

import java.io.IOException;

/**
 * Created by jhkim on 17. 6. 12.
 */

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
