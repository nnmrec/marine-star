// STAR-CCM+ macro: setmacroRunANDCrit.java
// Written by STAR-CCM+ 10.06.010
package macro;

import java.util.*;

import star.common.*;
import star.base.neo.*;

public class setmacroRunANDCrit extends StarMacro {

  public void execute() {
    execute0();
  }

  private void execute0() {

    Simulation simulation_0 = 
      getActiveSimulation();

    MonitorIterationStoppingCriterion monitorIterationStoppingCriterion_0 = 
      ((MonitorIterationStoppingCriterion) simulation_0.getSolverStoppingCriterionManager().getSolverStoppingCriterion("Continuity Criterion"));

    monitorIterationStoppingCriterion_0.getLogicalOption().setSelected(SolverStoppingCriterionLogicalOption.Type.AND);

    monitorIterationStoppingCriterion_0.setInnerIterationCriterion(false);

    monitorIterationStoppingCriterion_0.setInnerIterationCriterion(true);

    monitorIterationStoppingCriterion_0.setOuterIterationCriterion(true);

    monitorIterationStoppingCriterion_0.setOuterIterationCriterion(false);

    monitorIterationStoppingCriterion_0.setOuterIterationCriterion(true);

    MonitorIterationStoppingCriterionMinLimitType monitorIterationStoppingCriterionMinLimitType_0 = 
      ((MonitorIterationStoppingCriterionMinLimitType) monitorIterationStoppingCriterion_0.getCriterionType());

    monitorIterationStoppingCriterionMinLimitType_0.getLimit().setValue(0.001);

    StepStoppingCriterion stepStoppingCriterion_0 = 
      ((StepStoppingCriterion) simulation_0.getSolverStoppingCriterionManager().getSolverStoppingCriterion("Maximum Steps"));

    stepStoppingCriterion_0.setMaximumNumberSteps(50);

    stepStoppingCriterion_0.getLogicalOption().setSelected(SolverStoppingCriterionLogicalOption.Type.OR);

    stepStoppingCriterion_0.getLogicalOption().setSelected(SolverStoppingCriterionLogicalOption.Type.AND);
  }
}
