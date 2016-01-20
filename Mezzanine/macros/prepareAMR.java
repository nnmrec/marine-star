// STAR-CCM+ macro: prepareAMR.java
// Written by STAR-CCM+ 10.04.009
package macro;

import java.util.*;

import star.common.*;
import star.base.neo.*;
import star.trimmer.*;
import star.meshing.*;

public class prepareAMR extends StarMacro {

  public void execute() {
    execute0();
  }

  private void execute0() {

    Simulation simulation_0 = 
      getActiveSimulation();





UserFieldFunction userFieldFunction_99 = 
      simulation_0.getFieldFunctionManager().createFieldFunction();

    userFieldFunction_99.getTypeOption().setSelected(FieldFunctionTypeOption.SCALAR);

    userFieldFunction_99.setPresentationName("Turb Intensity Ratio");

    userFieldFunction_99.setFunctionName("Turb Intensity Ratio");

    Units units_1 = 
      simulation_0.getUnitsManager().getPreferredUnits(new IntVector(new int[] {0, 2, -2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}));

    Units units_0 = 
      simulation_0.getUnitsManager().getPreferredUnits(new IntVector(new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0}));


    userFieldFunction_99.setDefinition("(sqrt(2*$TurbulentKineticEnergy/3)/(mag($$Velocity))/0.1)");

    userFieldFunction_99.setIgnoreBoundaryValues(true);









    UserFieldFunction userFieldFunction_4 = 
      simulation_0.getFieldFunctionManager().createFieldFunction();

    userFieldFunction_4.getTypeOption().setSelected(FieldFunctionTypeOption.SCALAR);

    userFieldFunction_4.setPresentationName("Turb Intensity Ratio Function");

    userFieldFunction_4.setFunctionName("Turb Intensity Ratio Function");


    userFieldFunction_4.setDefinition("(((sqrt(2*$TurbulentKineticEnergy/3)/(mag($$Velocity)))/0.1)>1.19)?1.25:0");

    XyzInternalTable xyzInternalTable_2 = 
      simulation_0.getTableManager().createTable(XyzInternalTable.class);

    xyzInternalTable_2.setPresentationName("Turb Intensity Ratio Function Table");

    xyzInternalTable_2.setFieldFunctions(new NeoObjectVector(new Object[] {userFieldFunction_4}));

    Region region_0 = 
      simulation_0.getRegionManager().getRegion("Block");

    xyzInternalTable_2.getParts().setObjects(region_0);

    userFieldFunction_4.setIgnoreBoundaryValues(true);





    // xyzInternalTable_2.extract();




    // AutoMeshOperation autoMeshOperation_0 = 
    //   ((AutoMeshOperation) simulation_0.get(MeshOperationManager.class).getObject("Automated Mesh"));

    // TrimmerAutoMesher trimmerAutoMesher_0 = 
    //   ((TrimmerAutoMesher) autoMeshOperation_0.getMeshers().getObject("Trimmed Cell Mesher"));

    // trimmerAutoMesher_0.setMeshSizeTable(xyzInternalTable_2);

 




    // simulation_0.get(MeshOperationManager.class).executeAll();



    // Solution solution_0 = 
    //   simulation_0.getSolution();

    // solution_0.clearSolution(Solution.Clear.History, Solution.Clear.Fields);

    // simulation_0.getSimulationIterator().run();






    // xyzInternalTable_2.extract();

    // simulation_0.get(MeshOperationManager.class).executeAll();

    // solution_0.clearSolution(Solution.Clear.History, Solution.Clear.Fields);

    // simulation_0.getSimulationIterator().run();
  }
}
