// STAR-CCM+ macro: macroMeshAndRun.java
// Written by STAR-CCM+ 10.04.009
package macro;

import java.util.*;

import star.common.*;
import star.base.neo.*;
import star.meshing.*;

public class macroMeshAndRun extends StarMacro {

  public void execute() {
    execute0();
  }

  private void execute0() {

    Simulation simulation_0 = 
      getActiveSimulation();

    MeshPipelineController meshPipelineController_0 = 
      simulation_0.get(MeshPipelineController.class);

    meshPipelineController_0.generateVolumeMesh();

    save(sim);

    simulation_0.getSimulationIterator().run();

    save(sim);

  }



  void save(Simulation s) {
    String simName = String.format("%s.sim", s.getPresentationName());
    s.saveState(new java.io.File(s.getSessionDir(), simName).toString());
  }


}

