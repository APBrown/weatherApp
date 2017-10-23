package com.andrewpbrown.weatherapp;

import rx.Scheduler;
import rx.schedulers.Schedulers;

import static rx.android.schedulers.AndroidSchedulers.mainThread;

class AppSchedulerManager {
    final Scheduler mainThread;
    final Scheduler io;

    AppSchedulerManager() {
        this.mainThread = mainThread();
        this.io = Schedulers.io();
    }

    AppSchedulerManager(Scheduler mainThread, Scheduler io) {
        this.mainThread = mainThread;
        this.io = io;
    }

    public static AppSchedulerManager getMainSchedulerManager() {
        return new AppSchedulerManager(mainThread(), Schedulers.io());
    }

    public static AppSchedulerManager getTestSchedulerManager() {
        return new AppSchedulerManager(Schedulers.immediate(), Schedulers.immediate());
    }
}
