// STAR-CCM+ macro: macro_full_simu_dec31.java
// Written by STAR-CCM+ 10.04.009
//
// contributors: Danny C. Sale
//               Akshay Bagi
// 
// license: ?
// 
// 
// 
// 
// General idea of macro:
// 1) create the Physics Continuum (macro 1)
// 		* settings for Turbulence Models
// 		* settings for Solvers
// 
// 2) create the Geometry Parts for Meshing (macro 2: macroCreateMeshAndVirtualDisks.java)
// 		* assign "Block" Part to Regions
//   	* apply Boundary Conditions to Regions
// 			* note: velocity inlet should load from File Table, or create Field Function
// 		* create the initial "background mesh" within the "Block" (note: 3 options for refining around turbines ... Overset Mesh, Shape Part refinment, or Adaptive Mesh Refinement)
// 
// 3) create the Virtual Disks (macro 2 or 3)
// 		* read Virtual Disk XYZ coordinates from CSV file (at initial/updated conditions from "mooring model" code)
// 		* read Virtual Disk initial/updated settings from CSV file (like: dimensions, positions and operating conditions, file tables, etc.)
// 		* loop to create/update the Virtual Disks
// 		* loop to create Shape Parts for Volumetric Refinement at Virtual Disk locations (can set the cell sizes now in a loop, or use AMR method instead) 
// 
// 4) Alternate: use Adaptive Mesh Refinement (macro 4: macroRunRefineRunRepeat.java)
// 		* create the Field Function for "threshold variable", like TKE, TI, velocity gradient, or other ...
// 		* create the Internal XYZ Table for this Field Function
// 			* run ~100 iterations
// 			* compute the new Field Function values and extract to Internal XYZ Table
// 			* apply the Automated Mesh Operation to provide cell size by "table method"
// 			* run ~100 iterations
// 			* repeat a few times and then run to convergence
// 
// 5) Post Processing: extract solution, create figures and movies (macro 4: macroPostProcessing.java)
// 		* create/update derived parts to post-process/visualize solution (like slice planes, point probes)
// 		* extract the Solution Variables at the Point Probes defined in the "mooring model" code.  Write to updated CSV file from step 3.
//		* update Virtual Disk operating conditions (like change rotor speed to maintain constant tip-speed-ratio)
// 
// 6) repeat from step 3
// 
// 
// Things that should be "User Inputs"
// Physics Continuum options (if any?)
// "Block" dimensions
// Regions Boundary Conditions values (like flow speed, turbulence intensity, length scales, inlet profile)
// Virtual Disk settings (from File Table and "mooring model" table)
// Point Probe locations (from File Table for the "mooring model")
// Mesh Refinement options (like threshold values, cell sizes)
// Run Solver options (like iterations, convergence criteria) 
// 
// 
// OTHER IDEAS:
// 		* if using Overset Mesh ... try running case for single turbine with AMR on the Overset Mesh (not the background mesh) ... and then save this Overset Mesh and load
//        into the simulation with many turbines ... put this "Adaptively Refined" Overset Mesh onto each turbine ... this is alterate method to performing step 4.
// 



///////////////////////////////////////////////////////////////////////////////
// import all the classes we need
//
package macro;

import java.util.*;

import star.vdm.*;
import star.turbulence.*;
import star.kwturb.*;
import star.material.*;
import star.common.*;
import star.base.neo.*;
import star.vis.*;
import star.base.report.*;
import star.flow.*;
import star.trimmer.*;
import star.prismmesher.*;
import star.segregatedflow.*;
import star.metrics.*;
import star.meshing.*;

public class macro_full_simu_dec31 extends StarMacro {

  public void execute() {
    execute0();
  }

