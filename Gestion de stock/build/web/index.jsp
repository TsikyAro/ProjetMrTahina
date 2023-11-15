<%@page import="model.Magasin"%>
<%
    Magasin[] magasin = (Magasin[])request.getAttribute("magasin");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Stock Status</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        form {
            width: 400px;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h2, h3 {
            color: #333;
            margin-bottom: 15px;
        }

        label {
            display: block;
            margin-bottom: 8px;
        }

        input[type="date"],
        select,
        input[type="text"],
        input[type="submit"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }

        input[type="submit"] {
            background-color: #4caf50;
            color: white;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <form action="EtatdeStock" method="post">
        <h2>Stock Status</h2>
        <label for="date1">Date de début</label>
        <input type="date" name="date1" id="date1" required>
        <label for="date2">Date de fin</label>
        <input type="date" name="date2" id="date2" required>
        <label for="reference">Produit</label>
        <input type="text" name="reference" id="reference" placeholder="Référence Produit" required>
        <label for="Magasin">Magasin</label>
        <select name="magasin" id="Magasin" required>
            <option value="magasin1">Choisir Magasin</option>
            <%for(int i =0; i<magasin.length; i++){%>
                <option value="<%= magasin[i].getIdMagasin()%>"><%= magasin[i].getNomMagasin()%></option>
            <%}%>
        </select>

        <input type="submit" value="Voir">
    </form>
</body>
</html>
