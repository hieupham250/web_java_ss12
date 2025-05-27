<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Thêm xe khách</h2>

<c:if test="${not empty error}">
    <p style="color:red">${error}</p>
</c:if>

<form:form method="post" modelAttribute="bus">
    <table>
        <tr>
            <td>Biển số:</td>
            <td><form:input path="licensePlate"/></td>
            <td><form:errors path="licensePlate" cssClass="error"/></td>
        </tr>
        <tr>
            <td>Loại xe:</td>
            <td>
                <form:select path="busType">
                    <form:option value="" label="-- Chọn loại xe --"/>
                    <form:option value="NORMAL" label="NORMAL"/>
                    <form:option value="VIP" label="VIP"/>
                    <form:option value="LUXURY" label="LUXURY"/>
                </form:select>
            </td>
            <td><form:errors path="busType" cssClass="error"/></td>
        </tr>
        <tr>
            <td>Số hàng ghế:</td>
            <td><form:input path="rowSeat" type="number" min="1"/></td>
            <td><form:errors path="rowSeat" cssClass="error"/></td>
        </tr>
        <tr>
            <td>Số cột ghế:</td>
            <td><form:input path="colSeat" type="number" min="1"/></td>
            <td><form:errors path="colSeat" cssClass="error"/></td>
        </tr>
        <tr>
            <td>Hình ảnh (URL):</td>
            <td><form:input path="image"/></td>
            <td></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Thêm xe"/>
                <a href="${pageContext.request.contextPath}/bus">Hủy</a>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>
