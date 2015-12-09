// STAR-CCM+ macro: macroAddVirtualDisk.java
// Written by STAR-CCM+ 10.04.009
package macro;

import java.util.*;

import star.vdm.*;
import star.common.*;
import star.base.neo.*;
import star.base.report.*;
import star.trimmer.*;
import star.meshing.*;
import star.vis.*;


public class macroAddVirtualDisk extends StarMacro {
	// This creates Virtual Disks at specified coordinates and specifications



	///////////////////////////////////////////////////////////////////////////////
	// USER INPUTS
  	static final int nVirtualDisks = 9;

	// ///////////////////////////////////////////////////////////////////////////////
	// turbine specifications
	static final double hub_radius    = 1.0;
	static final double rotor_radius  = 10.0;
	static final double rotor_thick   = 2.0;
	static final double rotor_spacing = 28.0;
	static final double rotor_height  = 30.0;
	static final double rotor_rpm     = 11.5;
	static final double nx            = 1.0;
	static final double ny            = 0.0;
	static final double nz            = 0.0;

	// turbine names
	String[] name_VirtualDisks = new String[]
	{
		"turbine 1",
		"turbine 2",
		"turbine 3",
		"turbine 4",
		"turbine 5",
		"turbine 6",
		"turbine 7",
		"turbine 8",
		"turbine 9"
	};
	String[] name_VirtualDiskMarker = new String[]
	{
		"VirtualDiskMarker1",
		"VirtualDiskMarker2",
		"VirtualDiskMarker3",
		"VirtualDiskMarker4",
		"VirtualDiskMarker5",
		"VirtualDiskMarker6",
		"VirtualDiskMarker7",
		"VirtualDiskMarker8",
		"VirtualDiskMarker9"
	};
	String[] name_VirtualDiskInflowPlaneMarker = new String[]
	{
		"VirtualDiskInflowPlaneMarker1",
		"VirtualDiskInflowPlaneMarker2",
		"VirtualDiskInflowPlaneMarker3",
		"VirtualDiskInflowPlaneMarker4",
		"VirtualDiskInflowPlaneMarker5",
		"VirtualDiskInflowPlaneMarker6",
		"VirtualDiskInflowPlaneMarker7",
		"VirtualDiskInflowPlaneMarker8",
		"VirtualDiskInflowPlaneMarker9"
	};


	// turbine coordinates - SINGLE FENCE 1/4 Channel
	// double[][] coords_VirtualDisks = new double[][]
	// {
	// 	{10000,660,-20},
	// 	{10000,725.56,-32.494},
	// 	{10000,791.11,-44.811},
	// 	{10000,856.67,-58.912},
	// 	{10000,922.22,-66.235},
	// 	{10000,987.78,-69.76},
	// 	{10000,1053.3,-70},
	// 	{10000,1118.9,-70},
	// 	{10000,1184.4,-70},
	// 	{10000,1250,-70},
	// 	{10000,688,-20},
	// 	{10000,753.56,-32.494},
	// 	{10000,819.11,-44.811},
	// 	{10000,884.67,-58.912},
	// 	{10000,950.22,-66.235},
	// 	{10000,1015.8,-69.76},
	// 	{10000,1081.3,-70},
	// 	{10000,1146.9,-70},
	// 	{10000,1212.4,-70},
	// 	{10000,1278,-70}
	// };
  	
  	// turbine coordinates - SINGLE FENCE 1/2 Channel
	// double[][] coords_VirtualDisks = new double[][]
	// {
	// 	{10000,660,-20},
	// 	{10000,864.44,-58.912},
	// 	{10000,1068.9,-70},
	// 	{10000,1273.3,-70},
	// 	{10000,1477.8,-70},
	// 	{10000,1682.2,-70},
	// 	{10000,1886.7,-70},
	// 	{10000,2091.1,-70},
	// 	{10000,2295.6,-70},
	// 	{10000,2500,-70},
	// 	{10000,688,-20},
	// 	{10000,892.44,-58.912},
	// 	{10000,1096.9,-70},
	// 	{10000,1301.3,-70},
	// 	{10000,1505.8,-70},
	// 	{10000,1710.2,-70},
	// 	{10000,1914.7,-70},
	// 	{10000,2119.1,-70},
	// 	{10000,2323.6,-70},
	// 	{10000,2528,-70}
	// };

