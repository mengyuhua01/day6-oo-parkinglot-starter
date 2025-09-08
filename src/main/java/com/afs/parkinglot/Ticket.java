package com.afs.parkinglot;

public class Ticket {
    private boolean isUsed;
    public Ticket() {
        this.isUsed = false;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setIsUsed(boolean used) {
        isUsed = used;
    }
}
