<%@ page isELIgnored="false" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Редактирование города в Travel service</title>
    <meta http-equiv="Content-Type" content="text/html" charset="utf-8">
    <link rel="stylesheet" href="/static/css/style.css">
    <style>
        #descInput {
            width: 810px;
            height: 100px;
        }

        #climateInput {
            width: 810px;
            height: 100px;
        }
    </style>
</head>
<body>
<main>
    <div class="content__holder city__edit">
        <h1>Редактирование информации о городе ${name}</h1>
        <h3 style="color: red">${msg}</h3>
        <form method="POST" action="edit" enctype="multipart/form-data">
            <input type="hidden" name="id" readonly value="${id}">
            <input type="hidden" name="name" readonly value="${name}">
            <br>
            <b>Город:</b> ${name}
            <br><br>
            <p><b>Описание</b></p>
            <textarea name="description" id="descInput">${description}</textarea>
            <br><br>
            <p><b>Климат</b></p>
            <textarea name="climate" id="climateInput">${climate}</textarea>
            <br><br>
            <b>Население (кол. человек):</b> <input name="population" value="${population}"><br><br>
            <b>Географические координаты</b> <br>
            <p style="font-size: 12px;">
                Южную широту вводить со знаком "-"<br>
                Западную долготу вводить со знаком "-"
            <p>
            <b>Широта в радианах (от -π/2 до +π/2):</b> <input name="latitude" value="${latitude}"><br>
            <b>Долгота в радианах (от -π до +π):</b> <input name="longitude" value="${longitude}">
            <p>
            <button type="submit" class="button-green">Сохранить</button>
            <button type="reset">Отмена</button>
           </form>
        <form>
         <input class="button-orange" type="button" value="Назад" onclick="history.back()">
        </form>
    </div>
</main>


<hr/>
<p align="center"><a href="/travel">Домой</a>
</body>
</html>