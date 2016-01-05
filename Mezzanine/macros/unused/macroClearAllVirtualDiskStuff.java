// STAR-CCM+ macro: macroClearAllVirtualDiskStuff.java
// Written by STAR-CCM+ 10.06.010
package macro;

import java.util.*;

import star.vdm.*;
import star.common.*;
import star.base.neo.*;
import star.vis.*;
import star.base.report.*;
import star.meshing.*;

public class macroClearAllVirtualDiskStuff extends StarMacro {

  public void execute() {
    execute0();
  }

  private void execute0() {

    Simulation simulation_0 = 
      getActiveSimulation();

    SimpleConePart simpleConePart_0 = 
      ((SimpleConePart) simulation_0.get(SimulationPartManager.class).getPart("refine cone: turbine 1"));

    SimpleConePart simpleConePart_1 = 
      ((SimpleConePart) simulation_0.get(SimulationPartManager.class).getPart("refine cone: turbine 2"));

    SimpleConePart simpleConePart_2 = 
      ((SimpleConePart) simulation_0.get(SimulationPartManager.class).getPart("refine cone: turbine 3"));

    SimpleConePart simpleConePart_3 = 
      ((SimpleConePart) simulation_0.get(SimulationPartManager.class).getPart("refine cone: turbine 4"));

    SimpleConePart simpleConePart_4 = 
      ((SimpleConePart) simulation_0.get(SimulationPartManager.class).getPart("refine cone: turbine 5"));

    SimpleConePart simpleConePart_5 = 
      ((SimpleConePart) simulation_0.get(SimulationPartManager.class).getPart("refine cone: turbine 6"));

    SimpleConePart simpleConePart_6 = 
      ((SimpleConePart) simulation_0.get(SimulationPartManager.class).getPart("refine cone: turbine 7"));

    SimpleConePart simpleConePart_7 = 
      ((SimpleConePart) simulation_0.get(SimulationPartManager.class).getPart("refine cone: turbine 8"));

    SimpleConePart simpleConePart_8 = 
      ((SimpleConePart) simulation_0.get(SimulationPartManager.class).getPart("refine cone: turbine 9"));

    SimpleCylinderPart simpleCylinderPart_0 = 
      ((SimpleCylinderPart) simulation_0.get(SimulationPartManager.class).getPart("refine cylinder: turbine 1"));

    SimpleCylinderPart simpleCylinderPart_1 = 
      ((SimpleCylinderPart) simulation_0.get(SimulationPartManager.class).getPart("refine cylinder: turbine 2"));

    SimpleCylinderPart simpleCylinderPart_2 = 
      ((SimpleCylinderPart) simulation_0.get(SimulationPartManager.class).getPart("refine cylinder: turbine 3"));

    SimpleCylinderPart simpleCylinderPart_3 = 
      ((SimpleCylinderPart) simulation_0.get(SimulationPartManager.class).getPart("refine cylinder: turbine 4"));

    SimpleCylinderPart simpleCylinderPart_4 = 
      ((SimpleCylinderPart) simulation_0.get(SimulationPartManager.class).getPart("refine cylinder: turbine 5"));

    SimpleCylinderPart simpleCylinderPart_5 = 
      ((SimpleCylinderPart) simulation_0.get(SimulationPartManager.class).getPart("refine cylinder: turbine 6"));

    SimpleCylinderPart simpleCylinderPart_6 = 
      ((SimpleCylinderPart) simulation_0.get(SimulationPartManager.class).getPart("refine cylinder: turbine 7"));

    SimpleCylinderPart simpleCylinderPart_7 = 
      ((SimpleCylinderPart) simulation_0.get(SimulationPartManager.class).getPart("refine cylinder: turbine 8"));

    SimpleCylinderPart simpleCylinderPart_8 = 
      ((SimpleCylinderPart) simulation_0.get(SimulationPartManager.class).getPart("refine cylinder: turbine 9"));

    SimpleSpherePart simpleSpherePart_0 = 
      ((SimpleSpherePart) simulation_0.get(SimulationPartManager.class).getPart("refine sphere: turbine 1"));

    SimpleSpherePart simpleSpherePart_1 = 
      ((SimpleSpherePart) simulation_0.get(SimulationPartManager.class).getPart("refine sphere: turbine 2"));

    SimpleSpherePart simpleSpherePart_2 = 
      ((SimpleSpherePart) simulation_0.get(SimulationPartManager.class).getPart("refine sphere: turbine 3"));

    SimpleSpherePart simpleSpherePart_3 = 
      ((SimpleSpherePart) simulation_0.get(SimulationPartManager.class).getPart("refine sphere: turbine 4"));

    SimpleSpherePart simpleSpherePart_4 = 
      ((SimpleSpherePart) simulation_0.get(SimulationPartManager.class).getPart("refine sphere: turbine 5"));

    SimpleSpherePart simpleSpherePart_5 = 
      ((SimpleSpherePart) simulation_0.get(SimulationPartManager.class).getPart("refine sphere: turbine 6"));

    SimpleSpherePart simpleSpherePart_6 = 
      ((SimpleSpherePart) simulation_0.get(SimulationPartManager.class).getPart("refine sphere: turbine 7"));

    SimpleSpherePart simpleSpherePart_7 = 
      ((SimpleSpherePart) simulation_0.get(SimulationPartManager.class).getPart("refine sphere: turbine 8"));

    SimpleSpherePart simpleSpherePart_8 = 
      ((SimpleSpherePart) simulation_0.get(SimulationPartManager.class).getPart("refine sphere: turbine 9"));

    simulation_0.get(SimulationPartManager.class).removeParts(new NeoObjectVector(new Object[] {simpleConePart_0, simpleConePart_1, simpleConePart_2, simpleConePart_3, simpleConePart_4, simpleConePart_5, simpleConePart_6, simpleConePart_7, simpleConePart_8, simpleCylinderPart_0, simpleCylinderPart_1, simpleCylinderPart_2, simpleCylinderPart_3, simpleCylinderPart_4, simpleCylinderPart_5, simpleCylinderPart_6, simpleCylinderPart_7, simpleCylinderPart_8, simpleSpherePart_0, simpleSpherePart_1, simpleSpherePart_2, simpleSpherePart_3, simpleSpherePart_4, simpleSpherePart_5, simpleSpherePart_6, simpleSpherePart_7, simpleSpherePart_8}));

    AutoMeshOperation autoMeshOperation_0 = 
      ((AutoMeshOperation) simulation_0.get(MeshOperationManager.class).getObject("block"));

    VolumeCustomMeshControl volumeCustomMeshControl_0 = 
      ((VolumeCustomMeshControl) autoMeshOperation_0.getCustomMeshControls().getObject("refine cone: turbine 1"));

    autoMeshOperation_0.getCustomMeshControls().remove(volumeCustomMeshControl_0);

    VolumeCustomMeshControl volumeCustomMeshControl_1 = 
      ((VolumeCustomMeshControl) autoMeshOperation_0.getCustomMeshControls().getObject("refine cone: turbine 2"));

    autoMeshOperation_0.getCustomMeshControls().remove(volumeCustomMeshControl_1);

    VolumeCustomMeshControl volumeCustomMeshControl_2 = 
      ((VolumeCustomMeshControl) autoMeshOperation_0.getCustomMeshControls().getObject("refine cone: turbine 3"));

    autoMeshOperation_0.getCustomMeshControls().remove(volumeCustomMeshControl_2);

    VolumeCustomMeshControl volumeCustomMeshControl_3 = 
      ((VolumeCustomMeshControl) autoMeshOperation_0.getCustomMeshControls().getObject("refine cone: turbine 4"));

    autoMeshOperation_0.getCustomMeshControls().remove(volumeCustomMeshControl_3);

    VolumeCustomMeshControl volumeCustomMeshControl_4 = 
      ((VolumeCustomMeshControl) autoMeshOperation_0.getCustomMeshControls().getObject("refine cone: turbine 5"));

    autoMeshOperation_0.getCustomMeshControls().remove(volumeCustomMeshControl_4);

    VolumeCustomMeshControl volumeCustomMeshControl_5 = 
      ((VolumeCustomMeshControl) autoMeshOperation_0.getCustomMeshControls().getObject("refine cone: turbine 6"));

    autoMeshOperation_0.getCustomMeshControls().remove(volumeCustomMeshControl_5);

    VolumeCustomMeshControl volumeCustomMeshControl_6 = 
      ((VolumeCustomMeshControl) autoMeshOperation_0.getCustomMeshControls().getObject("refine cone: turbine 7"));

    autoMeshOperation_0.getCustomMeshControls().remove(volumeCustomMeshControl_6);

    VolumeCustomMeshControl volumeCustomMeshControl_7 = 
      ((VolumeCustomMeshControl) autoMeshOperation_0.getCustomMeshControls().getObject("refine cone: turbine 8"));

    autoMeshOperation_0.getCustomMeshControls().remove(volumeCustomMeshControl_7);

    VolumeCustomMeshControl volumeCustomMeshControl_8 = 
      ((VolumeCustomMeshControl) autoMeshOperation_0.getCustomMeshControls().getObject("refine cone: turbine 9"));

    autoMeshOperation_0.getCustomMeshControls().remove(volumeCustomMeshControl_8);

    VolumeCustomMeshControl volumeCustomMeshControl_9 = 
      ((VolumeCustomMeshControl) autoMeshOperation_0.getCustomMeshControls().getObject("refine cylinder: turbine 1"));

    autoMeshOperation_0.getCustomMeshControls().remove(volumeCustomMeshControl_9);

    VolumeCustomMeshControl volumeCustomMeshControl_10 = 
      ((VolumeCustomMeshControl) autoMeshOperation_0.getCustomMeshControls().getObject("refine cylinder: turbine 2"));

    autoMeshOperation_0.getCustomMeshControls().remove(volumeCustomMeshControl_10);

    VolumeCustomMeshControl volumeCustomMeshControl_11 = 
      ((VolumeCustomMeshControl) autoMeshOperation_0.getCustomMeshControls().getObject("refine cylinder: turbine 3"));

    autoMeshOperation_0.getCustomMeshControls().remove(volumeCustomMeshControl_11);

    VolumeCustomMeshControl volumeCustomMeshControl_12 = 
      ((VolumeCustomMeshControl) autoMeshOperation_0.getCustomMeshControls().getObject("refine cylinder: turbine 4"));

    autoMeshOperation_0.getCustomMeshControls().remove(volumeCustomMeshControl_12);

    VolumeCustomMeshControl volumeCustomMeshControl_13 = 
      ((VolumeCustomMeshControl) autoMeshOperation_0.getCustomMeshControls().getObject("refine cylinder: turbine 5"));

    autoMeshOperation_0.getCustomMeshControls().remove(volumeCustomMeshControl_13);

    VolumeCustomMeshControl volumeCustomMeshControl_14 = 
      ((VolumeCustomMeshControl) autoMeshOperation_0.getCustomMeshControls().getObject("refine cylinder: turbine 6"));

    autoMeshOperation_0.getCustomMeshControls().remove(volumeCustomMeshControl_14);

    VolumeCustomMeshControl volumeCustomMeshControl_15 = 
      ((VolumeCustomMeshControl) autoMeshOperation_0.getCustomMeshControls().getObject("refine cylinder: turbine 7"));

    autoMeshOperation_0.getCustomMeshControls().remove(volumeCustomMeshControl_15);

    VolumeCustomMeshControl volumeCustomMeshControl_16 = 
      ((VolumeCustomMeshControl) autoMeshOperation_0.getCustomMeshControls().getObject("refine cylinder: turbine 8"));

    autoMeshOperation_0.getCustomMeshControls().remove(volumeCustomMeshControl_16);

    VolumeCustomMeshControl volumeCustomMeshControl_17 = 
      ((VolumeCustomMeshControl) autoMeshOperation_0.getCustomMeshControls().getObject("refine cylinder: turbine 9"));

    autoMeshOperation_0.getCustomMeshControls().remove(volumeCustomMeshControl_17);

    VolumeCustomMeshControl volumeCustomMeshControl_18 = 
      ((VolumeCustomMeshControl) autoMeshOperation_0.getCustomMeshControls().getObject("refine sphere: turbine 1"));

    autoMeshOperation_0.getCustomMeshControls().remove(volumeCustomMeshControl_18);

    VolumeCustomMeshControl volumeCustomMeshControl_19 = 
      ((VolumeCustomMeshControl) autoMeshOperation_0.getCustomMeshControls().getObject("refine sphere: turbine 2"));

    autoMeshOperation_0.getCustomMeshControls().remove(volumeCustomMeshControl_19);

    VolumeCustomMeshControl volumeCustomMeshControl_20 = 
      ((VolumeCustomMeshControl) autoMeshOperation_0.getCustomMeshControls().getObject("refine sphere: turbine 3"));

    autoMeshOperation_0.getCustomMeshControls().remove(volumeCustomMeshControl_20);

    VolumeCustomMeshControl volumeCustomMeshControl_21 = 
      ((VolumeCustomMeshControl) autoMeshOperation_0.getCustomMeshControls().getObject("refine sphere: turbine 4"));

    autoMeshOperation_0.getCustomMeshControls().remove(volumeCustomMeshControl_21);

    VolumeCustomMeshControl volumeCustomMeshControl_22 = 
      ((VolumeCustomMeshControl) autoMeshOperation_0.getCustomMeshControls().getObject("refine sphere: turbine 5"));

    autoMeshOperation_0.getCustomMeshControls().remove(volumeCustomMeshControl_22);

    VolumeCustomMeshControl volumeCustomMeshControl_23 = 
      ((VolumeCustomMeshControl) autoMeshOperation_0.getCustomMeshControls().getObject("refine sphere: turbine 6"));

    autoMeshOperation_0.getCustomMeshControls().remove(volumeCustomMeshControl_23);

    VolumeCustomMeshControl volumeCustomMeshControl_24 = 
      ((VolumeCustomMeshControl) autoMeshOperation_0.getCustomMeshControls().getObject("refine sphere: turbine 7"));

    autoMeshOperation_0.getCustomMeshControls().remove(volumeCustomMeshControl_24);

    VolumeCustomMeshControl volumeCustomMeshControl_25 = 
      ((VolumeCustomMeshControl) autoMeshOperation_0.getCustomMeshControls().getObject("refine sphere: turbine 8"));

    autoMeshOperation_0.getCustomMeshControls().remove(volumeCustomMeshControl_25);

    VolumeCustomMeshControl volumeCustomMeshControl_26 = 
      ((VolumeCustomMeshControl) autoMeshOperation_0.getCustomMeshControls().getObject("refine sphere: turbine 9"));

    autoMeshOperation_0.getCustomMeshControls().remove(volumeCustomMeshControl_26);

    PhysicsContinuum physicsContinuum_0 = 
      ((PhysicsContinuum) simulation_0.getContinuumManager().getContinuum("Physics 1"));

    VirtualDiskModel virtualDiskModel_0 = 
      physicsContinuum_0.getModelManager().getModel(VirtualDiskModel.class);

    VirtualDisk virtualDisk_0 = 
      ((VirtualDisk) virtualDiskModel_0.getVirtualDiskManager().getObject("turbine 3"));

    VirtualDisk virtualDisk_1 = 
      ((VirtualDisk) virtualDiskModel_0.getVirtualDiskManager().getObject("turbine 4"));

    VirtualDisk virtualDisk_2 = 
      ((VirtualDisk) virtualDiskModel_0.getVirtualDiskManager().getObject("turbine 1"));

    VirtualDisk virtualDisk_3 = 
      ((VirtualDisk) virtualDiskModel_0.getVirtualDiskManager().getObject("turbine 9"));

    VirtualDisk virtualDisk_4 = 
      ((VirtualDisk) virtualDiskModel_0.getVirtualDiskManager().getObject("turbine 5"));

    VirtualDisk virtualDisk_5 = 
      ((VirtualDisk) virtualDiskModel_0.getVirtualDiskManager().getObject("turbine 8"));

    VirtualDisk virtualDisk_6 = 
      ((VirtualDisk) virtualDiskModel_0.getVirtualDiskManager().getObject("turbine 2"));

    VirtualDisk virtualDisk_7 = 
      ((VirtualDisk) virtualDiskModel_0.getVirtualDiskManager().getObject("turbine 7"));

    VirtualDisk virtualDisk_8 = 
      ((VirtualDisk) virtualDiskModel_0.getVirtualDiskManager().getObject("turbine 6"));

    virtualDiskModel_0.getVirtualDiskManager().removeObjects(virtualDisk_0, virtualDisk_1, virtualDisk_2, virtualDisk_3, virtualDisk_4, virtualDisk_5, virtualDisk_6, virtualDisk_7, virtualDisk_8);

    ThresholdPart thresholdPart_0 = 
      ((ThresholdPart) simulation_0.getPartManager().getObject("inflow turbine 1"));

    ThresholdPart thresholdPart_1 = 
      ((ThresholdPart) simulation_0.getPartManager().getObject("inflow turbine 2"));

    ThresholdPart thresholdPart_2 = 
      ((ThresholdPart) simulation_0.getPartManager().getObject("inflow turbine 3"));

    ThresholdPart thresholdPart_3 = 
      ((ThresholdPart) simulation_0.getPartManager().getObject("inflow turbine 4"));

    ThresholdPart thresholdPart_4 = 
      ((ThresholdPart) simulation_0.getPartManager().getObject("inflow turbine 5"));

    ThresholdPart thresholdPart_5 = 
      ((ThresholdPart) simulation_0.getPartManager().getObject("inflow turbine 6"));

    ThresholdPart thresholdPart_6 = 
      ((ThresholdPart) simulation_0.getPartManager().getObject("inflow turbine 7"));

    ThresholdPart thresholdPart_7 = 
      ((ThresholdPart) simulation_0.getPartManager().getObject("inflow turbine 8"));

    ThresholdPart thresholdPart_8 = 
      ((ThresholdPart) simulation_0.getPartManager().getObject("inflow turbine 9"));

    simulation_0.getPartManager().removeObjects(thresholdPart_0, thresholdPart_1, thresholdPart_2, thresholdPart_3, thresholdPart_4, thresholdPart_5, thresholdPart_6, thresholdPart_7, thresholdPart_8);

    ThresholdPart thresholdPart_9 = 
      ((ThresholdPart) simulation_0.getPartManager().getObject("turbine 1"));

    ThresholdPart thresholdPart_10 = 
      ((ThresholdPart) simulation_0.getPartManager().getObject("turbine 2"));

    ThresholdPart thresholdPart_11 = 
      ((ThresholdPart) simulation_0.getPartManager().getObject("turbine 3"));

    ThresholdPart thresholdPart_12 = 
      ((ThresholdPart) simulation_0.getPartManager().getObject("turbine 4"));

    ThresholdPart thresholdPart_13 = 
      ((ThresholdPart) simulation_0.getPartManager().getObject("turbine 5"));

    ThresholdPart thresholdPart_14 = 
      ((ThresholdPart) simulation_0.getPartManager().getObject("turbine 6"));

    ThresholdPart thresholdPart_15 = 
      ((ThresholdPart) simulation_0.getPartManager().getObject("turbine 7"));

    ThresholdPart thresholdPart_16 = 
      ((ThresholdPart) simulation_0.getPartManager().getObject("turbine 8"));

    ThresholdPart thresholdPart_17 = 
      ((ThresholdPart) simulation_0.getPartManager().getObject("turbine 9"));

    simulation_0.getPartManager().removeObjects(thresholdPart_9, thresholdPart_10, thresholdPart_11, thresholdPart_12, thresholdPart_13, thresholdPart_14, thresholdPart_15, thresholdPart_16, thresholdPart_17);

    LabCoordinateSystem labCoordinateSystem_0 = 
      simulation_0.getCoordinateSystemManager().getLabCoordinateSystem();

    CartesianCoordinateSystem cartesianCoordinateSystem_0 = 
      ((CartesianCoordinateSystem) labCoordinateSystem_0.getLocalCoordinateSystemManager().getObject("turbine 1-CSys 1"));

    labCoordinateSystem_0.getLocalCoordinateSystemManager().remove(cartesianCoordinateSystem_0);

    CartesianCoordinateSystem cartesianCoordinateSystem_1 = 
      ((CartesianCoordinateSystem) labCoordinateSystem_0.getLocalCoordinateSystemManager().getObject("turbine 2-CSys 1"));

    labCoordinateSystem_0.getLocalCoordinateSystemManager().remove(cartesianCoordinateSystem_1);

    CartesianCoordinateSystem cartesianCoordinateSystem_2 = 
      ((CartesianCoordinateSystem) labCoordinateSystem_0.getLocalCoordinateSystemManager().getObject("turbine 3-CSys 1"));

    labCoordinateSystem_0.getLocalCoordinateSystemManager().remove(cartesianCoordinateSystem_2);

    CartesianCoordinateSystem cartesianCoordinateSystem_3 = 
      ((CartesianCoordinateSystem) labCoordinateSystem_0.getLocalCoordinateSystemManager().getObject("turbine 4-CSys 1"));

    labCoordinateSystem_0.getLocalCoordinateSystemManager().remove(cartesianCoordinateSystem_3);

    CartesianCoordinateSystem cartesianCoordinateSystem_4 = 
      ((CartesianCoordinateSystem) labCoordinateSystem_0.getLocalCoordinateSystemManager().getObject("turbine 5-CSys 1"));

    labCoordinateSystem_0.getLocalCoordinateSystemManager().remove(cartesianCoordinateSystem_4);

    CartesianCoordinateSystem cartesianCoordinateSystem_5 = 
      ((CartesianCoordinateSystem) labCoordinateSystem_0.getLocalCoordinateSystemManager().getObject("turbine 6-CSys 1"));

    labCoordinateSystem_0.getLocalCoordinateSystemManager().remove(cartesianCoordinateSystem_5);

    CartesianCoordinateSystem cartesianCoordinateSystem_6 = 
      ((CartesianCoordinateSystem) labCoordinateSystem_0.getLocalCoordinateSystemManager().getObject("turbine 7-CSys 1"));

    labCoordinateSystem_0.getLocalCoordinateSystemManager().remove(cartesianCoordinateSystem_6);

    CartesianCoordinateSystem cartesianCoordinateSystem_7 = 
      ((CartesianCoordinateSystem) labCoordinateSystem_0.getLocalCoordinateSystemManager().getObject("turbine 8-CSys 1"));

    labCoordinateSystem_0.getLocalCoordinateSystemManager().remove(cartesianCoordinateSystem_7);

    CartesianCoordinateSystem cartesianCoordinateSystem_8 = 
      ((CartesianCoordinateSystem) labCoordinateSystem_0.getLocalCoordinateSystemManager().getObject("turbine 9-CSys 1"));

    labCoordinateSystem_0.getLocalCoordinateSystemManager().remove(cartesianCoordinateSystem_8);

    ReportMonitor reportMonitor_0 = 
      ((ReportMonitor) simulation_0.getMonitorManager().getMonitor("Torque turbine 1 Monitor"));

    ReportMonitor reportMonitor_1 = 
      ((ReportMonitor) simulation_0.getMonitorManager().getMonitor("Torque turbine 2 Monitor"));

    ReportMonitor reportMonitor_2 = 
      ((ReportMonitor) simulation_0.getMonitorManager().getMonitor("Torque turbine 3 Monitor"));

    ReportMonitor reportMonitor_3 = 
      ((ReportMonitor) simulation_0.getMonitorManager().getMonitor("Torque turbine 4 Monitor"));

    ReportMonitor reportMonitor_4 = 
      ((ReportMonitor) simulation_0.getMonitorManager().getMonitor("Torque turbine 5 Monitor"));

    ReportMonitor reportMonitor_5 = 
      ((ReportMonitor) simulation_0.getMonitorManager().getMonitor("Torque turbine 6 Monitor"));

    ReportMonitor reportMonitor_6 = 
      ((ReportMonitor) simulation_0.getMonitorManager().getMonitor("Torque turbine 7 Monitor"));

    ReportMonitor reportMonitor_7 = 
      ((ReportMonitor) simulation_0.getMonitorManager().getMonitor("Torque turbine 8 Monitor"));

    ReportMonitor reportMonitor_8 = 
      ((ReportMonitor) simulation_0.getMonitorManager().getMonitor("Torque turbine 9 Monitor"));

    ReportMonitor reportMonitor_9 = 
      ((ReportMonitor) simulation_0.getMonitorManager().getMonitor("volume avg. inflow turbine 1 Monitor"));

    ReportMonitor reportMonitor_10 = 
      ((ReportMonitor) simulation_0.getMonitorManager().getMonitor("volume avg. inflow turbine 2 Monitor"));

    ReportMonitor reportMonitor_11 = 
      ((ReportMonitor) simulation_0.getMonitorManager().getMonitor("volume avg. inflow turbine 3 Monitor"));

    ReportMonitor reportMonitor_12 = 
      ((ReportMonitor) simulation_0.getMonitorManager().getMonitor("volume avg. inflow turbine 4 Monitor"));

    ReportMonitor reportMonitor_13 = 
      ((ReportMonitor) simulation_0.getMonitorManager().getMonitor("volume avg. inflow turbine 5 Monitor"));

    ReportMonitor reportMonitor_14 = 
      ((ReportMonitor) simulation_0.getMonitorManager().getMonitor("volume avg. inflow turbine 6 Monitor"));

    ReportMonitor reportMonitor_15 = 
      ((ReportMonitor) simulation_0.getMonitorManager().getMonitor("volume avg. inflow turbine 7 Monitor"));

    ReportMonitor reportMonitor_16 = 
      ((ReportMonitor) simulation_0.getMonitorManager().getMonitor("volume avg. inflow turbine 8 Monitor"));

    ReportMonitor reportMonitor_17 = 
      ((ReportMonitor) simulation_0.getMonitorManager().getMonitor("volume avg. inflow turbine 9 Monitor"));

    simulation_0.getMonitorManager().removeObjects(reportMonitor_0, reportMonitor_1, reportMonitor_2, reportMonitor_3, reportMonitor_4, reportMonitor_5, reportMonitor_6, reportMonitor_7, reportMonitor_8, reportMonitor_9, reportMonitor_10, reportMonitor_11, reportMonitor_12, reportMonitor_13, reportMonitor_14, reportMonitor_15, reportMonitor_16, reportMonitor_17);

    ReportMonitor reportMonitor_18 = 
      ((ReportMonitor) simulation_0.getMonitorManager().getMonitor("Thrust turbine 1 Monitor"));

    ReportMonitor reportMonitor_19 = 
      ((ReportMonitor) simulation_0.getMonitorManager().getMonitor("Thrust turbine 2 Monitor"));

    ReportMonitor reportMonitor_20 = 
      ((ReportMonitor) simulation_0.getMonitorManager().getMonitor("Thrust turbine 3 Monitor"));

    ReportMonitor reportMonitor_21 = 
      ((ReportMonitor) simulation_0.getMonitorManager().getMonitor("Thrust turbine 4 Monitor"));

    ReportMonitor reportMonitor_22 = 
      ((ReportMonitor) simulation_0.getMonitorManager().getMonitor("Thrust turbine 5 Monitor"));

    ReportMonitor reportMonitor_23 = 
      ((ReportMonitor) simulation_0.getMonitorManager().getMonitor("Thrust turbine 6 Monitor"));

    ReportMonitor reportMonitor_24 = 
      ((ReportMonitor) simulation_0.getMonitorManager().getMonitor("Thrust turbine 7 Monitor"));

    ReportMonitor reportMonitor_25 = 
      ((ReportMonitor) simulation_0.getMonitorManager().getMonitor("Thrust turbine 8 Monitor"));

    ReportMonitor reportMonitor_26 = 
      ((ReportMonitor) simulation_0.getMonitorManager().getMonitor("Thrust turbine 9 Monitor"));

    simulation_0.getMonitorManager().removeObjects(reportMonitor_18, reportMonitor_19, reportMonitor_20, reportMonitor_21, reportMonitor_22, reportMonitor_23, reportMonitor_24, reportMonitor_25, reportMonitor_26);

    VirtualDiskForceReport virtualDiskForceReport_0 = 
      ((VirtualDiskForceReport) simulation_0.getReportManager().getReport("Thrust turbine 1"));

    VirtualDiskForceReport virtualDiskForceReport_1 = 
      ((VirtualDiskForceReport) simulation_0.getReportManager().getReport("Thrust turbine 2"));

    VirtualDiskForceReport virtualDiskForceReport_2 = 
      ((VirtualDiskForceReport) simulation_0.getReportManager().getReport("Thrust turbine 3"));

    VirtualDiskForceReport virtualDiskForceReport_3 = 
      ((VirtualDiskForceReport) simulation_0.getReportManager().getReport("Thrust turbine 4"));

    VirtualDiskForceReport virtualDiskForceReport_4 = 
      ((VirtualDiskForceReport) simulation_0.getReportManager().getReport("Thrust turbine 5"));

    VirtualDiskForceReport virtualDiskForceReport_5 = 
      ((VirtualDiskForceReport) simulation_0.getReportManager().getReport("Thrust turbine 6"));

    VirtualDiskForceReport virtualDiskForceReport_6 = 
      ((VirtualDiskForceReport) simulation_0.getReportManager().getReport("Thrust turbine 7"));

    VirtualDiskForceReport virtualDiskForceReport_7 = 
      ((VirtualDiskForceReport) simulation_0.getReportManager().getReport("Thrust turbine 8"));

    VirtualDiskForceReport virtualDiskForceReport_8 = 
      ((VirtualDiskForceReport) simulation_0.getReportManager().getReport("Thrust turbine 9"));

    VirtualDiskMomentReport virtualDiskMomentReport_0 = 
      ((VirtualDiskMomentReport) simulation_0.getReportManager().getReport("Torque turbine 1"));

    VirtualDiskMomentReport virtualDiskMomentReport_1 = 
      ((VirtualDiskMomentReport) simulation_0.getReportManager().getReport("Torque turbine 2"));

    VirtualDiskMomentReport virtualDiskMomentReport_2 = 
      ((VirtualDiskMomentReport) simulation_0.getReportManager().getReport("Torque turbine 3"));

    VirtualDiskMomentReport virtualDiskMomentReport_3 = 
      ((VirtualDiskMomentReport) simulation_0.getReportManager().getReport("Torque turbine 4"));

    VirtualDiskMomentReport virtualDiskMomentReport_4 = 
      ((VirtualDiskMomentReport) simulation_0.getReportManager().getReport("Torque turbine 5"));

    VirtualDiskMomentReport virtualDiskMomentReport_5 = 
      ((VirtualDiskMomentReport) simulation_0.getReportManager().getReport("Torque turbine 6"));

    VirtualDiskMomentReport virtualDiskMomentReport_6 = 
      ((VirtualDiskMomentReport) simulation_0.getReportManager().getReport("Torque turbine 7"));

    VirtualDiskMomentReport virtualDiskMomentReport_7 = 
      ((VirtualDiskMomentReport) simulation_0.getReportManager().getReport("Torque turbine 8"));

    VirtualDiskMomentReport virtualDiskMomentReport_8 = 
      ((VirtualDiskMomentReport) simulation_0.getReportManager().getReport("Torque turbine 9"));

    VolumeAverageReport volumeAverageReport_0 = 
      ((VolumeAverageReport) simulation_0.getReportManager().getReport("volume avg. inflow turbine 1"));

    VolumeAverageReport volumeAverageReport_1 = 
      ((VolumeAverageReport) simulation_0.getReportManager().getReport("volume avg. inflow turbine 2"));

    VolumeAverageReport volumeAverageReport_2 = 
      ((VolumeAverageReport) simulation_0.getReportManager().getReport("volume avg. inflow turbine 3"));

    VolumeAverageReport volumeAverageReport_3 = 
      ((VolumeAverageReport) simulation_0.getReportManager().getReport("volume avg. inflow turbine 4"));

    VolumeAverageReport volumeAverageReport_4 = 
      ((VolumeAverageReport) simulation_0.getReportManager().getReport("volume avg. inflow turbine 5"));

    VolumeAverageReport volumeAverageReport_5 = 
      ((VolumeAverageReport) simulation_0.getReportManager().getReport("volume avg. inflow turbine 6"));

    VolumeAverageReport volumeAverageReport_6 = 
      ((VolumeAverageReport) simulation_0.getReportManager().getReport("volume avg. inflow turbine 7"));

    VolumeAverageReport volumeAverageReport_7 = 
      ((VolumeAverageReport) simulation_0.getReportManager().getReport("volume avg. inflow turbine 8"));

    VolumeAverageReport volumeAverageReport_8 = 
      ((VolumeAverageReport) simulation_0.getReportManager().getReport("volume avg. inflow turbine 9"));

    simulation_0.getReportManager().removeObjects(virtualDiskForceReport_0, virtualDiskForceReport_1, virtualDiskForceReport_2, virtualDiskForceReport_3, virtualDiskForceReport_4, virtualDiskForceReport_5, virtualDiskForceReport_6, virtualDiskForceReport_7, virtualDiskForceReport_8, virtualDiskMomentReport_0, virtualDiskMomentReport_1, virtualDiskMomentReport_2, virtualDiskMomentReport_3, virtualDiskMomentReport_4, virtualDiskMomentReport_5, virtualDiskMomentReport_6, virtualDiskMomentReport_7, virtualDiskMomentReport_8, volumeAverageReport_0, volumeAverageReport_1, volumeAverageReport_2, volumeAverageReport_3, volumeAverageReport_4, volumeAverageReport_5, volumeAverageReport_6, volumeAverageReport_7, volumeAverageReport_8);
  }
}
