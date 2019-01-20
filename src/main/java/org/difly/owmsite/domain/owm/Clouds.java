package org.difly.owmsite.domain.owm;

public class Clouds {
    //"clouds":{"all":75},
    private int all;

    public int getAll() {
        return all;
    }

    public void setAll(int all) {
        this.all = all;
    }

    @Override
    public String toString() {
        return Integer.toString(getAll());
    }
}
