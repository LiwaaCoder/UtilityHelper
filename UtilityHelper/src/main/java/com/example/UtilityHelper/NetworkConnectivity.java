package com.example.UtilityHelper;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.TrafficStats;
import android.os.Build;
import android.util.Log;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class NetworkConnectivity {

    private Context context;
    private final String TAG = "NetworkConnectivity";
    private final int TIMEOUT_MS = 3000;
    private final String URL_TO_PING = "https://www.google.com";

    public NetworkConnectivity(Context context) {
        this.context = context;
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Network network = connectivityManager.getActiveNetwork();
            NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(network);
            return capabilities != null && (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR));
        } else {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isConnected();
        }
    }


    public int getNetworkType() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Network network = connectivityManager.getActiveNetwork();
            NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(network);
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    return ConnectivityManager.TYPE_WIFI;
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    return ConnectivityManager.TYPE_MOBILE;
                } else {
                    return -1;
                }
            } else {
                return -1;
            }
        } else {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null) {
                return networkInfo.getType();
            } else {
                return -1;
            }
        }
    }

    public String getIpAddress() {
        try {
            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface networkInterface : interfaces) {
                List<InetAddress> addresses = Collections.list(networkInterface.getInetAddresses());
                for (InetAddress address : addresses) {
                    if (!address.isLoopbackAddress() && address instanceof Inet4Address) {
                        return address.getHostAddress();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public long getNetworkUsage() {
        long usage = TrafficStats.getTotalRxBytes() + TrafficStats.getTotalTxBytes();
        return usage;
    }



    public interface NetworkSpeedCallback {
        void onSpeedReceived(double speedMbps);
    }
}
