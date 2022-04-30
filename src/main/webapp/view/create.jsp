<%@ page isELIgnored="false" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Create new city</title>
    <link rel="stylesheet" type="text/css" href="/static/css/style.css"/>
    <style>
        #descInput {
            width: 810px;
            height: 100px;
        }

        #climateInput {
            width: 810px;
            height: 75px;
        }
    </style>
</head>
<body>
<main>
    <div class="content__holder">
        <h1>Добавить новый город в Travel service</h1> <br>
        <form method="POST" action="create" enctype="multipart/form-data">
            <b>Название города:</b>
            <br>
            <input name="name"><br><br>
            <b>Описание города:</b>
            <br>
            <textarea name="description" id="descInput"></textarea><br><br>
            <b>Климат города:</b><br>
            <textarea name="climate" id="climateInput"></textarea><br><br>
            <b>Население города (кол. человек): </b>
            <input name="population_str"><br><br>
            <button type="submit">Добавить</button>
        </form>
    </div>
</main>
</body>
</html>