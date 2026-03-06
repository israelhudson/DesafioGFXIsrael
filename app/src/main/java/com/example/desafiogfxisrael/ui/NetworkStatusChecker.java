package com.example.desafiogfxisrael.ui;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;

public final class NetworkStatusChecker {
    private NetworkStatusChecker() {
    }

    public static boolean isConnected(Context context) {
        try {
            ConnectivityManager connectivityManager =
                    (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager == null) {
                return false;
            }

            Network activeNetwork = connectivityManager.getActiveNetwork();
            if (activeNetwork == null) {
                return false;
            }

            NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(activeNetwork);
            if (capabilities == null) {
                return false;
            }

            return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                    || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                    || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET);
        } catch (SecurityException exception) {
            return false;
        }
    }
}
