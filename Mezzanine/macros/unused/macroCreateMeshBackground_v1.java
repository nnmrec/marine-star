// STAR-CCM+ macro: macroCreateMeshBackground_v1.java
// Written by STAR-CCM+ 10.06.009
package macro;

import java.util.*;

import star.common.*;
import star.base.neo.*;
import star.trimmer.*;
import star.meshing.*;

public class macroCreateMeshBackground_v1 extends StarMacro {

  public void execute() {
    execute0();
  }

  private void execute0() {

    Simulation simulation_0 = 
      getActiveSimulation();

    AutoMeshOperation autoMeshOperation_0 = 
      ((AutoMeshOperation) simulation_0.get(MeshOperationManager.class).getObject("Automated Mesh"));

    SurfaceCustomMeshControl surfaceCustomMeshControl_0 = 
      ((SurfaceCustomMeshControl) autoMeshOperation_0.getCustomMeshControls().getObject("Surface Control"));

    surfaceCustomMeshControl_0.setPresentationName("block surface");

    SurfaceCustomMeshControl surfaceCustomMeshControl_1 = 
      ((SurfaceCustomMeshControl) autoMeshOperation_0.getCustomMeshControls().getObject("Surface Control 2"));

    surfaceCustomMeshControl_1.setPresentationName("seabed surface");

    PartsCustomSimpleSurfaceGrowthRate partsCustomSimpleSurfaceGrowthRate_0 = 
      surfaceCustomMeshControl_1.getCustomValues().get(PartsCustomSimpleSurfaceGrowthRate.class);

    partsCustomSimpleSurfaceGrowthRate_0.getSurfaceGrowthRateOption().setSelected(PartsSurfaceGrowthRateOption.Type.DISABLE);

    partsCustomSimpleSurfaceGrowthRate_0.getSurfaceGrowthRateOption().setSelected(PartsSurfaceGrowthRateOption.Type.VERYSLOW);

    partsCustomSimpleSurfaceGrowthRate_0.getSurfaceGrowthRateOption().setSelected(PartsSurfaceGrowthRateOption.Type.SLOW);

    partsCustomSimpleSurfaceGrowthRate_0.getSurfaceGrowthRateOption().setSelected(PartsSurfaceGrowthRateOption.Type.MEDIUM);

    partsCustomSimpleSurfaceGrowthRate_0.getSurfaceGrowthRateOption().setSelected(PartsSurfaceGrowthRateOption.Type.FAST);

    partsCustomSimpleSurfaceGrowthRate_0.getSurfaceGrowthRateOption().setSelected(PartsSurfaceGrowthRateOption.Type.CUSTOM);
  }
}
