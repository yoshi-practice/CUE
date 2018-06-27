package com.developer.yoshi1125hisa.healthcare;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@IgnoreExtraProperties
public class PointCounter {
    public String pointCount;
    public String key;
    public PointCounter() {}

    public PointCounter(String pointCount) {
        this.pointCount = pointCount;
    }

    @Override
    public String toString() {
        return pointCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PointCounter that = (PointCounter) o;
        return Objects.equals(pointCount, that.pointCount);
    }

    @Override
    public int hashCode() {

        return Objects.hash(pointCount);
    }

    public Map<String, Object> toMap() {
        return new HashMap<String, Object>() {{
            put("sleepTime", pointCount);
        }};
    }
}