  private void execute0() {

    Simulation simulation_0 = 
      getActiveSimulation();

    Units units_0 = 
      simulation_0.getUnitsManager().getPreferredUnits(new IntVector(new int[] {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}));









    MeshPartFactory meshPartFactory_0 = 
      simulation_0.get(MeshPartFactory.class);

    SimpleBlockPart simpleBlockPart_0 = 
      meshPartFactory_0.createNewBlockPart(simulation_0.get(SimulationPartManager.class));

    simpleBlockPart_0.setDoNotRetessellate(true);

    LabCoordinateSystem labCoordinateSystem_0 = 
      simulation_0.getCoordinateSystemManager().getLabCoordinateSystem();

    simpleBlockPart_0.setCoordinateSystem(labCoordinateSystem_0);

    Coordinate coordinate_0 = 
      simpleBlockPart_0.getCorner1();

    coordinate_0.setCoordinateSystem(labCoordinateSystem_0);

    coordinate_0.setCoordinate(units_0, units_0, units_0, new DoubleVector(new double[] {-1.0, -1.0, -1.0}));

    coordinate_0.setValue(new DoubleVector(new double[] {0.0, 0.0, 0.0}));

    Coordinate coordinate_1 = 
      simpleBlockPart_0.getCorner2();

    coordinate_1.setCoordinateSystem(labCoordinateSystem_0);

    coordinate_1.setCoordinate(units_0, units_0, units_0, new DoubleVector(new double[] {1.0, 1.0, 1.0}));

    coordinate_1.setValue(new DoubleVector(new double[] {1000.0, 500.0, 60.0}));

    simpleBlockPart_0.rebuildSimpleShapePart();

    simpleBlockPart_0.setDoNotRetessellate(false);

    simulation_0.getSceneManager().createGeometryScene("Geometry Scene", "Outline", "Geometry", 1);

    Scene scene_0 = 
      simulation_0.getSceneManager().getScene("Geometry Scene 1");

    scene_0.initializeAndWait();

    PartDisplayer partDisplayer_1 = 
      ((PartDisplayer) scene_0.getCreatorDisplayer());

    partDisplayer_1.initialize();

    PartDisplayer partDisplayer_0 = 
      ((PartDisplayer) scene_0.getDisplayerManager().getDisplayer("Outline 1"));

    partDisplayer_0.initialize();

    PartDisplayer partDisplayer_2 = 
      ((PartDisplayer) scene_0.getDisplayerManager().getDisplayer("Geometry 1"));

    partDisplayer_2.initialize();

    scene_0.open(true);

    LogoAnnotation logoAnnotation_0 = 
      ((LogoAnnotation) simulation_0.getAnnotationManager().getObject("Logo"));

    logoAnnotation_0.setOpacity(0.20000000298023224);

    CurrentView currentView_0 = 
      scene_0.getCurrentView();

    currentView_0.setInput(new DoubleVector(new double[] {500.0, 250.0, 30.0}), new DoubleVector(new double[] {500.0, 250.0, 2192.9837938293354}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 564.6520768844484, 0);

    currentView_0.setInput(new DoubleVector(new double[] {496.23607556391113, 243.0944799249471, 275.3017213329354}), new DoubleVector(new double[] {-350.15391652956527, -1309.743038725771, 1883.661613995001}), new DoubleVector(new double[] {-0.1374899298409863, 0.7477527812388121, 0.6495862508873954}), 624.0463623389898, 0);

    PartSurface partSurface_0 = 
      ((PartSurface) simpleBlockPart_0.getPartSurfaceManager().getPartSurface("Block Surface"));

    simpleBlockPart_0.getPartSurfaceManager().splitPartSurfacesByAngle(new NeoObjectVector(new Object[] {partSurface_0}), 89.0);

    simulation_0.getRegionManager().newRegionsFromParts(new NeoObjectVector(new Object[] {simpleBlockPart_0}), "OneRegionPerPart", null, "OneBoundaryPerPartSurface", null, "OneFeatureCurve", null, true);

    partSurface_0.setPresentationName("Inlet");

    PartSurface partSurface_1 = 
      ((PartSurface) simpleBlockPart_0.getPartSurfaceManager().getPartSurface("Block Surface 2"));

    partSurface_1.setPresentationName("Side Wall 1");

    PartSurface partSurface_2 = 
      ((PartSurface) simpleBlockPart_0.getPartSurfaceManager().getPartSurface("Block Surface 3"));

    partSurface_2.setPresentationName("Seabed");

    PartSurface partSurface_3 = 
      ((PartSurface) simpleBlockPart_0.getPartSurfaceManager().getPartSurface("Block Surface 4"));

    partSurface_3.setPresentationName("Side Wall 2");

    PartSurface partSurface_4 = 
      ((PartSurface) simpleBlockPart_0.getPartSurfaceManager().getPartSurface("Block Surface 5"));

    partSurface_4.setPresentationName("Surface");

    PartSurface partSurface_5 = 
      ((PartSurface) simpleBlockPart_0.getPartSurfaceManager().getPartSurface("Block Surface 6"));

    partSurface_5.setPresentationName("Outlet");

    Region region_0 = 
      simulation_0.getRegionManager().getRegion("Block");

    Boundary boundary_0 = 
      region_0.getBoundaryManager().getBoundary("Block Surface");

    boundary_0.setBoundaryType(InletBoundary.class);

    boundary_0.setPresentationName("Inlet");

    Boundary boundary_1 = 
      region_0.getBoundaryManager().getBoundary("Block Surface 2");

    boundary_1.setPresentationName("Side Wall 1");

    Boundary boundary_2 = 
      region_0.getBoundaryManager().getBoundary("Block Surface 3");

    boundary_2.setPresentationName("Seabed");

    Boundary boundary_3 = 
      region_0.getBoundaryManager().getBoundary("Block Surface 4");

    boundary_3.setPresentationName("Side Wall 2");

    Boundary boundary_4 = 
      region_0.getBoundaryManager().getBoundary("Block Surface 5");

    boundary_4.setPresentationName("Surface");

    Boundary boundary_5 = 
      region_0.getBoundaryManager().getBoundary("Block Surface 6");

    boundary_5.setPresentationName("Outlet");

    boundary_5.setBoundaryType(PressureBoundary.class);












    PhysicsContinuum physicsContinuum_0 = 
      simulation_0.getContinuumManager().createContinuum(PhysicsContinuum.class);

    physicsContinuum_0.enable(ThreeDimensionalModel.class);

    physicsContinuum_0.enable(SteadyModel.class);

    physicsContinuum_0.enable(SingleComponentLiquidModel.class);

    physicsContinuum_0.enable(SegregatedFlowModel.class);

    physicsContinuum_0.enable(ConstantDensityModel.class);

    physicsContinuum_0.enable(TurbulentModel.class);

    physicsContinuum_0.enable(RansTurbulenceModel.class);

    physicsContinuum_0.enable(KOmegaTurbulence.class);

    physicsContinuum_0.enable(SstKwTurbModel.class);

    physicsContinuum_0.enable(KwAllYplusWallTreatment.class);

    physicsContinuum_0.enable(VirtualDiskModel.class);

    SingleComponentLiquidModel singleComponentLiquidModel_0 = 
      physicsContinuum_0.getModelManager().getModel(SingleComponentLiquidModel.class);

    Liquid liquid_0 = 
      ((Liquid) singleComponentLiquidModel_0.getMaterial());

    ConstantMaterialPropertyMethod constantMaterialPropertyMethod_0 = 
      ((ConstantMaterialPropertyMethod) liquid_0.getMaterialProperties().getMaterialProperty(ConstantDensityProperty.class).getMethod());

    constantMaterialPropertyMethod_0.getQuantity().setValue(1025.0);









    FileTable fileTable_0 = 
      (FileTable) simulation_0.getTableManager().createFromFile(resolvePath("/mnt/data-RAID-10/akshaybagi/Mezzanine/Starccm+_WT_perf_Speed_Power_Ct.csv"));


    VirtualDiskModel virtualDiskModel_0 = 
      physicsContinuum_0.getModelManager().getModel(VirtualDiskModel.class);

    VirtualDisk virtualDisk_0 = 
      virtualDiskModel_0.getVirtualDiskManager().createDisk("Virtual Disk");

    virtualDisk_0.setActiveMethod(OneDMomentumMethod.class);

    virtualDisk_0.setDisplaySourceTerm(true);

    virtualDisk_0.getComponentsManager().get(PowerCurve.class).setActiveMethod(PowerCurveTableMethod.class);

    PowerCurveTableMethod powerCurveTableMethod_0 = 
      ((PowerCurveTableMethod) virtualDisk_0.getComponentsManager().get(PowerCurve.class).getActiveMethod());

    powerCurveTableMethod_0.setTable(fileTable_0);

    powerCurveTableMethod_0.setWindSpeed("Wind-speed_m/s");

    powerCurveTableMethod_0.setPower("Power_watts");

    powerCurveTableMethod_0.setThrustCoeff("Thrust-Ct");

    SimpleDiskGeometry simpleDiskGeometry_0 = 
      virtualDisk_0.getComponentsManager().get(SimpleDiskGeometry.class);

    simpleDiskGeometry_0.getDiskInnerRadius().setValue(0.0);

    simpleDiskGeometry_0.getDiskOuterRadius().setValue(12.5);

    simpleDiskGeometry_0.getDiskThickness().setValue(4.0);

    Coordinate coordinate_2 = 
      simpleDiskGeometry_0.getDiskOrigin();

    coordinate_2.setCoordinate(units_0, units_0, units_0, new DoubleVector(new double[] {500.0, 250.0, 30.0}));

    ((NormalAndCoordinateSystem) simpleDiskGeometry_0.getOrientationSpecification()).getDiskNormal().setComponents(1.0, 0.0, 0.0);

    PropellerInflowVelocityPlane propellerInflowVelocityPlane_0 = 
      virtualDisk_0.getComponentsManager().get(PropellerInflowVelocityPlane.class);

    propellerInflowVelocityPlane_0.getRadius().setValue(12.5);

    propellerInflowVelocityPlane_0.getOffset().setValue(-50.0);

    VdmRotationRateInputValue vdmRotationRateInputValue_0 = 
      virtualDisk_0.getComponentsManager().get(VdmRotationRateInputValue.class);

    Units units_1 = 
      ((Units) simulation_0.getUnitsManager().getObject("rpm"));

    vdmRotationRateInputValue_0.getRotationRate().setUnits(units_1);

    vdmRotationRateInputValue_0.getRotationRate().setValue(11.5);









    TurbulenceIntensityProfile turbulenceIntensityProfile_0 = 
      physicsContinuum_0.getInitialConditions().get(TurbulenceIntensityProfile.class);

    turbulenceIntensityProfile_0.getMethod(ConstantScalarProfileMethod.class).getQuantity().setValue(0.1);

    physicsContinuum_0.getInitialConditions().get(KwTurbSpecOption.class).setSelected(KwTurbSpecOption.INTENSITY_LENGTH_SCALE);

    TurbulentLengthScaleProfile turbulentLengthScaleProfile_0 = 
      physicsContinuum_0.getInitialConditions().get(TurbulentLengthScaleProfile.class);

    turbulentLengthScaleProfile_0.getMethod(ConstantScalarProfileMethod.class).getQuantity().setValue(6.25);

    TurbulentVelocityScaleProfile turbulentVelocityScaleProfile_0 = 
      physicsContinuum_0.getInitialConditions().get(TurbulentVelocityScaleProfile.class);

    turbulentVelocityScaleProfile_0.getMethod(ConstantScalarProfileMethod.class).getQuantity().setValue(0.1);

    boundary_0.getConditions().get(KwTurbSpecOption.class).setSelected(KwTurbSpecOption.INTENSITY_LENGTH_SCALE);

    TurbulenceIntensityProfile turbulenceIntensityProfile_1 = 
      boundary_0.getValues().get(TurbulenceIntensityProfile.class);

    turbulenceIntensityProfile_1.getMethod(ConstantScalarProfileMethod.class).getQuantity().setValue(0.1);

    TurbulentLengthScaleProfile turbulentLengthScaleProfile_1 = 
      boundary_0.getValues().get(TurbulentLengthScaleProfile.class);

    turbulentLengthScaleProfile_1.getMethod(ConstantScalarProfileMethod.class).getQuantity().setValue(6.25);





    boundary_1.getConditions().get(WallShearStressOption.class).setSelected(WallShearStressOption.SLIP);

    boundary_3.getConditions().get(WallShearStressOption.class).setSelected(WallShearStressOption.SLIP);

    boundary_4.getConditions().get(WallShearStressOption.class).setSelected(WallShearStressOption.SLIP);

    boundary_5.getConditions().get(KwTurbSpecOption.class).setSelected(KwTurbSpecOption.INTENSITY_LENGTH_SCALE);

    TurbulenceIntensityProfile turbulenceIntensityProfile_2 = 
      boundary_5.getValues().get(TurbulenceIntensityProfile.class);

    turbulenceIntensityProfile_2.getMethod(ConstantScalarProfileMethod.class).getQuantity().setValue(0.1);

    TurbulentLengthScaleProfile turbulentLengthScaleProfile_2 = 
      boundary_5.getValues().get(TurbulentLengthScaleProfile.class);

    turbulentLengthScaleProfile_2.getMethod(ConstantScalarProfileMethod.class).getQuantity().setValue(6.25);

    region_0.getConditions().get(TwoEquationTurbulenceUserSourceOption.class).setSelected(TwoEquationTurbulenceUserSourceOption.AMBIENT);

    KwAmbientTurbulenceSpecification kwAmbientTurbulenceSpecification_0 = 
      region_0.getValues().get(KwAmbientTurbulenceSpecification.class);

    kwAmbientTurbulenceSpecification_0.setInflowBoundary(boundary_0);

    scene_0.setTransparencyOverrideMode(1);









    SimpleCylinderPart simpleCylinderPart_0 = 
      meshPartFactory_0.createNewCylinderPart(simulation_0.get(SimulationPartManager.class));

    simpleCylinderPart_0.setDoNotRetessellate(true);

    CartesianCoordinateSystem cartesianCoordinateSystem_0 = 
      ((CartesianCoordinateSystem) labCoordinateSystem_0.getLocalCoordinateSystemManager().getObject("Virtual Disk-CSys 1"));

    simpleCylinderPart_0.setCoordinateSystem(cartesianCoordinateSystem_0);

    Coordinate coordinate_3 = 
      simpleCylinderPart_0.getStartCoordinate();

    coordinate_3.setCoordinateSystem(cartesianCoordinateSystem_0);

    coordinate_3.setCoordinate(units_0, units_0, units_0, new DoubleVector(new double[] {0.0, 0.0, 1.0}));

    coordinate_3.setValue(new DoubleVector(new double[] {0.0, 0.0, -20.0}));

    Coordinate coordinate_4 = 
      simpleCylinderPart_0.getEndCoordinate();

    coordinate_4.setCoordinateSystem(cartesianCoordinateSystem_0);

    coordinate_4.setCoordinate(units_0, units_0, units_0, new DoubleVector(new double[] {0.0, 0.0, 0.0}));

    coordinate_4.setValue(new DoubleVector(new double[] {0.0, 0.0, 20.0}));

    simpleCylinderPart_0.getRadius().setUnits(units_0);

    simpleCylinderPart_0.getRadius().setValue(15.0);

    simpleCylinderPart_0.getTessellationDensityOption().setSelected(TessellationDensityOption.MEDIUM);

    simpleCylinderPart_0.rebuildSimpleShapePart();

    simpleCylinderPart_0.setDoNotRetessellate(false);

    scene_0.setTransparencyOverrideMode(0);













    AutoMeshOperation autoMeshOperation_0 = 
      simulation_0.get(MeshOperationManager.class).createAutoMeshOperation(new StringVector(new String[] {"star.trimmer.TrimmerAutoMesher", "star.prismmesher.PrismAutoMesher"}), new NeoObjectVector(new Object[] {simpleBlockPart_0}));

    PrismAutoMesher prismAutoMesher_0 = 
      ((PrismAutoMesher) autoMeshOperation_0.getMeshers().getObject("Prism Layer Mesher"));

    prismAutoMesher_0.getPrismStretchingOption().setSelected(PrismStretchingOption.WALL_THICKNESS);

    autoMeshOperation_0.getDefaultValues().get(BaseSize.class).setValue(25.0);

    PartsTargetSurfaceSize partsTargetSurfaceSize_0 = 
      autoMeshOperation_0.getDefaultValues().get(PartsTargetSurfaceSize.class);

    GenericRelativeSize genericRelativeSize_0 = 
      ((GenericRelativeSize) partsTargetSurfaceSize_0.getRelativeSize());

    genericRelativeSize_0.setPercentage(10.0);

    NumPrismLayers numPrismLayers_0 = 
      autoMeshOperation_0.getDefaultValues().get(NumPrismLayers.class);

    numPrismLayers_0.setNumLayers(10);

    autoMeshOperation_0.getDefaultValues().get(PrismWallThickness.class).setValue(0.015);

    PrismThickness prismThickness_0 = 
      autoMeshOperation_0.getDefaultValues().get(PrismThickness.class);

    GenericRelativeSize genericRelativeSize_1 = 
      ((GenericRelativeSize) prismThickness_0.getRelativeSize());

    genericRelativeSize_1.setPercentage(25.0);

    PartsSimpleTemplateGrowthRate partsSimpleTemplateGrowthRate_0 = 
      autoMeshOperation_0.getDefaultValues().get(PartsSimpleTemplateGrowthRate.class);

    partsSimpleTemplateGrowthRate_0.getGrowthRateOption().setSelected(PartsGrowthRateOption.VERYSLOW);

    MaximumCellSize maximumCellSize_0 = 
      autoMeshOperation_0.getDefaultValues().get(MaximumCellSize.class);

    GenericRelativeSize genericRelativeSize_2 = 
      ((GenericRelativeSize) maximumCellSize_0.getRelativeSize());

    genericRelativeSize_2.setPercentage(50.0);

    SurfaceCustomMeshControl surfaceCustomMeshControl_0 = 
      autoMeshOperation_0.getCustomMeshControls().createSurfaceControl();

    surfaceCustomMeshControl_0.getGeometryObjects().setObjects(partSurface_0, partSurface_5, partSurface_1, partSurface_3, partSurface_4);

    surfaceCustomMeshControl_0.getCustomConditions().get(PartsTargetSurfaceSizeOption.class).setSelected(PartsTargetSurfaceSizeOption.CUSTOM);

    PartsCustomizePrismMesh partsCustomizePrismMesh_0 = 
      surfaceCustomMeshControl_0.getCustomConditions().get(PartsCustomizePrismMesh.class);

    partsCustomizePrismMesh_0.getCustomPrismOptions().setSelected(PartsCustomPrismsOption.DISABLE);

    PartsTargetSurfaceSize partsTargetSurfaceSize_1 = 
      surfaceCustomMeshControl_0.getCustomValues().get(PartsTargetSurfaceSize.class);

    GenericRelativeSize genericRelativeSize_3 = 
      ((GenericRelativeSize) partsTargetSurfaceSize_1.getRelativeSize());

    genericRelativeSize_3.setPercentage(200.0);










    VolumeCustomMeshControl volumeCustomMeshControl_0 = 
      autoMeshOperation_0.getCustomMeshControls().createVolumeControl();

    volumeCustomMeshControl_0.getGeometryObjects().setObjects(simpleCylinderPart_0);

    VolumeControlTrimmerSizeOption volumeControlTrimmerSizeOption_0 = 
      volumeCustomMeshControl_0.getCustomConditions().get(VolumeControlTrimmerSizeOption.class);

    volumeControlTrimmerSizeOption_0.setVolumeControlBaseSizeOption(true);






    simulation_0.get(MeshOperationManager.class).executeAll();

    ResidualMonitor residualMonitor_0 = 
      ((ResidualMonitor) simulation_0.getMonitorManager().getMonitor("Continuity"));

    MonitorIterationStoppingCriterion monitorIterationStoppingCriterion_0 = 
      residualMonitor_0.createIterationStoppingCriterion();

    MonitorIterationStoppingCriterionMinLimitType monitorIterationStoppingCriterionMinLimitType_0 = 
      ((MonitorIterationStoppingCriterionMinLimitType) monitorIterationStoppingCriterion_0.getCriterionType());

    monitorIterationStoppingCriterionMinLimitType_0.getLimit().setValue(1.0E-6);

    ResidualPlot residualPlot_0 = 
      ((ResidualPlot) simulation_0.getPlotManager().getPlot("Residuals"));

    residualPlot_0.open();

    simulation_0.getSimulationIterator().run();









  }
}
