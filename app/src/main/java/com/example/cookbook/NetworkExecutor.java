package com.example.cookbook;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class NetworkExecutor {
    private static NetworkExecutor instance;

    public NetworkExecutor(){}

    public static NetworkExecutor getInstance(){
        if(instance == null){
            instance = new NetworkExecutor();
        }
        return instance;
    };

    private final ScheduledExecutorService mNetworkIO = Executors.newScheduledThreadPool(3);
    public ScheduledExecutorService getNetworkExecutor(){
        return mNetworkIO;
    }
}
