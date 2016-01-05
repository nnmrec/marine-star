// STAR-CCM+ macro: createVirtualDisks.java
// Written by STAR-CCM+ 10.06.010
// 
// license: ?
// 
// ideas: * for help with debgging: split the main or loop into multiple foor loops: make VD, vamke shape parts, refine, reports , monitors, ... 
//        * there might be more inputs to pull from the WT_Perf files into here, like: the combined-case table (U RPM PITCH)
// 
// 
package macro;

import java.util.*;

import star.vdm.*;
import star.common.*;
import star.base.neo.*;
import star.base.report.*;
import star.trimmer.*;
import star.meshing.*;
import star.vis.*;


public class createVirtualDisks extends StarMacro {

	///////////////////////////////////////////////////////////////////////////////
	// USER INPUTS (all these user inputs should be read from a CSV file instead)
	// 
  	static final int    nVirtualDisks 	= 9;

	static final double hub_radius    	= 1.0;		
	static final double rotor_radius  	= 12.5;
	static final double rotor_thick   	= 2.0;
	// static final double rotor_spacing 	= 28.0;
	// static final double rotor_height  	= 30.0;
	static final double rotor_rpm     	= 11.5;
	static final double nx            	= 1.0;
	static final double ny            	= 0.0;
	static final double nz            	= 0.0;

	String table = "../inputs/tsr7p0.csv";	// should be able to define a table for each turbine

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

	// I think these are not connecgted to the name ... they will always be named in order of creation
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
  	// turbine coordinates - Mezzanine - first 2 rows
	double[][] coords_VirtualDisks = new double[][]
	{
		{187.5,   100, 44.5},
		{187.5,   175, 44.5},
		{187.5,   250, 44.5},
		{187.5,   325, 44.5},
		{187.5,   400, 44.5},
		{  375, 137.5, 44.5},
		{  375, 212.5, 44.5},
		{  375, 287.5, 44.5},
		{  375, 362.5, 44.5}
	};
	///////////////////////////////////////////////////////////////////////////////





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

	    
















	    // assign the file table
	    virtualDisk_0.getComponentsManager().get(PowerCurve.class).setActiveMethod(PowerCurveTableMethod.class);

	    PowerCurveTableMethod powerCurveTableMethod_0 = 
	      ((PowerCurveTableMethod) virtualDisk_0.getComponentsManager().get(PowerCurve.class).getActiveMethod());

	    // optional: could load different power tables for different turbine types or operating conditions
	    FileTable fileTable_0 = 
          (FileTable) simulation_0.getTableManager().createFromFile(resolvePath(table));
          // (FileTable) simulation_0.getTableManager().createFromFile(resolvePath("../inputs/virtual-blade-1DM_Speed_Power_Ct.csv"));
          // (FileTable) simulation_0.getTableManager().createFromFile(resolvePath("/mnt/data-RAID-10/akshaybagi/Mezzanine/Starccm+_WT_perf_Speed_Power_Ct.csv"));
          
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



    












//                      _         _____       _ _           _            
//                     | |       /  __ \     | (_)         | |           
//   ___ _ __ ___  __ _| |_ ___  | /  \/_   _| |_ _ __   __| | ___ _ __  
//  / __| '__/ _ \/ _` | __/ _ \ | |   | | | | | | '_ \ / _` |/ _ \ '__| 
// | (__| | |  __/ (_| | ||  __/ | \__/\ |_| | | | | | | (_| |  __/ |    
//  \___|_|  \___|\__,_|\__\___|  \____/\__, |_|_|_| |_|\__,_|\___|_|    
//                                       __/ |                           
//                                      |___/                           
// 		// CYLINDER create the Shape Part
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
	    // simpleCylinderPart_0.getTessellationDensityOption().setSelected(TessellationDensityOption.MEDIUM);

	    simpleCylinderPart_0.rebuildSimpleShapePart();

	    // simpleCylinderPart_0.setDoNotRetessellate(false);




//                      _         _____       _                    
//                     | |       /  ___|     | |                   
//   ___ _ __ ___  __ _| |_ ___  \ `--. _ __ | |__   ___ _ __ ___  
//  / __| '__/ _ \/ _` | __/ _ \  `--. \ '_ \| '_ \ / _ \ '__/ _ \ 
// | (__| | |  __/ (_| | ||  __/ /\__/ / |_) | | | |  __/ | |  __/ 
//  \___|_|  \___|\__,_|\__\___| \____/| .__/|_| |_|\___|_|  \___| 
//                                     | |                         
//                                     |_|                         
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
	    // simpleSpherePart_0.getTessellationDensityOption().setSelected(TessellationDensityOption.MEDIUM);

	    simpleSpherePart_0.rebuildSimpleShapePart();

	    // simpleSpherePart_0.setDoNotRetessellate(false);

	    

// //                      _         _____ _____ _   _  _____ 
// //                     | |       /  __ \  _  | \ | ||  ___|
// //   ___ _ __ ___  __ _| |_ ___  | /  \/ | | |  \| || |__  
// //  / __| '__/ _ \/ _` | __/ _ \ | |   | | | | . ` ||  __| 
// // | (__| | |  __/ (_| | ||  __/ | \__/\ \_/ / |\  || |___ 
// //  \___|_|  \___|\__,_|\__\___|  \____/\___/\_| \_/\____/ 
                                                        
                                                        
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

	    // simpleConePart_0.getTessellationDensityOption().setSelected(TessellationDensityOption.MEDIUM);

	    simpleConePart_0.rebuildSimpleShapePart();

	    // simpleConePart_0.setDoNotRetessellate(false);

	    

	    














// needo something about the Automated Mesh here
	    // AutoMeshOperation autoMeshOperation_0 = 
	    //   ((AutoMeshOperation) simulation_0.get(MeshOperationManager.class).getObject("Block"));

// SimpleBlockPart simpleBlockPart_0 = 
//       ((SimpleBlockPart) simulation_0.get(SimulationPartManager.class).getPart("Block"));

//     AutoMeshOperation autoMeshOperation_0 = 
//       simulation_0.get(MeshOperationManager.class).createAutoMeshOperation(new StringVector(new String[] {}), new NeoObjectVector(new Object[] {simpleBlockPart_0}));

AutoMeshOperation autoMeshOperation_0 = 
      ((AutoMeshOperation) simulation_0.get(MeshOperationManager.class).getObject("Automated Mesh"));

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

	    genericAbsoluteSize_2.getValue().setValue(0.20*rotor_radius);


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
	    // AutoMeshOperation autoMeshOperation_1 = 
	    //   ((AutoMeshOperation) simulation_0.get(MeshOperationManager.class).getObject("Block"));

	    VolumeCustomMeshControl volumeCustomMeshControl_3 = 
	      autoMeshOperation_0.getCustomMeshControls().createVolumeControl();

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

	    genericAbsoluteSize_3.getValue().setValue(0.40*rotor_radius);

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

	    genericAbsoluteSize_1.getValue().setValue(0.40*rotor_radius);
















	} // end FOR loop





		// dunno why but these things behave better in a separate loop

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

		
  } // end execute0()
} // end public class
