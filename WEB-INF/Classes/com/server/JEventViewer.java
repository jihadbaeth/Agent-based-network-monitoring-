
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Jihad
 */


import java.util.Vector;


public class JEventViewer
	{

	private native Vector displayEntries(Vector vector);

	public Vector insertEntries(Vector vect)
	{
		JEventViewer jeventviewer = new JEventViewer();
		Vector rawVect = new Vector();
		rawVect = jeventviewer.displayEntries(rawVect);
		Vector evntVect = new Vector();

		int i = 0;
			while(i < rawVect.size())
			{

				EventRecord evntrec = new EventRecord();
				evntrec.setID((String)rawVect.elementAt(i).toString());
				evntrec.setEventType((String)rawVect.elementAt(i+1).toString());
				evntrec.setSource((String)rawVect.elementAt(i+2).toString());
				evntrec.setDescription((String)rawVect.elementAt(i+3).toString());
				if(evntrec.getEventType().equals("Error"))
				{evntVect.add(evntrec);}
				i=i+4;
				}
		return evntVect;
	}

	public Vector getEvents() {


		JEventViewer jeventviewer = new JEventViewer();
		Vector vect = new Vector();
		//System.out.println("returnVector vector.size = " + vect.size() );
		vect = jeventviewer.insertEntries(vect);

		int i=0;
		while(i < vect.size())
		{
			EventRecord evntrec = new EventRecord();
			evntrec = (EventRecord)vect.get(i);
			//System.out.print("Event ID: " + evntrec.getID());
			//System.out.print("##");
			//System.out.print("Event Type: "+  evntrec.getEventType());
			//System.out.print("##");
			//System.out.print("Event Source: "+  evntrec.getSource());
			//System.out.println("##");
		i=i+1;
		}


		//System.out.println("Number Of Events : "+ vect.size());

                return vect;
	}
	static {
	System.loadLibrary("JEventViewer");
			}
	}
