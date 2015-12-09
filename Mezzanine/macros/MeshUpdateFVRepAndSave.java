import star.common.*;
import star.meshing.*;
import star.vis.*;

/**
 * This macro will mesh, update all the objects to Volume Mesh Representation (FVR),
 * except the ones defined in the regular expressions strings (regex), and save the case.
 * 
 * More information on regex: https://en.wikipedia.org/wiki/Regular_expression
 * 
 * @author Fabio Kasper (fabio.kasper@cd-adapco.com)
 * @version 1.1
 * @since Sep/2015
 */
public class MeshUpdateFVRepAndSave extends StarMacro {

  /** Regular Expression for ignoring Displayers. */
  String regex4IgnoringDisplayers = "(Parts|Geom.*)";
  /** Regular Expression for ignoring Scenes. */
  String regex4IgnoringScenes = "Geom.*";
  
  public void execute() {
      
    Simulation sim = getActiveSimulation();

    meshAndUpdateToFVR(sim);
    
    save(sim);
    
  }
  
  void meshAndUpdateToFVR(Simulation s) {
    s.get(MeshPipelineController.class).generateVolumeMesh();
    FvRepresentation fvr =  ((FvRepresentation) s.getRepresentationManager().getObject("Volume Mesh"));
    String sfmt = "%s|--+ %s: %s...";
    s.println("Changing objects to Finite Volume Representation:");
    for (Scene scn : s.getSceneManager().getObjects()) {
        String sn = scn.getPresentationName();
        if (sn.matches(regex4IgnoringScenes)) {
            s.println(String.format(sfmt, "", "Skipping Scene", sn));
            continue;
        }
        s.println(String.format(sfmt, "", "Changing Scene", sn));
        for (Displayer d : scn.getDisplayerManager().getObjects()) {
            String dn = d.getPresentationName();
            if (dn.matches(regex4IgnoringDisplayers)) {
                s.println(String.format(sfmt, "   ", "Skipping Displayer", dn));
                continue;
            }
            s.println(String.format(sfmt, "   ", "Changing Displayer", dn));
            d.setRepresentation(fvr);
        }
    }
    s.getPlotManager().applyRepresentation(fvr);
    s.getTableManager().applyRepresentation(fvr);
    s.getReportManager().applyRepresentation(fvr);
  }

  void save(Simulation s) {
    String simName = String.format("%s.sim", s.getPresentationName());
    s.saveState(new java.io.File(s.getSessionDir(), simName).toString());
  }
  
}