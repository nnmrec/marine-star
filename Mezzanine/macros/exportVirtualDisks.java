// STAR-CCM+ macro: exportVirtualDisks.java
// Written by STAR-CCM+ 10.06.010
// 
// license: ?
// 
package macro;

import java.util.*;
import star.common.*;
import star.base.neo.*;

public class exportVirtualDisks extends StarMacro {

    ///////////////////////////////////////////////////////////////////////////////
    // USER INPUTS
    //
    String path = "../outputs/rotors-velocity.csv";
    ///////////////////////////////////////////////////////////////////////////////
    
    public void execute() {

        Simulation simulation_0 = getActiveSimulation();

        MonitorPlot monitorPlot_0 = 
          ((MonitorPlot) simulation_0.getPlotManager().getPlot("rotors-inflow"));

        monitorPlot_0.export(resolvePath(path), ",");
    } 
}
