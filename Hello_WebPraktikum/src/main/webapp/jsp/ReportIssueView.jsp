<%@ page import="de.hwg_lu.bwi520.beans.AnfrageBean" %>
<%@ page import="de.hwg_lu.bwi520.beans.TechnicalFailureBean" %>
<%@ page import="de.hwg_lu.bwi520.beans.ChangeDataUserBean" %>
<%@ page import="de.hwg_lu.bwi520.beans.TaskBean" %>
<%@ page import="de.hwg_lu.bwi520.beans.LoginBean" %>
<%@ page import="de.hwg_lu.bwi520.beans.MeldungsBean" %>
<%@ page import="de.hwg_lu.bwi520.beans.BenutzerBean" %>
<%@ page import="de.hwg_lu.bwi520.beans.KontaktBean" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" buffer="32kb" autoFlush="true" %>
<%@ taglib prefix="sec" tagdir="/WEB-INF/tags" %>
<sec:allow roles="Mitarbeiter" />

<!DOCTYPE html>
<html lang="de">
<head>
    <meta charset="UTF-8">
    <title>Anfrageformular</title>
    <script type="text/javascript" src="../js/Task.js"></script>
    <link rel="stylesheet" type="text/css" href="../css/Task.css">
</head>

<body id="portalview">

    <!-- JSP Beans -->
    <jsp:useBean id="myAnfrage" class="de.hwg_lu.bwi520.beans.AnfrageBean" scope="session" />
    <jsp:useBean id="myFailure" class="de.hwg_lu.bwi520.beans.TechnicalFailureBean" scope="session" />
    <jsp:useBean id="myCDU" class="de.hwg_lu.bwi520.beans.ChangeDataUserBean" scope="session" />
    <jsp:useBean id="myUser" class="de.hwg_lu.bwi520.beans.BenutzerBean" scope="session" />
    <jsp:useBean id="myMel" class="de.hwg_lu.bwi520.beans.MeldungsBean" scope="session" />
    <jsp:useBean id="myLog" class="de.hwg_lu.bwi520.beans.LoginBean" scope="session" />
    <jsp:useBean id="myTask" class="de.hwg_lu.bwi520.beans.TaskBean" scope="session" />
    <jsp:useBean id="myK" class="de.hwg_lu.bwi520.beans.KontaktBean" scope="session" />

    <!-- Header -->
    <header id="main-content">
        <h1>Anfrageformular</h1>
    </header>

    <!-- Formular -->
    <div class="tab-content custom-tab-content">
        <div class="tab-pane fade show active" id="pills-login" role="tabpanel" aria-labelledby="tab-login">

            <form id="dynamicForm" action="ReportIssueAppl.jsp" method="GET" onsubmit="return showAlert()">
                <input type="hidden" name="personnalnummer" value="<%= myLog.getPersonalnummer() %>">

                <!-- Abschnitt: Typ der Meldung -->
                <h3>Typ der Meldung</h3>
                <div class="form-group">
                    <label><input type="radio" name="requestType" value="Fehler" required> Fehler melden</label><br>
                    <label><input type="radio" name="requestType" value="Verbesserungsvorschlag"> Verbesserungsvorschlag</label>
                </div>

                <!-- Abschnitt: Problemtyp -->
                <h3>Problemart</h3>
                <div class="form-group">
                    <label><input type="radio" id="software" name="problemType" value="Software" required> Software</label><br>
                    <label><input type="radio" id="hardware" name="problemType" value="Hardware"> Hardware</label>
                </div>

                <!-- Abschnitt: Problembeschreibung -->
                <h3>Problembeschreibung</h3>
                <div class="form-group">
                    <label for="problemDescription">Bitte beschreiben Sie das Problem so genau wie möglich:</label><br>
                    <textarea id="problemDescription" name="beschreibung" rows="7" cols="90" 
                              placeholder="Detaillierte Beschreibung" required></textarea>
                </div>

                <!-- Abschnitt: Datum -->
                <h3>Feststellungsdatum</h3>
                <div class="form-group">
                    <label for="datum">Datum:</label><br>
                    <input type="date" id="datum" name="festelltllungsdatum"
                           value="<%= new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()) %>">
                </div>

                <!-- Buttons -->
                <div class="form-actions">
                    <input type="submit" id="btn" class="btn btn-primary btn-block mb-4" name="senden" value="Senden">
                   
                </div>
            </form>
            
            <form action="ReportIssueAppl.jsp" method="GET">
             <input type="submit" id="btn" class="btn btn-primary btn-block mb-4" name="abbrechen" value="Zurück">
			</form>
        </div>
    </div>

    <!-- Skript -->
    <script>
        function showAlert() {
            alert("Anfrage erfolgreich gesendet!");
            return true;
        }
    </script>



</body>
</html>
