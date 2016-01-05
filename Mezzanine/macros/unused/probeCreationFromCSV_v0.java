// STAR-CCM+ macro: probeCreationFromCSV_v0.java
// Written by STAR-CCM+ 10.06.009
package macro;

import java.util.*;

import star.common.*;
import star.base.neo.*;
import star.vis.*;

public class probeCreationFromCSV_v0 extends StarMacro {

  public void execute() {
    execute0();
  }

  private void execute0() {

    Simulation simulation_0 = 
      getActiveSimulation();

    PointPart pointPart_1 = 
      ((PointPart) simulation_0.getPartManager().getObject("Point 10"));

    pointPart_1.setPresentationName("n1");

    PointPart pointPart_2 = 
      ((PointPart) simulation_0.getPartManager().getObject("Point 11"));

    pointPart_2.setPresentationName("n");
  }
}
