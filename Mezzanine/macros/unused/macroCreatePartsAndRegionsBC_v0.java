// STAR-CCM+ macro: macroCreatePartsAndRegionsBC_v0.java
// Written by STAR-CCM+ 10.06.009
package macro;

import java.util.*;

import star.turbulence.*;
import star.kwturb.*;
import star.common.*;
import star.base.neo.*;
import star.flow.*;

public class macroCreatePartsAndRegionsBC_v0 extends StarMacro {

  public void execute() {
    execute0();
  }

  private void execute0() {

    Simulation simulation_0 = 
      getActiveSimulation();

    Region region_0 = 
      simulation_0.getRegionManager().getRegion("Block");

    Boundary boundary_0 = 
      region_0.getBoundaryManager().getBoundary("Inlet");

    boundary_0.getConditions().get(KwTurbSpecOption.class).setSelected(KwTurbSpecOption.Type.INTENSITY_LENGTH_SCALE);

    boundary_0.getConditions().get(InletVelocityOption.class).setSelected(InletVelocityOption.Type.COMPONENTS);

    TurbulenceIntensityProfile turbulenceIntensityProfile_0 = 
      boundary_0.getValues().get(TurbulenceIntensityProfile.class);

    turbulenceIntensityProfile_0.getMethod(ConstantScalarProfileMethod.class).getQuantity().setValue(0.1);

    TurbulentLengthScaleProfile turbulentLengthScaleProfile_0 = 
      boundary_0.getValues().get(TurbulentLengthScaleProfile.class);

    turbulentLengthScaleProfile_0.getMethod(ConstantScalarProfileMethod.class).getQuantity().setValue(3.125);

    VelocityProfile velocityProfile_0 = 
      boundary_0.getValues().get(VelocityProfile.class);

    velocityProfile_0.getMethod(ConstantVectorProfileMethod.class).getQuantity().setComponents(1.0, 0.0, 0.0);

    Boundary boundary_1 = 
      region_0.getBoundaryManager().getBoundary("Left Bank");

    boundary_1.getConditions().get(WallShearStressOption.class).setSelected(WallShearStressOption.Type.SLIP);

    Boundary boundary_2 = 
      region_0.getBoundaryManager().getBoundary("Right Bank");

    boundary_2.getConditions().get(WallShearStressOption.class).setSelected(WallShearStressOption.Type.SLIP);

    Boundary boundary_3 = 
      region_0.getBoundaryManager().getBoundary("Sea Surface");

    boundary_3.getConditions().get(WallShearStressOption.class).setSelected(WallShearStressOption.Type.SLIP);

    Boundary boundary_4 = 
      region_0.getBoundaryManager().getBoundary("Outlet");

    boundary_4.getConditions().get(KwTurbSpecOption.class).setSelected(KwTurbSpecOption.Type.INTENSITY_LENGTH_SCALE);

    TurbulenceIntensityProfile turbulenceIntensityProfile_1 = 
      boundary_4.getValues().get(TurbulenceIntensityProfile.class);

    turbulenceIntensityProfile_1.getMethod(ConstantScalarProfileMethod.class).getQuantity().setValue(0.1);

    TurbulentLengthScaleProfile turbulentLengthScaleProfile_1 = 
      boundary_4.getValues().get(TurbulentLengthScaleProfile.class);

    turbulentLengthScaleProfile_1.getMethod(ConstantScalarProfileMethod.class).getQuantity().setValue(3.125);

    Boundary boundary_5 = 
      region_0.getBoundaryManager().getBoundary("Seabed");

    boundary_5.getConditions().get(WallShearStressOption.class).setSelected(WallShearStressOption.Type.SLIP);

    boundary_5.getConditions().get(WallShearStressOption.class).setSelected(WallShearStressOption.Type.NO_SLIP);

    boundary_5.getConditions().get(WallSurfaceOption.class).setSelected(WallSurfaceOption.Type.ROUGH);

    RoughnessHeightProfile roughnessHeightProfile_0 = 
      boundary_5.getValues().get(RoughnessHeightProfile.class);

    roughnessHeightProfile_0.getMethod(ConstantScalarProfileMethod.class).getQuantity().setValue(0.01);

    region_0.getConditions().get(TwoEquationTurbulenceUserSourceOption.class).setSelected(TwoEquationTurbulenceUserSourceOption.Type.AMBIENT);

    KwAmbientTurbulenceSpecification kwAmbientTurbulenceSpecification_0 = 
      region_0.getValues().get(KwAmbientTurbulenceSpecification.class);

    kwAmbientTurbulenceSpecification_0.setInflowBoundary(boundary_0);
  }
}
