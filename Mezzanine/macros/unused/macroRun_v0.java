// STAR-CCM+ macro: macroRun_v0.java
// Written by STAR-CCM+ 10.06.009
package macro;

import java.util.*;

import star.kwturb.*;
import star.common.*;
import star.base.neo.*;
import star.base.report.*;

public class macroRun_v0 extends StarMacro {

  public void execute() {
    execute0();
  }

  private void execute0() {

    Simulation simulation_0 = 
      getActiveSimulation();

    

    StepStoppingCriterion stepStoppingCriterion_0 = 
      ((StepStoppingCriterion) simulation_0.getSolverStoppingCriterionManager().getSolverStoppingCriterion("Maximum Steps"));

    stepStoppingCriterion_0.setIsUsed(false);

    stepStoppingCriterion_0.setIsUsed(true);

    stepStoppingCriterion_0.setMaximumNumberSteps(500);

    ResidualMonitor residualMonitor_0 = 
      ((ResidualMonitor) simulation_0.getMonitorManager().getMonitor("Continuity"));

    MonitorIterationStoppingCriterion monitorIterationStoppingCriterion_0 = 
      residualMonitor_0.createIterationStoppingCriterion();

    MonitorIterationStoppingCriterionMinLimitType monitorIterationStoppingCriterionMinLimitType_0 = 
      ((MonitorIterationStoppingCriterionMinLimitType) monitorIterationStoppingCriterion_0.getCriterionType());

    monitorIterationStoppingCriterionMinLimitType_0.getLimit().setValue(0.001);

    Solution solution_0 = 
      simulation_0.getSolution();

    solution_0.initializeSolution();

    simulation_0.getSimulationIterator().step(1);

    simulation_0.getSimulationIterator().run();

    monitorIterationStoppingCriterionMinLimitType_0.getLimit().setValue(1.1E-12);

    solution_0.initializeSolution();

    simulation_0.getSimulationIterator().step(1);

    simulation_0.getSimulationIterator().run();
  }
}
