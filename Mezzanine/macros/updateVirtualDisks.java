// STAR-CCM+ macro: updateVirtualDisks.java
// Written by STAR-CCM+ 10.06.010
// 
// license: ?
// 
package macro;

import java.io.*;
import java.util.*;
import java.util.logging.*;
import star.common.*;
import star.base.neo.*;
import star.vdm.*;

import star.vis.*;

public class updateVirtualDisks extends StarMacro {


    ///////////////////////////////////////////////////////////////////////////////
    // USER INPUTS
    //
    // path to CSV file with names and coordinates of point probes (this gets updated from the "mooring model" code) This file should NOT have any empty lines at bottom 
    String path1     = "inputs/rotors.csv";


///////////////////////////////////////////////////////////////////////////////
    // USER INPUTS
    // 
    // static final int    nVirtualDisks   = 9;        // turbine specifications
    // static final double hub_radius      = 1.0;      
    // static final double rotor_radius    = 12.5;
    // static final double rotor_thick     = 2.0;
    // static final double rotor_spacing    = 28.0;
    // static final double rotor_height     = 30.0;
    // static final double rotor_rpm       = 11.5;
    // static final double nx              = 1.0;
    // static final double ny              = 0.0;
    // static final double nz              = 0.0;
    // double[][] coords_VirtualDisks = new double[][]
    // {
    //     {187.5,   100, 44.5},
    //     {187.5,   175, 44.5},
    //     {187.5,   250, 44.5},
    //     {187.5,   325, 44.5},
    //     {187.5,   400, 44.5},
    //     {  375, 137.5, 44.5},
    //     {  375, 212.5, 44.5},
    //     {  375, 287.5, 44.5},
    //     {  375, 362.5, 44.5}
    // };

    // "the name of the File Table should also be a user input, each disk can have its own table, or use a duplicate"
    // table [U P Ct] for: TSR 

    ///////////////////////////////////////////////////////////////////////////////

    public void execute() {

        Simulation simulation_0 = getActiveSimulation();

        PhysicsContinuum physicsContinuum_0 = 
          ((PhysicsContinuum) simulation_0.getContinuumManager().getContinuum("Physics 1"));

        VirtualDiskModel virtualDiskModel_0 = 
          physicsContinuum_0.getModelManager().getModel(VirtualDiskModel.class);

        Region region_0 =
                simulation_0.getRegionManager().getRegion("Block");

        Units units_0 = 
          ((Units) simulation_0.getUnitsManager().getObject("m"));

        // now read the updated Virtual Disk coordinate files (written by "mooring model" code)
        // then recompute the tip-speed-ratio and update the rotor speed accordingly
        File f = new File(path1);
        try {

            FileReader  fr      = new FileReader(f);
            Scanner     sc      = new Scanner(fr);
            String      line    = "";

        Integer nLines = new Integer(0);
        while (sc.hasNextLine()) {
            if(nLines == 0) {
               nLines = nLines + 1;
               sc.nextLine();
               continue;
            }
            line        = sc.nextLine();
            String name = line.split(",")[0];
            double rpm  = Double.parseDouble(line.split(",")[2]);
            double x    = Double.parseDouble(line.split(",")[3]);
            double y    = Double.parseDouble(line.split(",")[4]);
            double z    = Double.parseDouble(line.split(",")[5]);

            VirtualDisk virtualDisk_0 = 
              ((VirtualDisk) virtualDiskModel_0.getVirtualDiskManager().getObject(name));

            SimpleDiskGeometry simpleDiskGeometry_0 = 
              virtualDisk_0.getComponentsManager().get(SimpleDiskGeometry.class);

            Coordinate coordinate_0 = 
              simpleDiskGeometry_0.getDiskOrigin();

            coordinate_0.setCoordinate(units_0, units_0, units_0, new DoubleVector(new double[] {x, y, z}));

            VdmRotationRateInputValue vdmRotationRateInputValue_0 = 
              virtualDisk_0.getComponentsManager().get(VdmRotationRateInputValue.class);

            vdmRotationRateInputValue_0.getRotationRate().setValue(rpm);

            simulation_0.println("Virtual Disk '" + name + "' updated coordinates (" + x + "," + y + "," + z + ")");

            simulation_0.println("Virtual Disk '" + name + "' updated rotor speed (" + rpm + ")");



        } // end while


        } catch (FileNotFoundException ex) {
            Logger.getLogger(updateVirtualDisks.class.getName()).log(Level.SEVERE, null, ex);
        }



  } // end execute0()
} // end public class

