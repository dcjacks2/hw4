<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
    <title>SpringBoot</title>
    <style>
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
            width: 150px;
        }

        tr:nth-child(even) {
            background-color: #dddddd;
        }
    </style>
</head>
<body>

<h2>Random Quotes!</h2>


<form method="post" action="/save" id="inp">
    <input type="hidden" name="id" value="">
    <textarea cols="30" rows="10" name="quotessubmitted" >${quote}</textarea>
    <br><br>

    <input type="submit" value="Load">
</form>
<br><br>
<table>
    <tr>
        <th>Quotes</th>

    </tr>
    <c:forEach var = "listitem" items = "${savedquotelist}">
        <tr>
            <td>${listitem.getQuotessubmitted()}</td>
        </tr>
    </c:forEach>
</table>










</body>
</html>
