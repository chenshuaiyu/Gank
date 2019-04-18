package com.example.chen.gank.utils;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import androidx.annotation.NonNull;

/**
 * Coder : chenshuaiyu
 * Time : 2019/1/31 20:56
 */
class DiskIOThreadExecutor implements Executor {

    private final Executor mDiskIO;

    public DiskIOThreadExecutor() {
        mDiskIO = Executors.newSingleThreadExecutor();
    }

    @Override
    public void execute(@NonNull Runnable runnable) {
        mDiskIO.execute(runnable);
    }
}
