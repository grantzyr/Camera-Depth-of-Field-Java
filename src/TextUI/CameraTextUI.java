package TextUI;

import Model.Lens;
import Model.LensManager;
import Model.Calculator;
import java.text.DecimalFormat;
import java.util.Scanner;

public class CameraTextUI {
    private static final double COC = 0.029;    // "Circle of Confusion" for a "Full Frame" camera
    private LensManager manager;
    private Scanner in = new Scanner(System.in);// Read from keyboard
    //private Calculator DOF;

    public CameraTextUI(LensManager manager) {
        // Accept and store a reference to the lens manager (the model)
        // (this design is called "dependency injection")
        this.manager = manager;

        // Populate lenses (Make, max aperture (smallest supported F number), focal length [mm]):
        manager.add(new Lens("Canon", 1.8, 50));
        manager.add(new Lens("Tamron", 2.8, 90));
        manager.add(new Lens("Sigma", 2.8, 200));
        manager.add(new Lens("Nikon", 4, 200));
    }

    public void show() {
        boolean isDone = false;
        while (!isDone) {
            System.out.println("Lenses to pick from: ");
            int i = 0;

            for (Lens lenses : manager) {
                System.out.println("  " + i + ". " + lenses);
                i++;
            }
            System.out.println("  (-1 to exit)");

            System.out.print(": ");
            int choice = in.nextInt();

            if (choice == -1) {

                isDone = true;
                break;

            } else if (choice >= 0 && choice < manager.size()) {

                System.out.print("Aperture [the F number]: ");
                double aperture = in.nextDouble();
                Lens selectedLens = manager.getLens(choice);

                if (aperture < selectedLens.getMaxAperture()) {
                    System.out.println("ERROR: This aperture is not possible with this lens.\n");
                } else {
                    System.out.print("Distance to subject [m]: ");
                    double distance = in.nextDouble();
                    Calculator DOF = new Calculator();
                    double hyperFocal = DOF.getHyperFocal(selectedLens, aperture, COC);
                    double nearFocal = DOF.getNearFocal(selectedLens, hyperFocal, distance);
                    double farFocal = DOF.getFarFocal(selectedLens, hyperFocal, distance);
                    double depthOfField = DOF.getDepthOfField(farFocal,nearFocal);

                    System.out.println("  In focus: " +
                            formatM(nearFocal) + "m to " + formatM(farFocal) +
                            "m [DOF = " + formatM(depthOfField) + "m]" );
                    System.out.println("  Hyperfocal point: " + formatM(hyperFocal) + "m");
                    System.out.print("\n");
                }
            } else {
                System.out.println("Error: Invalid lens index.\n");
            }

        }
    }

    private String formatM(double distanceInM) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(distanceInM);
    }
}
