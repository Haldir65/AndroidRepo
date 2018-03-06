package com.bernet.hallmark

class Clock {
    long startTimeInMs

    String name

    Clock(long startTimeInMs) {
        this.startTimeInMs = startTimeInMs
    }

    long getTimeInMs() {
        return System.currentTimeMillis() - startTimeInMs
    }

    String getName() {
        return name
    }


}