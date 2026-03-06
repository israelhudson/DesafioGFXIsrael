package com.example.desafiogfxisrael.ui;

import androidx.annotation.StringRes;

import com.example.desafiogfxisrael.R;
import com.example.desafiogfxisrael.repository.RepositoryError;

public final class ErrorMessageMapper {
    private ErrorMessageMapper() {
    }

    @StringRes
    public static int toMessageRes(RepositoryError error) {
        if (error == null) {
            return R.string.error_unknown;
        }

        switch (error) {
            case NO_INTERNET:
                return R.string.error_no_internet;
            case SERVER_ERROR:
                return R.string.error_server_unavailable;
            case API_ERROR:
                return R.string.error_api_failure;
            case EMPTY_RESPONSE:
                return R.string.error_empty_products;
            default:
                return R.string.error_unknown;
        }
    }
}
