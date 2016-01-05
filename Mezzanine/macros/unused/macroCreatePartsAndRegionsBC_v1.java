// STAR-CCM+ macro: macroCreatePartsAndRegionsBC_v1.java
// Written by STAR-CCM+ 10.06.009
package macro;

import java.util.*;

import star.turbulence.*;
import star.kwturb.*;
import star.common.*;

public class macroCreatePartsAndRegionsBC_v1 extends StarMacro {

  public void execute() {
    execute0();
  }

  private void execute0() {

    Simulation simulation_0 = 
      getActiveSimulation();

    Region region_5 = 
      simulation_0.getRegionManager().getRegion("Block");

    region_5.getConditions().get(TwoEquationTurbulenceUserSourceOption.class).setSelected(TwoEquationTurbulenceUserSourceOption.Type.AMBIENT);

    KwAmbientTurbulenceSpecification kwAmbientTurbulenceSpecification_0 = 
      region_5.getValues().get(KwAmbientTurbulenceSpecification.class);

    Boundary boundary_0 = 
      region_5.getBoundaryManager().getBoundary("Inlet");

    kwAmbientTurbulenceSpecification_0.setInflowBoundary(boundary_0);
  }
}
