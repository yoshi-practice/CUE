package com.developer.yoshi1125hisa.healthcare;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@IgnoreExtraProperties
public class WalkCounter {
    public String walkCount;
    public String key;
    public WalkCounter() {}

    public WalkCounter(String walkCount) {
        this.walkCount = walkCount;
    }

    @Override
    public String toString() {
        return walkCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WalkCounter that = (WalkCounter) o;
        return Objects.equals(walkCount, that.walkCount);
    }

    @Override
    public int hashCode() {

        return Objects.hash(walkCount);
    }

    public Map<String, Object> toMap() {
        return new HashMap<String, Object>() {{
            put("walkCount", walkCount);
        }};
    }
}
