/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Jihad
 */


import java.io.*;
import java.util.*;

public class EventsVect implements Serializable{

Vector eventsVect = new Vector();



	public void setEventsVect(Vector eventsVect)
	{
		this.eventsVect=eventsVect;
	}
	public Vector geEventsVect()
	{
		return eventsVect;
	}

}

