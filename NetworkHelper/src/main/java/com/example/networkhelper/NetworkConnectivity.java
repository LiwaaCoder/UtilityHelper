package com.example.networkhelper;

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

    public void getNetworkSpeed(NetworkSpeedCallback callback) {
        Executors.newSingleThreadExecutor().submit(() -> {
            try {
                String host = InetAddress.getByName("www.google.com").getHostAddress();
                long startTime = System.currentTimeMillis();
                Process process = Runtime.getRuntime().exec("/system/bin/ping -c 1 -w " + TIMEOUT_MS + " " + host);
                int exitCode = process.waitFor();
                long endTime = System.currentTimeMillis();
                if (exitCode == 0) {
                    double timeTaken = (double) (endTime - startTime) / 1000;
                    double speed = (double) (8000) / timeTaken; // Speed in bits per second
                    BigDecimal bd = new BigDecimal(speed).setScale(2, RoundingMode.HALF_UP);
                    double roundedSpeed = bd.doubleValue() / 1000000; // Speed in Mbps
                    callback.onSpeedReceived(roundedSpeed);
                } else {
                    callback.onSpeedReceived(-1);
                }
            } catch (UnknownHostException e) {
                Log.e(TAG, "UnknownHostException: " + e.getMessage());
                callback.onSpeedReceived(-1);
            } catch (IOException e) {
                Log.e(TAG, "IOException: " + e.getMessage());
                callback.onSpeedReceived(-1);
            } catch (InterruptedException e) {
                Log.e(TAG, "InterruptedException: " + e.getMessage());
                callback.onSpeedReceived(-1);
            }
        });
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
