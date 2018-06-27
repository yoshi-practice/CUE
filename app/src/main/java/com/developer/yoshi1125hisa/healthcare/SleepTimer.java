package com.developer.yoshi1125hisa.healthcare;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@IgnoreExtraProperties
public class SleepTimer {
    public String sleepTime;
    public String key;
    public SleepTimer() {}

    public SleepTimer(String sleepTime) {
        this.sleepTime = sleepTime;
    }

    @Override
    public String toString() {
        return sleepTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SleepTimer that = (SleepTimer) o;
        return Objects.equals(sleepTime, that.sleepTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(sleepTime);
    }

    public Map<String, Object> toMap() {
        return new HashMap<String, Object>() {{
            put("sleepTime", sleepTime);
        }};
    }
}
