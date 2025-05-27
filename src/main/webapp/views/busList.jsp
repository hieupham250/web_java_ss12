<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Danh sách xe khách</h2>

<c:if test="${not empty success}">
    <p style="color:green">${success}</p>
</c:if>
<c:if test="${not empty error}">
    <p style="color:red">${error}</p>
</c:if>

<a href="${pageContext.request.contextPath}/bus/add">Thêm xe mới</a>
<table border="1" cellpadding="5" cellspacing="0">
    <tr>
        <th>ID</th>
        <th>Biển số</th>
        <th>Loại xe</th>
        <th>Số hàng</th>
        <th>Số cột</th>
        <th>Tổng ghế</th>
        <th>Hình ảnh</th>
        <th>Hành động</th>
    </tr>
    <c:forEach var="bus" items="${buses}">
        <tr>
            <td>${bus.id}</td>
            <td>${bus.licensePlate}</td>
            <td>${bus.busType}</td>
            <td>${bus.rowSeat}</td>
            <td>${bus.colSeat}</td>
            <td>${bus.totalSeat}</td>
            <td><img src="${bus.image}" alt="Bus Image" width="100"/></td>
            <td>
                <a href="${pageContext.request.contextPath}/bus/detail/${bus.id}">Chi tiết</a> |
                <a href="${pageContext.request.contextPath}/bus/edit/${bus.id}">Sửa</a> |
                <a href="${pageContext.request.contextPath}/bus/delete/${bus.id}" onclick="return confirm('Bạn có chắc muốn xóa xe này?');">Xóa</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
