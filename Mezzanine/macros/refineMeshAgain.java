// STAR-CCM+ macro: refineMeshAgain.java
// Written by STAR-CCM+ 10.06.010
package macro;

import java.util.*;

import star.common.*;
import star.base.neo.*;
import star.meshing.*;

public class refineMeshAgain extends StarMacro {

  public void execute() {
    execute0();
  }

  private void execute0() {

    Simulation simulation_0 = 
      getActiveSimulation();

    UserFieldFunction userFieldFunction_3 = 
      ((UserFieldFunction) simulation_0.getFieldFunctionManager().getFunction("Turb Intensity Ratio Function"));

    // userFieldFunction_3.setDefinition("(((sqrt(2*$TurbulentKineticEnergy/3)/(mag($$Velocity)))/0.1)>1.4)?1.5:0");
      userFieldFunction_3.setDefinition("(((sqrt(2*$TurbulentKineticEnergy/3)/(mag($$Velocity)))/0.1)>1.2)?1.25:1.5");

    XyzInternalTable xyzInternalTable_2 = 
      ((XyzInternalTable) simulation_0.getTableManager().getTable("Turb Intensity Ratio Function Table"));

    xyzInternalTable_2.extract();

    MeshPipelineController meshPipelineController_0 = 
      simulation_0.get(MeshPipelineController.class);

    meshPipelineController_0.generateVolumeMesh();
  }
}
