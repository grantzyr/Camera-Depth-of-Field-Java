package Model;

import static java.lang.Double.POSITIVE_INFINITY;

public class Calculator {
    private double hyperFocal;
    private double nearFocal;
    private double farFocal;
    private double depthOfField;

    public double getHyperFocal(Lens lens, double aperture, double COC) {
        this.hyperFocal = (lens.getFocalLens() * lens.getFocalLens()) / (aperture * COC);
        return hyperFocal/1000;
    }


    public double getNearFocal(Lens lens, double hyperFocal, double distance) {
        this.nearFocal = (hyperFocal * distance) /  (hyperFocal + (distance*1000 - lens.getFocalLens())/1000);
        return nearFocal;
    }


    public double getFarFocal(Lens lens, double hyperFocal, double distance) {
        this.farFocal = (hyperFocal * distance) /  (hyperFocal - (distance*1000 - lens.getFocalLens())/1000);
        if (farFocal < 0) {
            return POSITIVE_INFINITY;
        }
        return farFocal;
    }

    public double getDepthOfField(double farFocal, double nearFocal) {
        this.depthOfField = farFocal - nearFocal;
        return depthOfField;
    }
}

