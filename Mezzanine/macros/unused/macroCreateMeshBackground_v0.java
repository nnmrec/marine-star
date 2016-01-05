// STAR-CCM+ macro: macroCreateMeshBackground_v0.java
// Written by STAR-CCM+ 10.06.009
package macro;

import java.util.*;

import star.common.*;
import star.base.neo.*;
import star.trimmer.*;
import star.prismmesher.*;
import star.meshing.*;

public class macroCreateMeshBackground_v0 extends StarMacro {

  public void execute() {
    execute0();
  }

  private void execute0() {

    Simulation simulation_0 = 
      getActiveSimulation();

    SimpleBlockPart simpleBlockPart_0 = 
      ((SimpleBlockPart) simulation_0.get(SimulationPartManager.class).getPart("Block"));

    AutoMeshOperation autoMeshOperation_0 = 
      simulation_0.get(MeshOperationManager.class).createAutoMeshOperation(new StringVector(new String[] {}), new NeoObjectVector(new Object[] {simpleBlockPart_0}));

    autoMeshOperation_0.getMeshers().setMeshersByNames(new StringVector(new String[] {"star.trimmer.TrimmerAutoMesher", "star.prismmesher.PrismAutoMesher"}));

    PrismAutoMesher prismAutoMesher_0 = 
      ((PrismAutoMesher) autoMeshOperation_0.getMeshers().getObject("Prism Layer Mesher"));

    prismAutoMesher_0.getPrismStretchingOption().setSelected(PrismStretchingOption.Type.WALL_THICKNESS);

    autoMeshOperation_0.getDefaultValues().get(BaseSize.class).setValue(25.0);

    PartsTargetSurfaceSize partsTargetSurfaceSize_0 = 
      autoMeshOperation_0.getDefaultValues().get(PartsTargetSurfaceSize.class);

    partsTargetSurfaceSize_0.getRelativeOrAbsoluteOption().setSelected(RelativeOrAbsoluteOption.Type.ABSOLUTE);

    GenericAbsoluteSize genericAbsoluteSize_0 = 
      ((GenericAbsoluteSize) partsTargetSurfaceSize_0.getAbsoluteSize());

    genericAbsoluteSize_0.getValue().setValue(5.55);

    NumPrismLayers numPrismLayers_0 = 
      autoMeshOperation_0.getDefaultValues().get(NumPrismLayers.class);

    numPrismLayers_0.setNumLayers(10);

    autoMeshOperation_0.getDefaultValues().get(PrismWallThickness.class).setValue(0.02);

    PrismThickness prismThickness_0 = 
      autoMeshOperation_0.getDefaultValues().get(PrismThickness.class);

    prismThickness_0.getRelativeOrAbsoluteOption().setSelected(RelativeOrAbsoluteOption.Type.ABSOLUTE);

    GenericAbsoluteSize genericAbsoluteSize_1 = 
      ((GenericAbsoluteSize) prismThickness_0.getAbsoluteSize());

    genericAbsoluteSize_1.getValue().setValue(3.33);

    MaxTrimmerSizeToPrismThicknessRatio maxTrimmerSizeToPrismThicknessRatio_0 = 
      autoMeshOperation_0.getDefaultValues().get(MaxTrimmerSizeToPrismThicknessRatio.class);

    maxTrimmerSizeToPrismThicknessRatio_0.setLimitCellSizeByPrismThickness(true);

    SizeThicknessRatio sizeThicknessRatio_0 = 
      maxTrimmerSizeToPrismThicknessRatio_0.getSizeThicknessRatio();

    sizeThicknessRatio_0.setNeighboringThicknessMultiplier(2.1);

    PartsSimpleTemplateGrowthRate partsSimpleTemplateGrowthRate_0 = 
      autoMeshOperation_0.getDefaultValues().get(PartsSimpleTemplateGrowthRate.class);

    partsSimpleTemplateGrowthRate_0.getGrowthRateOption().setSelected(PartsGrowthRateOption.Type.SLOW);

    partsSimpleTemplateGrowthRate_0.getSurfaceGrowthRateOption().setSelected(PartsSurfaceGrowthRateOption.Type.MEDIUM);

    MaximumCellSize maximumCellSize_0 = 
      autoMeshOperation_0.getDefaultValues().get(MaximumCellSize.class);

    maximumCellSize_0.getRelativeOrAbsoluteOption().setSelected(RelativeOrAbsoluteOption.Type.ABSOLUTE);

    GenericAbsoluteSize genericAbsoluteSize_2 = 
      ((GenericAbsoluteSize) maximumCellSize_0.getAbsoluteSize());

    genericAbsoluteSize_2.getValue().setValue(30.0);

    SurfaceCustomMeshControl surfaceCustomMeshControl_0 = 
      autoMeshOperation_0.getCustomMeshControls().createSurfaceControl();

    surfaceCustomMeshControl_0.getGeometryObjects().setQuery(null);

    PartSurface partSurface_0 = 
      ((PartSurface) simpleBlockPart_0.getPartSurfaceManager().getPartSurface("Inlet"));

    PartSurface partSurface_1 = 
      ((PartSurface) simpleBlockPart_0.getPartSurfaceManager().getPartSurface("Left Bank"));

    PartSurface partSurface_2 = 
      ((PartSurface) simpleBlockPart_0.getPartSurfaceManager().getPartSurface("Outlet"));

    PartSurface partSurface_3 = 
      ((PartSurface) simpleBlockPart_0.getPartSurfaceManager().getPartSurface("Right Bank"));

    PartSurface partSurface_4 = 
      ((PartSurface) simpleBlockPart_0.getPartSurfaceManager().getPartSurface("Sea Surface"));

    surfaceCustomMeshControl_0.getGeometryObjects().setObjects(partSurface_0, partSurface_1, partSurface_2, partSurface_3, partSurface_4);

    PartsCustomizePrismMesh partsCustomizePrismMesh_0 = 
      surfaceCustomMeshControl_0.getCustomConditions().get(PartsCustomizePrismMesh.class);

    partsCustomizePrismMesh_0.getCustomPrismOptions().setSelected(PartsCustomPrismsOption.Type.DISABLE);

    SurfaceCustomMeshControl surfaceCustomMeshControl_1 = 
      autoMeshOperation_0.getCustomMeshControls().createSurfaceControl();

    surfaceCustomMeshControl_1.getGeometryObjects().setQuery(null);

    PartSurface partSurface_5 = 
      ((PartSurface) simpleBlockPart_0.getPartSurfaceManager().getPartSurface("Seabed"));

    surfaceCustomMeshControl_1.getGeometryObjects().setObjects(partSurface_5);

    surfaceCustomMeshControl_1.getCustomConditions().get(PartsTargetSurfaceSizeOption.class).setSelected(PartsTargetSurfaceSizeOption.Type.CUSTOM);

    PartsTargetSurfaceSize partsTargetSurfaceSize_1 = 
      surfaceCustomMeshControl_1.getCustomValues().get(PartsTargetSurfaceSize.class);

    partsTargetSurfaceSize_1.getRelativeOrAbsoluteOption().setSelected(RelativeOrAbsoluteOption.Type.ABSOLUTE);

    GenericAbsoluteSize genericAbsoluteSize_3 = 
      ((GenericAbsoluteSize) partsTargetSurfaceSize_1.getAbsoluteSize());

    genericAbsoluteSize_3.getValue().setValue(6.66);

    surfaceCustomMeshControl_1.getCustomConditions().get(PartsCustomSurfaceGrowthRateOption.class).setSelected(PartsCustomSurfaceGrowthRateOption.Type.CUSTOM);

    PartsCustomSimpleSurfaceGrowthRate partsCustomSimpleSurfaceGrowthRate_0 = 
      surfaceCustomMeshControl_1.getCustomValues().get(PartsCustomSimpleSurfaceGrowthRate.class);

    partsCustomSimpleSurfaceGrowthRate_0.getSurfaceGrowthRateOption().setSelected(PartsSurfaceGrowthRateOption.Type.FAST);
  }
}
