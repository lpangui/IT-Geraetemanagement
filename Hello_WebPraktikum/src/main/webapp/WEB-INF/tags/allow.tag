<%@ tag language="java" pageEncoding="UTF-8"
         trimDirectiveWhitespaces="true" body-content="empty" %>
<%@ attribute name="roles" required="true" %>
<%
//Grundlegende Objekte abrufen
    javax.servlet.jsp.PageContext pc = (javax.servlet.jsp.PageContext)getJspContext(); //(pc= Page context)
    javax.servlet.http.HttpSession sitzung = pc.getSession();
    javax.servlet.http.HttpServletResponse antwort =
        (javax.servlet.http.HttpServletResponse)pc.getResponse();
    javax.servlet.http.HttpServletRequest anfrage =
        (javax.servlet.http.HttpServletRequest)pc.getRequest();
    String ctx = anfrage.getContextPath();

    // Login prüfen (du hältst myLog in der Session)
    de.hwg_lu.bwi520.beans.LoginBean myLog =
        (de.hwg_lu.bwi520.beans.LoginBean)sitzung.getAttribute("myLog");
    if (myLog == null || !myLog.isLoggedIn()) {
        try { 
        	pc.getOut().clear(); //Löscht den bisher generierten Output (HTML)
        
        } catch (Exception ignore) { 
        	
        }
        if (!antwort.isCommitted()) antwort.sendRedirect(ctx + "/jsp/TaskView.jsp");
        else { antwort.resetBuffer(); antwort.sendRedirect(ctx + "/jsp/TaskView.jsp"); }
        return;
    }

 // Kein Caching: verhindert, dass der Browser alte Seiten anzeigt
    antwort.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    antwort.setHeader("Pragma", "no-cache");
    antwort.setDateHeader("Expires", 0);

    // Rollen prüfen
    String erlaubt = (String)getJspContext().getAttribute("roles");
    String rolle = myLog.checkRolle();
    boolean ok = false;
    
    if (rolle != null && erlaubt != null) {
    	
        for (String r : erlaubt.split(",")) {
            if (rolle.trim().equalsIgnoreCase(r.trim())) { 
            	ok = true; break; 
            	}
        }
    }
    
    if (!ok) {
        try { 
        	pc.getOut().clear(); 
        	
        } catch (Exception ignore) {
        	
        }
        
        String home = "/jsp/TaskView.jsp";
        if ("Mitarbeiter".equals(rolle))      home = "/jsp/MitarbeiterView.jsp";
        else if ("Techniker".equals(rolle))   home = "/jsp/TechnikerView.jsp";
        else if ("Super Techniker".equals(rolle)) home = "/jsp/SuperTechnikerView.jsp";
        if (!antwort.isCommitted()) antwort.sendRedirect(ctx + home);
        else { antwort.resetBuffer(); antwort.sendRedirect(ctx + home); }
        return;
    }
%>
