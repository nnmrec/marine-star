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

    // StepStoppingCriterion stepStoppingCriterion_0 = 
    //   ((StepStoppingCriterion) simulation_0.getSolverStoppingCriterionManager().getSolverStoppingCriterion("Maximum Steps"));

    // stepStoppingCriterion_0.setMaximumNumberSteps(10);

    // ResidualPlot residualPlot_0 = 
    //   ((ResidualPlot) simulation_0.getPlotManager().getPlot("Residuals"));

    // residualPlot_0.open();

    simulation_0.getSimulationIterator().run();
  }
}

