// STAR-CCM+ macro: macroPostPostProcess.java
// Written by STAR-CCM+ 10.06.009
package macro;

import java.util.*;

import star.common.*;
import star.base.neo.*;
import star.vis.*;
import star.meshing.*;

public class macroPostPostProcess extends StarMacro {

  public void execute() {
    execute0();
  }

  private void execute0() {

    Simulation simulation_0 = 
      getActiveSimulation();

    PlaneSection planeSection_0 = 
      (PlaneSection) simulation_0.getPartManager().createImplicitPart(new NeoObjectVector(new Object[] {}), new DoubleVector(new double[] {0.0, 0.0, 1.0}), new DoubleVector(new double[] {0.0, 0.0, 0.0}), 0, 1, new DoubleVector(new double[] {0.0}));

    LabCoordinateSystem labCoordinateSystem_0 = 
      simulation_0.getCoordinateSystemManager().getLabCoordinateSystem();

    CartesianCoordinateSystem cartesianCoordinateSystem_0 = 
      ((CartesianCoordinateSystem) labCoordinateSystem_0.getLocalCoordinateSystemManager().getObject("turbine 1-CSys 1"));

    planeSection_0.setCoordinateSystem(cartesianCoordinateSystem_0);

    Coordinate coordinate_0 = 
      planeSection_0.getOrientationCoordinate();

    Units units_0 = 
      ((Units) simulation_0.getUnitsManager().getObject("m"));

    coordinate_0.setCoordinate(units_0, units_0, units_0, new DoubleVector(new double[] {0.0, 1.0, 0.0}));

    planeSection_0.getInputParts().setQuery(null);

    Region region_0 = 
      simulation_0.getRegionManager().getRegion("Block");

    Boundary boundary_0 = 
      region_0.getBoundaryManager().getBoundary("Inlet");

    Boundary boundary_1 = 
      region_0.getBoundaryManager().getBoundary("Left Bank");

    Boundary boundary_2 = 
      region_0.getBoundaryManager().getBoundary("Outlet");

    Boundary boundary_3 = 
      region_0.getBoundaryManager().getBoundary("Right Bank");

    Boundary boundary_4 = 
      region_0.getBoundaryManager().getBoundary("Sea Surface");

    Boundary boundary_5 = 
      region_0.getBoundaryManager().getBoundary("Seabed");

    planeSection_0.getInputParts().setObjects(region_0, boundary_0, boundary_1, boundary_2, boundary_3, boundary_4, boundary_5);

    new StarScript(getActiveSimulation(),

                   new java.io.File(resolvePath("macroMeshAndSave.java"))).play();


    AutoMeshOperation autoMeshOperation_0 = 
      ((AutoMeshOperation) simulation_0.get(MeshOperationManager.class).getObject("Automated Mesh"));

    autoMeshOperation_0.execute();

    SimpleConePart simpleConePart_0 = 
      ((SimpleConePart) simulation_0.get(SimulationPartManager.class).getPart("refine cone: turbine 1"));

    simpleConePart_0.setRegion(region_0);

    simpleConePart_0.setDoNotRetessellate(true);

    simpleConePart_0.setCoordinateSystem(cartesianCoordinateSystem_0);

    Coordinate coordinate_1 = 
      simpleConePart_0.getStartCoordinate();

    coordinate_1.setCoordinateSystem(cartesianCoordinateSystem_0);

    coordinate_1.setCoordinate(units_0, units_0, units_0, new DoubleVector(new double[] {0.0, 0.0, 0.0}));

    simpleConePart_0.getStartRadius().setUnits(units_0);

    simpleConePart_0.getStartRadius().setValue(18.75);

    Coordinate coordinate_2 = 
      simpleConePart_0.getEndCoordinate();

    coordinate_2.setCoordinateSystem(cartesianCoordinateSystem_0);

    coordinate_2.setCoordinate(units_0, units_0, units_0, new DoubleVector(new double[] {0.0, 0.0, 500.0}));

    simpleConePart_0.getEndRadius().setUnits(units_0);

    simpleConePart_0.getEndRadius().setValue(25.0);

    simpleConePart_0.getTessellationDensityOption().setSelected(TessellationDensityOption.Type.MEDIUM);

    simpleConePart_0.rebuildSimpleShapePart();

    simpleConePart_0.setDoNotRetessellate(false);

    SimpleConePart simpleConePart_1 = 
      ((SimpleConePart) simulation_0.get(SimulationPartManager.class).getPart("refine cone: turbine 2"));

    simpleConePart_1.setRegion(region_0);

    simpleConePart_1.setDoNotRetessellate(true);

    CartesianCoordinateSystem cartesianCoordinateSystem_1 = 
      ((CartesianCoordinateSystem) labCoordinateSystem_0.getLocalCoordinateSystemManager().getObject("turbine 2-CSys 1"));

    simpleConePart_1.setCoordinateSystem(cartesianCoordinateSystem_1);

    Coordinate coordinate_3 = 
      simpleConePart_1.getStartCoordinate();

    coordinate_3.setCoordinateSystem(cartesianCoordinateSystem_1);

    coordinate_3.setCoordinate(units_0, units_0, units_0, new DoubleVector(new double[] {0.0, 0.0, 0.0}));

    simpleConePart_1.getStartRadius().setUnits(units_0);

    simpleConePart_1.getStartRadius().setValue(18.75);

    Coordinate coordinate_4 = 
      simpleConePart_1.getEndCoordinate();

    coordinate_4.setCoordinateSystem(cartesianCoordinateSystem_1);

    coordinate_4.setCoordinate(units_0, units_0, units_0, new DoubleVector(new double[] {0.0, 0.0, 500.0}));

    simpleConePart_1.getEndRadius().setUnits(units_0);

    simpleConePart_1.getEndRadius().setValue(25.0);

    simpleConePart_1.getTessellationDensityOption().setSelected(TessellationDensityOption.Type.MEDIUM);

    simpleConePart_1.rebuildSimpleShapePart();

    simpleConePart_1.setDoNotRetessellate(false);

    ResidualPlot residualPlot_0 = 
      ((ResidualPlot) simulation_0.getPlotManager().getPlot("Residuals"));

    residualPlot_0.close();
  }
}
