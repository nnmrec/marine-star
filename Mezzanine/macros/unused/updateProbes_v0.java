// STAR-CCM+ macro: updateProbes_v0.java
// Written by STAR-CCM+ 10.06.009
package macro;

import java.util.*;

import star.common.*;
import star.base.neo.*;
import star.vis.*;

public class updateProbes_v0 extends StarMacro {

  public void execute() {
    execute0();
  }

  private void execute0() {

    new StarScript(getActiveSimulation(),

                   new java.io.File(resolvePath("probeCreationFromCSV.java"))).play();


    Simulation simulation_0 = 
      getActiveSimulation();

    PointPart pointPart_0 = 
      ((PointPart) simulation_0.getPartManager().getObject("name-1"));

    Coordinate coordinate_0 = 
      pointPart_0.getPointCoordinate();

    Units units_0 = 
      ((Units) simulation_0.getUnitsManager().getObject("m"));

    coordinate_0.setCoordinate(units_0, units_0, units_0, new DoubleVector(new double[] {1.0, 2.0, 3.0}));

    XyzInternalTable xyzInternalTable_0 = 
      simulation_0.getTableManager().createTable(XyzInternalTable.class);

    PointPart pointPart_1 = 
      ((PointPart) simulation_0.getPartManager().getObject("name-2"));

    PointPart pointPart_2 = 
      ((PointPart) simulation_0.getPartManager().getObject("name-3"));

    PointPart pointPart_3 = 
      ((PointPart) simulation_0.getPartManager().getObject("name-4"));

    PointPart pointPart_4 = 
      ((PointPart) simulation_0.getPartManager().getObject("name-5"));

    xyzInternalTable_0.getParts().setObjects(pointPart_0, pointPart_1, pointPart_2, pointPart_3, pointPart_4);

    PrimitiveFieldFunction primitiveFieldFunction_0 = 
      ((PrimitiveFieldFunction) simulation_0.getFieldFunctionManager().getFunction("Velocity"));

    VectorMagnitudeFieldFunction vectorMagnitudeFieldFunction_0 = 
      ((VectorMagnitudeFieldFunction) primitiveFieldFunction_0.getMagnitudeFunction());

    xyzInternalTable_0.setFieldFunctions(new NeoObjectVector(new Object[] {vectorMagnitudeFieldFunction_0}));

    xyzInternalTable_0.extract();

    xyzInternalTable_0.export(resolvePath("/mnt/data-RAID-1/danny/marine-star/Mezzanine/outputs/point-probe-extract.csv"), ",");

    TableUpdate tableUpdate_0 = 
      xyzInternalTable_0.getTableUpdate();

    tableUpdate_0.setFilePath("outputs");

    tableUpdate_0.setSaveToFile(true);

    tableUpdate_0.setSaveToFile(false);

    tableUpdate_0.setAutoExtract(false);

    xyzInternalTable_0.export(resolvePath("/mnt/data-RAID-1/danny/marine-star/Mezzanine/outputs/point-probe-extract.csv"), ",");
  }
}
