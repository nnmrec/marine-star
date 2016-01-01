package macro;

import java.io.*;
import java.util.*;
import java.util.logging.*;
import star.common.*;
import star.base.neo.*;

public class probeCreationFromCSV extends StarMacro {

    public void execute() {

// the file should NOT have any empty lines at top or bottom (improve his later by having a name column and rename in STAR)
        String path = "../inputs/probes.csv";
        String region = "Block";

        Simulation simulation_0 = getActiveSimulation();

// Default name of the region is "Block". Enter the input part of your probe points.
        Region region_0 =
                simulation_0.getRegionManager().getRegion(region);

        File f = new File(path);
        try {

            FileReader fr = new FileReader(f);

            Scanner sc = new Scanner(fr);

            String coord = "";

// read the line and stop at the end of the file
            while (sc.hasNextLine()) {

                coord = sc.nextLine();

                double x = Double.parseDouble(coord.split(",")[0]);
                double y = Double.parseDouble(coord.split(",")[1]);
                double z = Double.parseDouble(coord.split(",")[2]);

                simulation_0.println("Probe point created at (" + x + "," + y + "," + z + ")");
// create the probe point
                simulation_0.getPartManager().createPointPart(new NeoObjectVector(new Object[]{region_0}), new DoubleVector(new double[]{x, y, z}));
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(probeCreationFromCSV.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

