/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import java.util.*;
/**
 *
 * @author Jihad
 */
public class Event implements Serializable{

    public String getId() {
        return id;
    }

    public String getProblem() {
        return problem;
    }

    public String getSource() {
        return source;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public void setSource(String source) {
        this.source = source;
    }
    public void setIp(String ip) {
	    this.ip = ip;
	}
	public String getIp() {
		return ip;
    }
    public Event()
    {
        id="0";
        source="Unknown";
        problem="Unknown";
        ip = "0.0.0.0";
    }

    public String id;
    public String source;
    public String problem;
    public String ip;


}
