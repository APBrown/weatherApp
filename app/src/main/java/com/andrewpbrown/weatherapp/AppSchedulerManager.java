package com.andrewpbrown.weatherapp;

import rx.Scheduler;
import rx.schedulers.Schedulers;

import static rx.android.schedulers.AndroidSchedulers.mainThread;

class AppSchedulerManager {
    final Scheduler mainThread;
    final Scheduler io;

    AppSchedulerManager(Scheduler mainThread, Scheduler io) {
        this.mainThread = mainThread;
        this.io = io;
    }

    static AppSchedulerManager getMainSchedulerManager() {
        return new AppSchedulerManager(mainThread(), Schedulers.io());
    }

    static AppSchedulerManager getTestSchedulerManager() {
        return new AppSchedulerManager(Schedulers.immediate(), Schedulers.immediate());
    }
}
