package com.example.autoimageslider.ForAdmin;

public class Messages {
    private final String interaction;
    private final String visitLocation;
    private final String tripSelection;

    public Messages(String interaction, String visitLocation, String tripSelection) {
        this.interaction = interaction;
        this.visitLocation = visitLocation;
        this.tripSelection = tripSelection;
    }

    public String getInteraction() {
        return interaction;
    }

    public String getVisitLocation() {
        return visitLocation;
    }

    public String getTripSelection() {
        return tripSelection;
    }
}
