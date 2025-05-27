<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Chi tiết xe - ${bus.licensePlate}</h2>
<p>Loại xe: ${bus.busType}</p>
<p>Số hàng ghế: ${bus.rowSeat}, Số cột ghế: ${bus.colSeat}</p>
<img src="${bus.image}" alt="Bus Image" width="200"/>

<table class="seat-layout">
    <c:forEach var="row" begin="0" end="${bus.rowSeat - 1}">
        <tr>
            <c:forEach var="col" begin="0" end="${bus.colSeat - 1}">
                <c:set var="seat" value="${seatMatrix[row][col]}"/>
                <td class="${seat != null && seat.status == 'available' ? 'available' : 'booked'}">
                    <c:choose>
                        <c:when test="${seat != null}">
                            ${seat.nameSeat}<br/>
                            ${seat.price}đ<br/>
                            <c:choose>
                                <c:when test="${seat.status == 'available'}">Còn trống</c:when>
                                <c:otherwise>Đã đặt</c:otherwise>
                            </c:choose>
                        </c:when>
                        <c:otherwise>--</c:otherwise>
                    </c:choose>
                </td>
            </c:forEach>
        </tr>
    </c:forEach>
</table>

<p><a href="${pageContext.request.contextPath}/bus">Quay lại danh sách</a></p>

</body>
</html>
