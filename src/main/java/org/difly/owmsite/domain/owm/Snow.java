package org.difly.owmsite.domain.owm;

public class Snow {
    private int h1;
    private int h3;

    public int getH1() {
        return h1;
    }

    public void setH1(int h1) {
        this.h1 = h1;
    }

    public int getH3() {
        return h3;
    }

    public void setH3(int h3) {
        this.h3 = h3;
    }

    @Override
    public String toString() {
        return getH1() + ", " + getH3();
    }
}
