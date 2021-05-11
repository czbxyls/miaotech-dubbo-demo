package com.miaotech.common.result;

import java.io.Serializable;

public final class Pair<F, S> implements Serializable {
    private static final long serialVersionUID = -1037592552817068942L;

    public F first;

    public S second;

    public Pair() {}

    public Pair(F f, S s) {
        this.first = f;
        this.second = s;
    }

    public static <FT, ST> Pair<FT, ST> makePair(FT f, ST s) {
        return new Pair<>(f, s);
    }

    private static <T> boolean eq(T o1, T o2) {
        return (o1 == null) ? ((o2 == null)) : o1.equals(o2);
    }

    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (!(o instanceof Pair))
            return false;
        Pair<F, S> pr = (Pair<F, S>)o;
        return (eq(this.first, pr.first) && eq(this.second, pr.second));
    }

    private static int h(Object o) {
        return (o == null) ? 0 : o.hashCode();
    }

    public int hashCode() {
        int seed = h(this.first);
        seed ^= h(this.second) + -1640531527 + (seed << 6) + (seed >> 2);
        return seed;
    }

    public F getFirst() {
        return this.first;
    }

    public S getSecond() {
        return this.second;
    }

    public void setFirst(F first) {
        this.first = first;
    }

    public void setSecond(S second) {
        this.second = second;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{").append(this.first).append(", ").append(this.second).append("}");
        return sb.toString();
    }
}
