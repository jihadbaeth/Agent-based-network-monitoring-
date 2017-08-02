/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Vector;

/**
 *
 * @author Jihad
 */
public class Diagnoser {


      JEventViewer jev = new JEventViewer();
      Beliefs b = new Beliefs();
      Event e = new Event();

      public Vector match()
      {
          Vector v = new Vector();
          Vector eventsVect = new Vector();
          v= jev.getEvents();
          EventRecord er = new EventRecord();
          //System.out.println("Java Events Viewer Raw vector size" +v.size());
          b.printBeliefsContent();
          for(int i = 0; i<v.size();i++)
          {
			  //System.out.println("Looking try #: " + i);
              er = (EventRecord)v.get(i);
              if(b.exists(er.getID()))
              {
				  Event event = b.searchBeliefs(er.getID().trim());
				  if(er.getID().equals("1002"))
				  {
					  event.setProblem(event.getProblem()+""+er.getSource());
				  }

                  eventsVect.add(event);

                  //System.out.println("Event added");
              }

          }
		System.out.println("number of found problems" +eventsVect.size());
          return eventsVect;

      }

}
