// STAR-CCM+ macro: macroCreateVirtualDisksAndRefinements.java
// Written by STAR-CCM+ 10.04.009
//
// contributors: Danny C. Sale
//               Akshay Bagi
// 
// license: ?
// 
// 
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


public class macroCreateVirtualDisksAndRefinements extends StarMacro {

	///////////////////////////////////////////////////////////////////////////////
	// USER INPUTS
	//
	// static final double density 			= 1025;		// fluid density [kg/m^2]
	// static final double dynamic_viscosity 	= 0.00108; 	// fluid dynamic viscosity [Pa-s]
	// static final double init_TI 			= 0.1; 		// turbulence intensity, TI = u' / U [unitless]
	// static final double init_Lturb 			= 3.125; 	// turbulent length scale [m]
	// static final double init_Vturb 			= 0.1; 		// turbulent velocity scale [m/s]
	// static final double init_Vx 			= 0.0; 		// initial x-velocity [m/s]
	// static final double init_Vy 			= 0.0; 		// initial y-velocity [m/s]
	// static final double init_Vz 			= 0.0; 		// initial z-velocity [m/s]

  public void execute() {
    execute0();
  }

    private void execute0() {

  	// if want to play a macro within a macro ... do this (a one-liner):
	  // new StarScript(getActiveSimulation(), new java.io.File(resolvePath("macroCreatePhysics.java"))).play();

	  Simulation simulation_0 = 
	    getActiveSimulation();

	///////////////////////////////////////////////////////////////////////////////
	// create the Virtual Disks and change any default settings 
	//
    Units units_0 = 
      simulation_0.getUnitsManager().getPreferredUnits(new IntVector(new int[] {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}));

    PhysicsContinuum physicsContinuum_0 = 
      simulation_0.getContinuumManager().createContinuum(PhysicsContinuum.class);












  } // end execute0()
} // end public class
