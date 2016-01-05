// STAR-CCM+ macro: macroTest.java
// Written by STAR-CCM+ 10.04.009
package macro;

import java.util.*;

import star.common.*;
import star.base.neo.*;

public class macroTest extends StarMacro {

  public void execute() {
    execute0();
    // /mnt/data-RAID-1/danny/star-ccm+/sims-starccm/Hyak-saved-runs/sims-starccm/Admiralty_Mesh=Circular-AllYplus_LTurb=5m_VTurb=0.11mps_TI=0.1-Ambient_Seabed=NoSlip-Rough_Coastline=NoSlip-Smooth_SST-K-Omega_Tide=Flood_Zo=0.01_Turbines=10__CLEARED.sim
    execute1();
  }

  private void execute0() {

    Simulation simulation_0 = 
      getActiveSimulation();

    simulation_0.saveState(resolvePath("/mnt/data-RAID-1/danny/star-ccm+/sims-starccm/Hyak-saved-runs/sims-starccm/Admiralty_Mesh=Circular-AllYplus_LTurb=5m_VTurb=0.11mps_TI=0.1-Ambient_Seabed=NoSlip-Rough_Coastline=NoSlip-Smooth_SST-K-Omega_Tide=Flood_Zo=0.01_Turbines=10__CLEARED.sim"));
  }

  private void execute1() {

    Simulation simulation_0 = 
      getActiveSimulation();

    FileTable fileTable_0 = 
      (FileTable) simulation_0.getTableManager().createFromFile(resolvePath("inputs/virtual-blade-1DM_Speed_Power_Ct.csv"));
  }
}
