<%@page import="java.sql.Date"%>
<%@page import="model.Entre"%>
<%
    Entre[] entre1= (Entre[])request.getAttribute("entre1");
    Entre[] entre2= (Entre[])request.getAttribute("entre2");
    Date date1 = (Date)request.getAttribute("date1");
    Date date2 = (Date)request.getAttribute("date2");
    Entre etat1 = (Entre)request.getAttribute("eta1");
    Entre etat2 = (Entre)request.getAttribute("eta2");
    double montant1=0;
    double montant2=0;
     double fin=0;
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Side-by-Side Tables</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
        }

        h2 {
            color: #333;
            margin-bottom: 20px;
        }

        .tables-container {
            display: flex;
            justify-content: space-between;
            width: 95%;
        }

        table {
            width: 18%; /* Adjusted to leave space between tables */
            border-collapse: collapse;
            margin-bottom: 20px;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 12px;
            text-align: left;
        }

        th {
            background-color: #4CAF50;
            color: white;
        }
        .table-title {
            font-size: 16px;
            font-weight: bold;
            margin-bottom: 10px;
            color: #555;
        }
        .bouton{
            padding: 15px;
            background-color: #4caf50;
            color: white;
            cursor: pointer;
            transition: background-color 0.3s;
            text-decoration: none;
            border-radius:5px;
        }
        .bouton:hover{
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <h2>L'Etat de Stock du <%= date1%> et de <%= date2%></h2>

    <div class="tables-container">
        <table>
            <thead>
                <tr>
                    <th>Article</th>
                    <th>Reference Article</th>
                    <th>Prix Unitaire</th>
                    <th>Quantite</th>
                    <th>Magasin</th>
                    <th>Unite</th>
                    <th>Montant</th>
                </tr>
            </thead>
            <tbody>
                <% for (int i = 0; i <entre1.length; i++) {%>
                <tr>
                    <td><%= entre1[i].getNomArticle()%></td>
                    <td><%= entre1[i].getReference()%></td>
                    <td><%= entre1[i].getPrixunitaire()%></td>
                    <td><%= entre1[i].getQuantite()%></td>
                    <td><%= entre1[i].getNomMagasin()%></td>
                    <td><%= entre1[i].getNomUnite()%></td>
                    <%
                        montant1 = entre1[i].getQuantite()* entre1[i].getPrixunitaire();
                    %>
                    <td><%= montant1%></td>
                </tr>
                <%}%>
            </tbody>
        </table>
        <table>
            <thead>
               <tr>
                    <th>Article</th>
                    <th>Reference Article</th>
                    <th>Prix Unitaire</th>
                    <th>Quantite</th>
                    <th>Magasin</th>
                    <th>Unite</th>
                     <th>Montant</th>
                </tr>
            </thead>
            <tbody>
                 <% for (int i = 0; i <entre2.length; i++) {%>
                <tr>
                    <td><%= entre2[i].getNomArticle()%></td>
                    <td><%= entre2[i].getReference()%></td>
                    <td><%= entre2[i].getPrixunitaire()%></td>
                    <td><%= entre2[i].getQuantite()%></td>
                    <td><%= entre2[i].getNomMagasin()%></td>
                    <td><%= entre2[i].getNomUnite()%></td>
                     <%
                         montant2 = entre2[i].getQuantite()* entre2[i].getPrixunitaire();
                         fin = fin + montant2;
                    %>
                    <td><%= montant2%></td>
                    
                </tr>
                <%}%>
            </tbody>
        </table>
    </div>
         <div class="tables-container">
            <div>
                <h3>Prix Unitaire Total:<%=etat1.getPrixunitaire()%></h3>
                <h3>Quantite Total:<%=etat1.getQuantite()%></h3>
            </div>
            <div>
                <h3>Montant Total:<%= fin%></h3>
                <h3>Prix Unitaire Total:<%=etat2.getPrixunitaire()%></h3>
                <h3>Quantite Total:<%=etat2.getQuantite()%></h3>
                <a  href="InsertionSortie" class="bouton">Insertion Sortie >> </a>
            </div>
            
         </div>
            
</body>
</html>

