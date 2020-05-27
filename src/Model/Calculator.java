package Model;

public class Calculator {
    private double hyperFocal;
    private double nearFocal;
    private double farFocal;
    private double depthOfField;

    public double getHyperFocal (Lens lens, double aperture, double COC) {
        this.hyperFocal = (lens.getFocalLens() * lens.getFocalLens()) / (aperture * COC);
        return hyperFocal;
    }


    public double getNearFocal (Lens lens, double hyperFocal, double distance) {
        this.nearFocal = (hyperFocal * distance*1000) /  (hyperFocal + (distance*1000 - lens.getFocalLens()));
        return nearFocal/1000;
    }


    public double getFarFocal (Lens lens, double hyperFocal, double distance) {
        this.farFocal = (hyperFocal * distance*1000) /  (hyperFocal - (distance*1000 - lens.getFocalLens()));
        return farFocal/1000;
    }

    public double getDepthOfField (double farFocal, double nearFocal) {
        this.depthOfField = farFocal - nearFocal;
        return depthOfField;
    }
}

