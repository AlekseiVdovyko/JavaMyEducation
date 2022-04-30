<%@ page isELIgnored="false" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Информация о городе ${name}</title>
    <meta http-equiv="Content-Type" content="text/html" charset="utf-8">
    <script type="text/javascript" src=""></script>
    <link rel="stylesheet" href="/static/css/style.css"/>
</head>
<body>
<main>
    <div class="content__holder city__info">
        <div class="text">
            <h1>Информация о городе ${name}</h1>
            <img src="${imgSrc}" height="300" alt=""> <!-- Добавил временно для тестирования -->
            <p>${description}</p>
            <br>
            <h2>Климат</h2>
            <p>${climate}</p>
        </div>
        <div class="info">
            <p><b>Температура в городе на данный момент:</b> ${weather}°C</p>
            <p><b>Население:</b> ${population} человек</p>
            <p><b>Географические координаты:</b> ${latitude} ${longitude} </p>
        </div>
        <form action="edit" metod="get">
            <input name="cityId" hidden value="${id}">
            <button type="submit">Редактировать</button>
        </form>
        <form method="POST" action="delete">
            <input hidden name="cityId" value="${id}">
            <button class="button-red" type="submit">Удалить</button>
        </form>
        <p>Города поблизости (${distance} км):</p>
        <ul>
            <c:forEach items="${cityProviderList}" var="elements">
                <li><a href="city?cityId=${elements.cityToId}"> ${elements.cityToName} ${elements.distance} км</a></li>
            </c:forEach>
        </ul>
        <hr/>
        <p align="center"><a href="/travel">Домой</a>
    </div>
</main>
</body>
</html>