  	// turbine coordinates - Admiralty staggered layout
	double[][] coords_VirtualDisks = new double[][]
	{
		{187.5,   150, 40},
		{187.5,   225, 40},
		{187.5,   300, 40},
		{187.5,   375, 40},
		{187.5,   450, 40},
		{375,   187.5, 40},
		{375,   262.5, 40},
		{375,   337.5, 40},
		{375,   412.5, 40}
	};






///////////////////////////////////////////////////////////////////////////////

	public void execute() {
		execute0();
	}

///////////////////////////////////////////////////////////////////////////////

	private void execute0() {

    Simulation simulation_0 = 
      getActiveSimulation();


    PhysicsContinuum physicsContinuum_0 = 
      ((PhysicsContinuum) simulation_0.getContinuumManager().getContinuum("Physics 1"));


	// create a new virtual disk
	for (int i = 0; i < nVirtualDisks; i++) {
		
	    VirtualDiskModel virtualDiskModel_0 = 
	      physicsContinuum_0.getModelManager().getModel(VirtualDiskModel.class);

	    VirtualDisk virtualDisk_0 = 
	      virtualDiskModel_0.getVirtualDiskManager().createDisk(name_VirtualDisks[i]);

      	virtualDisk_0.setDisplaySourceTerm(true);

	    virtualDisk_0.setActiveMethod(OneDMomentumMethod.class);

	    virtualDisk_0.getComponentsManager().get(PowerCurve.class).setActiveMethod(PowerCurveTableMethod.class);

	    PowerCurveTableMethod powerCurveTableMethod_0 = 
	      ((PowerCurveTableMethod) virtualDisk_0.getComponentsManager().get(PowerCurve.class).getActiveMethod());

	    FileTable fileTable_0 = 
	      ((FileTable) simulation_0.getTableManager().getTable("virtual-blade-1DM_Speed_Power_Ct"));

	    powerCurveTableMethod_0.setTable(fileTable_0);

	    powerCurveTableMethod_0.setWindSpeed("Wind-speed_m/s");

	    powerCurveTableMethod_0.setPower("Power_watts");

	    powerCurveTableMethod_0.setThrustCoeff("Thrust-Ct");

	    SimpleDiskGeometry simpleDiskGeometry_0 = 
	      virtualDisk_0.getComponentsManager().get(SimpleDiskGeometry.class);

	    simpleDiskGeometry_0.getDiskOuterRadius().setValue(rotor_radius);

	    simpleDiskGeometry_0.getDiskInnerRadius().setValue(hub_radius);

	    simpleDiskGeometry_0.getDiskThickness().setValue(rotor_thick);

	    Coordinate coordinate_0 = 
	      simpleDiskGeometry_0.getDiskOrigin();

	    Units units_1 = 
	      ((Units) simulation_0.getUnitsManager().getObject("m"));

	    coordinate_0.setCoordinate(units_1, units_1, units_1, new DoubleVector(new double[] {coords_VirtualDisks[i][0], coords_VirtualDisks[i][1], coords_VirtualDisks[i][2]}));

	    ((NormalAndCoordinateSystem) simpleDiskGeometry_0.getOrientationSpecification()).getDiskNormal().setComponents(nx, ny, nz);

	    PropellerInflowVelocityPlane propellerInflowVelocityPlane_2 = 
	      virtualDisk_0.getComponentsManager().get(PropellerInflowVelocityPlane.class);

	    propellerInflowVelocityPlane_2.getRadius().setValue(rotor_radius);

	    propellerInflowVelocityPlane_2.getOffset().setValue(-1*4*rotor_radius);

	    VdmRotationRateInputValue vdmRotationRateInputValue_2 = 
	      virtualDisk_0.getComponentsManager().get(VdmRotationRateInputValue.class);

	    Units units_0 = 
	      ((Units) simulation_0.getUnitsManager().getObject("rpm"));

	    vdmRotationRateInputValue_2.getRotationRate().setUnits(units_0);

	    vdmRotationRateInputValue_2.getRotationRate().setValue(rotor_rpm);
















	    ///////////////////////////////////////////////////////////////////////////////
		// create reports and monitors
		// Torque
		VirtualDiskMomentReport virtualDiskMomentReport_0 = 
      	  simulation_0.getReportManager().createReport(VirtualDiskMomentReport.class);

      	virtualDiskMomentReport_0.setVirtualDisk(virtualDisk_0);

		virtualDiskMomentReport_0.setPresentationName("Torque " + name_VirtualDisks[i]);

	    VirtualDiskMomentReport virtualDiskMomentReport_1 = 
	      ((VirtualDiskMomentReport) simulation_0.getReportManager().getReport("Torque " + name_VirtualDisks[i]));

	    ReportMonitor reportMonitor_5 = 
	      virtualDiskMomentReport_1.createMonitor();

	    // Thrust
		VirtualDiskForceReport virtualDiskForceReport_0 = 
          simulation_0.getReportManager().createReport(VirtualDiskForceReport.class);
		  
		virtualDiskForceReport_0.setVirtualDisk(virtualDisk_0);

		virtualDiskForceReport_0.setPresentationName("Thrust " + name_VirtualDisks[i]);

	    VirtualDiskForceReport virtualDiskForceReport_1 = 
	      ((VirtualDiskForceReport) simulation_0.getReportManager().getReport("Thrust " + name_VirtualDisks[i]));

	    ReportMonitor reportMonitor_6 = 
	      virtualDiskForceReport_1.createMonitor();



    

















		// CYLINDER create the Shape Part
	    Units units_2 = 
	      simulation_0.getUnitsManager().getPreferredUnits(new IntVector(new int[] {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}));

	    MeshPartFactory meshPartFactory_0 = 
	      simulation_0.get(MeshPartFactory.class);

	    SimpleCylinderPart simpleCylinderPart_0 = 
	      meshPartFactory_0.createNewCylinderPart(simulation_0.get(SimulationPartManager.class));
	      simpleCylinderPart_0.setPresentationName("refine cylinder: " + name_VirtualDisks[i]);


	    simpleCylinderPart_0.setDoNotRetessellate(true);


	    // set the coordinate system
	    LabCoordinateSystem labCoordinateSystem_0 = 
	      simulation_0.getCoordinateSystemManager().getLabCoordinateSystem();

	    CartesianCoordinateSystem cartesianCoordinateSystem_1 = 
	      // ((CartesianCoordinateSystem) labCoordinateSystem_0.getLocalCoordinateSystemManager().getObject("turbine  1: cw-CSys 1"));
	      ((CartesianCoordinateSystem) labCoordinateSystem_0.getLocalCoordinateSystemManager().getObject(name_VirtualDisks[i] + "-CSys 1"));
	      
	    simpleCylinderPart_0.setCoordinateSystem(cartesianCoordinateSystem_1);


	    // start coordinate
	    Coordinate coordinate_5 = 
	      simpleCylinderPart_0.getStartCoordinate();
	    coordinate_5.setCoordinateSystem(cartesianCoordinateSystem_1);

	    coordinate_5.setCoordinate(units_2, units_2, units_2, new DoubleVector(new double[] {0.0, 0.0, 1.0}));

	    coordinate_5.setValue(new DoubleVector(new double[] {0.0, 0.0, -0.5*rotor_radius}));

	    // end coordinate
	    Coordinate coordinate_6 = 
	      simpleCylinderPart_0.getEndCoordinate();

	    coordinate_6.setCoordinateSystem(cartesianCoordinateSystem_1);

	    coordinate_6.setCoordinate(units_2, units_2, units_2, new DoubleVector(new double[] {0.0, 0.0, 0.0}));

	    coordinate_6.setValue(new DoubleVector(new double[] {0.0, 0.0, 0.5*rotor_radius}));

	    // radius
	    simpleCylinderPart_0.getRadius().setUnits(units_2);

	    simpleCylinderPart_0.getRadius().setValue(1.25*rotor_radius);

	    // misc
	    simpleCylinderPart_0.getTessellationDensityOption().setSelected(TessellationDensityOption.MEDIUM);

	    simpleCylinderPart_0.rebuildSimpleShapePart();

	    simpleCylinderPart_0.setDoNotRetessellate(false);

	    ///////////////////////////////////////////////////////////////////////////////
	    // SPHERE create the Shape Part
	    SimpleSpherePart simpleSpherePart_0 = 
	      meshPartFactory_0.createNewSpherePart(simulation_0.get(SimulationPartManager.class));
	      
	    simpleSpherePart_0.setPresentationName("refine sphere: " + name_VirtualDisks[i]);

	    simpleSpherePart_0.setDoNotRetessellate(true);

	    // coordinate system - attach to the Virtual Disk coordinate system
	    LabCoordinateSystem labCoordinateSystem_1 = 
	      simulation_0.getCoordinateSystemManager().getLabCoordinateSystem();

	    CartesianCoordinateSystem cartesianCoordinateSystem_0 = 
	      ((CartesianCoordinateSystem) labCoordinateSystem_1.getLocalCoordinateSystemManager().getObject(name_VirtualDisks[i] + "-CSys 1"));
	      
	    simpleSpherePart_0.setCoordinateSystem(cartesianCoordinateSystem_0);


	    // origin
	    Coordinate coordinate_9 = 
	      simpleSpherePart_0.getOrigin();

	    coordinate_9.setCoordinateSystem(cartesianCoordinateSystem_0);

	    coordinate_9.setCoordinate(units_2, units_2, units_2, new DoubleVector(new double[] {0.0, 0.0, 0.0}));

	    coordinate_9.setValue(new DoubleVector(new double[] {0.0, 0.0, 0.0}));

	    
	    // radius
	    simpleSpherePart_0.getRadius().setUnits(units_2);

	    simpleSpherePart_0.getRadius().setValue(4.50*rotor_radius);

	    
	    // misc
	    simpleSpherePart_0.getTessellationDensityOption().setSelected(TessellationDensityOption.MEDIUM);

	    simpleSpherePart_0.rebuildSimpleShapePart();

	    simpleSpherePart_0.setDoNotRetessellate(false);

	    

//                      _         _____ _____ _   _  _____ 
//                     | |       /  __ \  _  | \ | ||  ___|
//   ___ _ __ ___  __ _| |_ ___  | /  \/ | | |  \| || |__  
//  / __| '__/ _ \/ _` | __/ _ \ | |   | | | | . ` ||  __| 
// | (__| | |  __/ (_| | ||  __/ | \__/\ \_/ / |\  || |___ 
//  \___|_|  \___|\__,_|\__\___|  \____/\___/\_| \_/\____/ 
                                                        
                                                        
	    ///////////////////////////////////////////////////////////////////////////////
	    // CONE create the Shape Part
	    SimpleConePart simpleConePart_0 = 
	      meshPartFactory_0.createNewConePart(simulation_0.get(SimulationPartManager.class));

	  	simpleConePart_0.setPresentationName("refine cone: " + name_VirtualDisks[i]);

	    simpleConePart_0.setDoNotRetessellate(true);

	    simpleConePart_0.setCoordinateSystem(cartesianCoordinateSystem_0);

	    Coordinate coordinate_2 = 
	      simpleConePart_0.getStartCoordinate();

	    coordinate_2.setCoordinateSystem(cartesianCoordinateSystem_0);

	    coordinate_2.setCoordinate(units_2, units_2, units_2, new DoubleVector(new double[] {0.0, 0.0, 1.0}));

	    coordinate_2.setValue(new DoubleVector(new double[] {0.0, 0.0, 0.0}));

	    simpleConePart_0.getStartRadius().setUnits(units_2);

	    simpleConePart_0.getStartRadius().setValue(1.50*rotor_radius);

	    Coordinate coordinate_3 = 
	      simpleConePart_0.getEndCoordinate();

	    coordinate_3.setCoordinateSystem(cartesianCoordinateSystem_0);

	    coordinate_3.setCoordinate(units_2, units_2, units_2, new DoubleVector(new double[] {0.0, 0.0, 0.0}));

	    coordinate_3.setValue(new DoubleVector(new double[] {0.0, 0.0, 20*2*rotor_radius}));

	    simpleConePart_0.getEndRadius().setUnits(units_2);

	    simpleConePart_0.getEndRadius().setValue(2.0*rotor_radius);

	    simpleConePart_0.getTessellationDensityOption().setSelected(TessellationDensityOption.MEDIUM);

	    simpleConePart_0.rebuildSimpleShapePart();

	    simpleConePart_0.setDoNotRetessellate(false);

	    

	    





















//            __ _              _____       _ _           _           
//           / _(_)            /  __ \     | (_)         | |          
//  _ __ ___| |_ _ _ __   ___  | /  \/_   _| |_ _ __   __| | ___ _ __ 
// | '__/ _ \  _| | '_ \ / _ \ | |   | | | | | | '_ \ / _` |/ _ \ '__|
// | | |  __/ | | | | | |  __/ | \__/\ |_| | | | | | | (_| |  __/ |   
// |_|  \___|_| |_|_| |_|\___|  \____/\__, |_|_|_| |_|\__,_|\___|_|   
//                                     __/ |                          
//                                    |___/                           
	    ///////////////////////////////////////////////////////////////////////////////
	    // CYLINDER create a volumentric mesh control, add Shape Parts

	    AutoMeshOperation autoMeshOperation_0 = 
	      ((AutoMeshOperation) simulation_0.get(MeshOperationManager.class).getObject("block"));

	    VolumeCustomMeshControl volumeCustomMeshControl_2 = 
	      autoMeshOperation_0.getCustomMeshControls().createVolumeControl();

	      volumeCustomMeshControl_2.setPresentationName("refine cylinder: " + name_VirtualDisks[i]);

	      volumeCustomMeshControl_2.getGeometryObjects().setObjects(simpleCylinderPart_0);


	      // customize the size of this mesh refinement
	    VolumeControlTrimmerSizeOption volumeControlTrimmerSizeOption_2 = 
	      volumeCustomMeshControl_2.getCustomConditions().get(VolumeControlTrimmerSizeOption.class);

	    volumeControlTrimmerSizeOption_2.setVolumeControlBaseSizeOption(true);

	    VolumeControlSize volumeControlSize_2 = 
	      volumeCustomMeshControl_2.getCustomValues().get(VolumeControlSize.class);

	    volumeControlSize_2.getRelativeOrAbsoluteOption().setSelected(RelativeOrAbsoluteOption.ABSOLUTE);

	    GenericAbsoluteSize genericAbsoluteSize_2 = 
	      ((GenericAbsoluteSize) volumeControlSize_2.getAbsoluteSize());

	    genericAbsoluteSize_2.getValue().setValue(0.10*rotor_radius);

//            __ _              _____ _____ _   _  _____ 
//           / _(_)            /  __ \  _  | \ | ||  ___|
//  _ __ ___| |_ _ _ __   ___  | /  \/ | | |  \| || |__  
// | '__/ _ \  _| | '_ \ / _ \ | |   | | | | . ` ||  __| 
// | | |  __/ | | | | | |  __/ | \__/\ \_/ / |\  || |___ 
// |_|  \___|_| |_|_| |_|\___|  \____/\___/\_| \_/\____/ 
		///////////////////////////////////////////////////////////////////////////
	    // CONE create a volumentric mesh control, add Shape Parts
	    VolumeCustomMeshControl volumeCustomMeshControl_0 = 
	      autoMeshOperation_0.getCustomMeshControls().createVolumeControl();

		volumeCustomMeshControl_0.setPresentationName("refine cone: " + name_VirtualDisks[i]);

	    volumeCustomMeshControl_0.getGeometryObjects().setObjects(simpleConePart_0);


	    VolumeControlTrimmerSizeOption volumeControlTrimmerSizeOption_0 = 
	      volumeCustomMeshControl_0.getCustomConditions().get(VolumeControlTrimmerSizeOption.class);

	    volumeControlTrimmerSizeOption_0.setVolumeControlBaseSizeOption(true);

	    VolumeControlSize volumeControlSize_1 = 
	      volumeCustomMeshControl_0.getCustomValues().get(VolumeControlSize.class);

	    volumeControlSize_1.getRelativeOrAbsoluteOption().setSelected(RelativeOrAbsoluteOption.ABSOLUTE);

	    GenericAbsoluteSize genericAbsoluteSize_1 = 
	      ((GenericAbsoluteSize) volumeControlSize_1.getAbsoluteSize());

	    genericAbsoluteSize_1.getValue().setValue(0.10*rotor_radius);

//            __ _              _____       _                   
//           / _(_)            /  ___|     | |                  
//  _ __ ___| |_ _ _ __   ___  \ `--. _ __ | |__   ___ _ __ ___ 
// | '__/ _ \  _| | '_ \ / _ \  `--. \ '_ \| '_ \ / _ \ '__/ _ \
// | | |  __/ | | | | | |  __/ /\__/ / |_) | | | |  __/ | |  __/
// |_|  \___|_| |_|_| |_|\___| \____/| .__/|_| |_|\___|_|  \___|
//                                   | |                        
//                                   |_|                        
	    ///////////////////////////////////////////////////////////////////////////////
	    // SPHERE create a volumentric mesh control, add Shape Parts
	    AutoMeshOperation autoMeshOperation_1 = 
	      ((AutoMeshOperation) simulation_0.get(MeshOperationManager.class).getObject("block"));

	    VolumeCustomMeshControl volumeCustomMeshControl_3 = 
	      autoMeshOperation_1.getCustomMeshControls().createVolumeControl();

	    volumeCustomMeshControl_3.setPresentationName("refine sphere: " + name_VirtualDisks[i]);

	    volumeCustomMeshControl_3.getGeometryObjects().setObjects(simpleSpherePart_0);

	    VolumeControlTrimmerSizeOption volumeControlTrimmerSizeOption_3 = 
	      volumeCustomMeshControl_3.getCustomConditions().get(VolumeControlTrimmerSizeOption.class);

	    volumeControlTrimmerSizeOption_3.setVolumeControlBaseSizeOption(true);

	    VolumeControlSize volumeControlSize_3 = 
	      volumeCustomMeshControl_3.getCustomValues().get(VolumeControlSize.class);

	    volumeControlSize_3.getRelativeOrAbsoluteOption().setSelected(RelativeOrAbsoluteOption.ABSOLUTE);

	    GenericAbsoluteSize genericAbsoluteSize_3 = 
	      ((GenericAbsoluteSize) volumeControlSize_3.getAbsoluteSize());

	    genericAbsoluteSize_3.getValue().setValue(0.20*rotor_radius);














	} // end FOR loop





// 		// dunno why but these things behave better in a separate loop

		Units units_none = 
		  simulation_0.getUnitsManager().getPreferredUnits(new IntVector(new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}));

