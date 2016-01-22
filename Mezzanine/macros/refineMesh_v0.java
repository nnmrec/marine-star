// STAR-CCM+ macro: refineMesh_v0.java
// Written by STAR-CCM+ 10.06.010
package macro;

import java.util.*;

import star.common.*;
import star.trimmer.*;
import star.meshing.*;

public class refineMesh_v0 extends StarMacro {

  public void execute() {
    execute0();
  }

  private void execute0() {

    Simulation simulation_0 = 
      getActiveSimulation();

    AutoMeshOperation autoMeshOperation_0 = 
      ((AutoMeshOperation) simulation_0.get(MeshOperationManager.class).getObject("Automated Mesh"));

    TrimmerAutoMesher trimmerAutoMesher_0 = 
      ((TrimmerAutoMesher) autoMeshOperation_0.getMeshers().getObject("Trimmed Cell Mesher"));

    XyzInternalTable xyzInternalTable_2 = 
      ((XyzInternalTable) simulation_0.getTableManager().getTable("Turb Intensity Ratio Function Table"));

    trimmerAutoMesher_0.setMeshSizeTable(xyzInternalTable_2);
  }
}
