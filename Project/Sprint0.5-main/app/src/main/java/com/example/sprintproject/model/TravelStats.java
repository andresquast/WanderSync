package com.example.sprintproject.model;

public class TravelStats {
    private int allottedDays;
    private int plannedDays;

    public TravelStats(int allottedDays, int plannedDays) {
        this.allottedDays = allottedDays;
        this.plannedDays = plannedDays;
    }

    public int getAllottedDays() {
        return allottedDays;
    }

    public int getPlannedDays() {
        return plannedDays;
    }

    public void setAllottedDays(int days) {
        this.allottedDays = days;
    }
    public void setPlannedDays(int days) {
        this.allottedDays = days;
    }
}