		Region region_0 = 
		  simulation_0.getRegionManager().getRegion("Block");

		for (int i = 0; i < nVirtualDisks; i++) {

			    ///////////////////////////////////////////////////////////////////////////////
				// create threshold parts
			    PrimitiveFieldFunction primitiveFieldFunction_0 = 
			      ((PrimitiveFieldFunction) simulation_0.getFieldFunctionManager().getFunction(name_VirtualDiskMarker[i]));
			      // ((PrimitiveFieldFunction) simulation_0.getFieldFunctionManager().getFunction("VirtualDiskMarker" + i));
			      // ((PrimitiveFieldFunction) simulation_0.getFieldFunctionManager().getFunction("VirtualDiskMarker1"));			      
			      // ((PrimitiveFieldFunction) simulation_0.getFieldFunctionManager().getFunction("VirtualDiskMarker" + "i]"));

			    ThresholdPart thresholdPart_0 = 
			      simulation_0.getPartManager().createThresholdPart(new NeoObjectVector(new Object[] {region_0}), new DoubleVector(new double[] {1.0, 1.0}), units_none, primitiveFieldFunction_0, 0);

			    thresholdPart_0.setPresentationName(name_VirtualDisks[i]);


			    PrimitiveFieldFunction primitiveFieldFunction_1 = 
			      ((PrimitiveFieldFunction) simulation_0.getFieldFunctionManager().getFunction(name_VirtualDiskInflowPlaneMarker[i]));
			      // ((PrimitiveFieldFunction) simulation_0.getFieldFunctionManager().getFunction("VirtualDiskInflowPlaneMarker" + i));
			      // ((PrimitiveFieldFunction) simulation_0.getFieldFunctionManager().getFunction("VirtualDiskInflowPlaneMarker1"));
			      // ((PrimitiveFieldFunction) simulation_0.getFieldFunctionManager().getFunction("VirtualDiskInflowPlaneMarker" + "i]"));

			    ThresholdPart thresholdPart_1 = 
			      simulation_0.getPartManager().createThresholdPart(new NeoObjectVector(new Object[] {region_0}), new DoubleVector(new double[] {1.0, 1.0}), units_none, primitiveFieldFunction_1, 0);

			    thresholdPart_1.setPresentationName("inflow " + name_VirtualDisks[i]);




			    ///////////////////////////////////////////////////////////////////////////////
				// create volume average reports and monitors on the threshold parts
			    VolumeAverageReport volumeAverageReport_0 = 
			      simulation_0.getReportManager().createReport(VolumeAverageReport.class);

		      	FvRepresentation fvRepresentation_0 = 
      			  ((FvRepresentation) simulation_0.getRepresentationManager().getObject("Volume Mesh"));

    			volumeAverageReport_0.setRepresentation(fvRepresentation_0);

			    PrimitiveFieldFunction primitiveFieldFunction_2 = 
			      ((PrimitiveFieldFunction) simulation_0.getFieldFunctionManager().getFunction("Velocity"));

			    VectorMagnitudeFieldFunction vectorMagnitudeFieldFunction_0 = 
			      ((VectorMagnitudeFieldFunction) primitiveFieldFunction_2.getMagnitudeFunction());

			    volumeAverageReport_0.setScalar(vectorMagnitudeFieldFunction_0);

			    volumeAverageReport_0.getParts().setObjects(thresholdPart_1);

			    volumeAverageReport_0.setPresentationName("volume avg. inflow " + name_VirtualDisks[i]);


			    VolumeAverageReport volumeAverageReport_1 = 
			      ((VolumeAverageReport) simulation_0.getReportManager().getReport("volume avg. inflow " + name_VirtualDisks[i]));

			    ReportMonitor reportMonitor_0 = 
				  volumeAverageReport_1.createMonitor();




		} // end FOR loop





// 	// Object[] reportMonitorTorque = new Object[nVirtualDisks];
//     // List<Object> reportMonitorTorque = new ArrayList<Object>(nVirtualDisks);
//  //    List reportMonitorTorque = new ArrayList();
//  //    Object asdf = new Object();
// 	// String[] names_reports = new String[nVirtualDisks];

// // ReportMonitor reportMonitor_0 = null;

// 	// String[] iter = new String[]
// 	// {
// 	// 	"1",
// 	// 	"2",
// 	// 	"3",
// 	// 	"4",
// 	// 	"5",
// 	// 	"6",
// 	// 	"7",
// 	// 	"8",
// 	// 	"9",
// 	// 	"10",
// 	// 	"11",
// 	// 	"12",
// 	// 	"13",
// 	// 	"14",
// 	// 	"15",
// 	// 	"16",
// 	// 	"17",
// 	// 	"18",
// 	// 	"19",
// 	// 	"20"
// 	// };


// // 	for (int i = 0; i < nVirtualDisks; i++) {
// // // int i = 0;
// // 	    ReportMonitor reportMonitor_0 = 
// // 	      ((ReportMonitor) simulation_0.getMonitorManager().getMonitor("Torque " + name_VirtualDisks[i] + " Monitor"));

// // 	    // ReportMonitor reportMonitorTorque = 
// // 	    //   ((ReportMonitor) simulation_0.getMonitorManager().getMonitor("Torque " + name_VirtualDisks[i] + " Monitor"));

// // 		// reportMonitorTorque[i] = reportMonitor_0;
// // 	      reportMonitorTorque.add(reportMonitor_0);

// // 	    // if (i == nVirtualDisks-1) {
// //     	// 	names_reports[i] = "reportMonitorTorque[" + iter[i] + "]";	
// //     	// } else {
// //     	// 	names_reports[i] = "reportMonitorTorque[" + iter[i] + "], ";
// // 	    // }
	    


// // 	    // MonitorPlot monitorPlot_0 = 
// //      //  	  simulation_0.getPlotManager().createMonitorPlot(new NeoObjectVector(new Object[] {reportMonitorTorque}), "Monitors Plot");

// // 	}

// // 	// MonitorPlot monitorPlot_0 = 
// // 	//       	  simulation_0.getPlotManager().createMonitorPlot(new NeoObjectVector(new Object[] reportMonitorTorque), "Monitors Plot");
// // 	// MonitorPlot monitorPlot_0 = 
// // 	//       	  simulation_0.getPlotManager().createMonitorPlot(new NeoObjectVector(new Object[] Arrays.toString(names_reports)), "Monitors Plot");

// // 	// MonitorPlot monitorPlot_0 = 
// // 	//   simulation_0.getPlotManager().createMonitorPlot(new NeoObjectVector(new Object[] reportMonitor_0), "Monitors Plot");

// // 	MonitorPlot monitorPlot_0 = 
// // 	  // simulation_0.getPlotManager().createMonitorPlot(new NeoObjectVector(new Object[] {reportMonitorTorque}), "Monitors Plot");
// // 	  simulation_0.getPlotManager().createMonitorPlot(new NeoObjectVector(new Object[] {reportMonitorTorque[0], reportMonitorTorque[1]}), "Monitors Plot");	  


// //     // MonitorPlot monitorPlot_0 = 
// //     //   simulation_0.getPlotManager().createMonitorPlot(new NeoObjectVector(new Object[] reportMonitorTorque), "Monitors Plot");

// //     monitorPlot_0.setPresentationName("rotor torque");





// 		// simulation_0.getMonitorManager().createMonitorAndPlot(new NeoObjectVector(new Object[] {virtualDiskMomentReport_0, virtualDiskMomentReport_1, virtualDiskMomentReport_2, virtualDiskMomentReport_3, virtualDiskMomentReport_4, virtualDiskMomentReport_5, virtualDiskMomentReport_6, virtualDiskMomentReport_7, virtualDiskMomentReport_8, virtualDiskMomentReport_9, virtualDiskMomentReport_10, virtualDiskMomentReport_11, virtualDiskMomentReport_12, virtualDiskMomentReport_13, virtualDiskMomentReport_14, virtualDiskMomentReport_15, virtualDiskMomentReport_16, virtualDiskMomentReport_17, virtualDiskMomentReport_18, virtualDiskMomentReport_19}), true, "Reports Plot");

// 		// MonitorPlot monitorPlot_0 = 
// 		//   simulation_0.getPlotManager().createMonitorPlot(new NeoObjectVector(new Object[] {reportMonitor_0, reportMonitor_1, reportMonitor_2, reportMonitor_3, reportMonitor_4, reportMonitor_5, reportMonitor_6, reportMonitor_7, reportMonitor_8, reportMonitor_9, reportMonitor_10, reportMonitor_11, reportMonitor_12, reportMonitor_13, reportMonitor_14, reportMonitor_15, reportMonitor_16, reportMonitor_17, reportMonitor_18, reportMonitor_19}), "Reports Plot");

// 		// monitorPlot_0.setPresentationName("sadfasdfadsfads");






	} //end execute0


	// add a volume mesh refinement around each turbine





} // end execute()
