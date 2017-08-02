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

public class EventRecord implements Serializable
{


	public String id;
	public String eventType;
	public String source;
	public String description;

	public void setID(String id)
	{
		this.id=id;
	}
	public String getID()
	{
		return id;
	}
	public void setEventType(String eventType)
	{
		this.eventType=eventType;
	}
	public String getEventType()
	{
		return eventType;
	}
	public void setSource(String source)
	{
		this.source=source;
	}
	public String getSource()
	{
		return source;
	}
	public void setDescription(String description)
	{
		this.description=description;
	}

	public String getDescription()
	{
		return description;
	}

}