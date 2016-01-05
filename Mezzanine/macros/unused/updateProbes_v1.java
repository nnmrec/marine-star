// STAR-CCM+ macro: updateProbes_v1.java
// Written by STAR-CCM+ 10.06.009
package macro;

import java.util.*;

import star.common.*;
import star.base.neo.*;
import star.vis.*;

public class updateProbes_v1 extends StarMacro {

  public void execute() {
    execute0();
  }

  private void execute0() {

    Simulation simulation_0 = 
      getActiveSimulation();


    XyzInternalTable xyzInternalTable_0 = 
      simulation_0.getTableManager().createTable(XyzInternalTable.class);

    // XyzInternalTable xyzInternalTable_0 = 
    //   ((XyzInternalTable) simulation_0.getTableManager().getTable("XYZ Internal Table"));

    xyzInternalTable_0.setPresentationName("extract-Point-Probes");


    // PointPart pointPart_0 = 
    //   ((PointPart) simulation_0.getPartManager().getObject("name-1"));

    // PointPart pointPart_1 = 
    //   ((PointPart) simulation_0.getPartManager().getObject("name-2"));

    // PointPart pointPart_2 = 
    //   ((PointPart) simulation_0.getPartManager().getObject("name-3"));

    // PointPart pointPart_3 = 
    //   ((PointPart) simulation_0.getPartManager().getObject("name-4"));

    // xyzInternalTable_0.getParts().setObjects(pointPart_0, pointPart_1, pointPart_2, pointPart_3);


// ex1
// ArrayList<Matrices> list = new ArrayList<Matrices>();
// list.add( new Matrices(1,1,10) );
// list.add( new Matrices(1,2,20) );

      // ex2
      // ArrayList<String> cities = new ArrayList<String>(){{
      //  add("Delhi");
      //  add("Agra");
      //  add("Chennai");
      //  }};

      // ex3
    //   ArrayList<String> books = new ArrayList<String>();
    //  books.add("Java Book1");
    //  books.add("Java Book2");
    //  books.add("Java Book3");
    // System.out.println("Books stored in array list are: "+books);

// ex4
     //  ArrayList<Student> arraylist = new ArrayList<Student>();
     // arraylist.add(new Student(223, "Chaitanya", 26));
     // arraylist.add(new Student(245, "Rahul", 24));
     // arraylist.add(new Student(209, "Ajeet", 32));

      String[] names = new String[nLines];
      names = new String[] {};
      // List<String> names = new ArrayList<String>();
      // ArrayList<String> names = new ArrayList<String>();
      // String[] names = new String[]{};
      names.add("name-1");
      names.add("name-2");
      names.add("name-3");
      names.add("name-4");

      ArrayList<PointPart> pointParts = new ArrayList<PointPart>();
      for (int i = 0; i < 4; i++) {
        PointPart pointPart_n = 
          ((PointPart) simulation_0.getPartManager().getObject(names[i]));

        pointParts.add(pointPart_n);

      } // end for loop
      xyzInternalTable_0.getParts().setObjects(pointParts);


      // ArrayList<PointPart> pointParts = new ArrayList<PointPart>();
      // pointParts.add(pointPart_0);
      // pointParts.add(pointPart_1);
      // pointParts.add(pointPart_2);
      // pointParts.add(pointPart_3);
      // xyzInternalTable_0.getParts().setObjects(pointParts);



  }
}
