<%@ page language="java"
contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<html>
<head>
<title>ERROR : ${message}</title>
<style>
table td{
vertical-align:top;
border:solid 1px #888;
padding:10px;
}
</style>
</head>
<body>
    <h1>ERROR : ${message}</h1>
    <table>
        <tr>
            <td>Date</td>
            <td>${timestamp}</td>
        </tr>
        <tr>
            <td>Error</td>
            <td>${error}</td>
        </tr>
        <tr>
            <td>URL</td>
            <td>${url}</td>
        </tr>
        <tr>
            <td>Message</td>
            <td>${message}</td>
        </tr>
        <tr>
            <td>Trace</td>
            <td>
                <pre>${trace}</pre>
            </td>
        </tr>
    </table>
    <p>
    <a href="javascript:history.back()">Вернуться назад</a>
    <p>
    <a href="/travel">Домой</a>
    <p>
</body>
</html>