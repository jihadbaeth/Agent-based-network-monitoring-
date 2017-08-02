/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Vector;

/**
 *
 * @author Jihad
 */
public class Beliefs {

    Vector beliefs = new Vector();

    public Vector getBeliefs() {
        return beliefs;
    }

    public void setBeliefs(Vector beliefs) {
        this.beliefs = beliefs;
    }

    public Beliefs() {

        Event e = new Event();
        e.setId("11500");
        e.setSource("MsiInstaller");
        e.setProblem("Administration Tools Pack -- Error 1500. Another installation is in progress. You must complete that installation before continuing this one.");
        beliefs.add(e);

        Event e1 = new Event();
        e1.setId("1000");
        e1.setSource("Application Error");
        e1.setProblem("Faulting application");
        beliefs.add(e1);

        Event e2 = new Event();
        e2.setId("8194");
        e2.setSource("VSS");
        e2.setProblem("Volume Shadow Copy Service error: Unexpected error querying for the IVssWriterCallback interface");
        beliefs.add(e2);

        Event e3 = new Event();
        e3.setId("1002");
        e3.setSource("Hanging Application");
        e3.setProblem("Application Hang.");
        beliefs.add(e3);

        Event e4 = new Event();
        e4.setId("4107");
        e4.setSource("LDAPSVC");
        e4.setProblem("The Site Server Authentication Service could not do an LDAP bind. Error: 81, virtual server number 5. If this happened during a reboot of the machine and the LDAP server is on the same machine, it is not critical. The Authentication Service wil automatically recover from this problem when the first authentication happens.");
        beliefs.add(e4);


        Event e5 = new Event();
        e5.setId("3");
        e5.setSource("Communicator");
        e5.setProblem("Communicator was unable to resolve the DNS hostname of the login server .Resolution: If you are using manual configuration for Communicator please check that the server name is typed correctly and in full. If you are using automatic configuration the network administrator will need to double-check the DNS A record configuration for <server> because it could not be resolved.");
        beliefs.add(e5);

        Event e6 = new Event();
        e6.setId("1023");
        e6.setSource(".NET Runtime");
        e6.setProblem("NET Runtime version 2.0.50727.42 - Fatal Execution Engine Error");
        beliefs.add(e6);

        Event e7 = new Event();
        e7.setId("13");
        e7.setSource("VSS");
        e7.setProblem("The user name <user name> specified in registry (SYSTEM\\CurrentControlSet\\Services\\VSS\\VssAccessControl) does not map to a real user name. The entry is ignored");
        beliefs.add(e7);

        Event e8 = new Event();
        e8.setId("11327");
        e8.setSource("MsiInstaller");
        e8.setProblem("Invalid Drive: <drive letter>");
        beliefs.add(e8);

        Event e9 = new Event();
        e9.setId("20");
        e9.setSource("MsiInstaller");
        e9.setProblem("Windows could not install some security updates");
        beliefs.add(e9);

        Event e10 = new Event();
        e10.setId("11712");
        e10.setSource("MsiInstaller");
        e10.setProblem("Error 1712. One or more of the files required to restore your computer to its previous state could not be found. Restoration will not be possible.");
        beliefs.add(e10);

        Event e11 = new Event();
        e11.setId("11500");
        e11.setSource("MsiInstaller");
        e11.setProblem("Administration Tools Pack -- Error 1500. Another installation is in progress. You must complete that installation before continuing this one.");
        beliefs.add(e11);

        Event e12 = new Event();
        e12.setId("5000");
        e12.setSource("Microsoft Office 11");
        e12.setProblem("EventType office11shipassert, P1 zoau, P2 11.0.5606.0, P3 NIL, P4 NIL, P5 NIL, P6 NIL, P7 NIL, P8 NIL, P9 NIL, P10 NIL.");
        beliefs.add(e12);


        //System.out.println(beliefs.size());
    }
    public Event searchBeliefs(String id)
    {
        Event s = new Event();
        int i =0;
        boolean found = false;
        while(!found && i<beliefs.size())
        {
           s= (Event) beliefs.get(i);
           if(s.getId().equals(id))
           {
               found=true;
               //System.out.println("found");
           }
            i++;
        }

		//System.out.println("found Nothing");
        return s;
    }
        public boolean exists(String id)
    {

        Event s = new Event();
        int i =0;
        boolean found = false;
        while(!found && i<beliefs.size())
        {
			//System.out.println("found Nothing");
           s= (Event) beliefs.get(i);
         // System.out.println(s.getId()+": compared to : "+id );
           if(s.getId().equals(id))
           {
               found=true;
           }
            i++;
        }


        return found;
    }
    public void printBeliefsContent()
    {
		for(int i=0; i<=12 ; i++)
		{
			        Event e = new Event();
			e=(Event)beliefs.get(i);

			//System.out.println(e.getId());
		}
	}

}
