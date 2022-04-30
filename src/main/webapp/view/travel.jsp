<%@ page isELIgnored="false" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
<head>
    <title>TravelService</title>
    <meta http-equiv="Content-Type" content="text/html" charset="utf-8">
    <script type="text/javascript" src=""></script>
    <link rel="stylesheet" href="/static/css/style.css"/>
</head>
<body>
<div class="main__page-header" style="background-image: url('/static/img/map.png');">
   <div class="main__page-header-sitename">Travel Service</div>
</div>
<div>
    <a class="add__city-button" href="/travel/create">Добавить город</a>
</div>
<main>
    <div class="content__holder cities__list">
        <h2>Города</h2>
        <div class="cities">
            <c:forEach var="fullCityInfo" items="${citiesFullInfo}">
                <div class="city__item">
                    <c:set var = "cityId" scope = "request" value = "${fullCityInfo.getCityInfo().id}"/>
                    <div class="city__item-image" style="background-image: url('${cityService.getImageReference(cityService.getCity(cityId).getName())}');">
                        <div class="city__item-title">
                            <h2>${fullCityInfo.getCityInfo().name}</h2>
                        </div>
                        <div class="weather">Погода: ${fullCityInfo.temperature}&deg;</div>
                    </div>
                    <div class="city__item-content">
                        ${fn:substring(fullCityInfo.getCityInfo().description, 0, 250)}...
                    </div>
                    <div class="city__item-details-button-wrapper">
                        <a class="city__item-details-button" href="/travel/city?cityId=${fullCityInfo.getCityInfo().id}">${buttonName}</a>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</main>
</body>
</html>
