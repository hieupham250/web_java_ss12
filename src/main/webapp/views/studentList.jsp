<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Danh sách sinh viên</h2>
<a href="${pageContext.request.contextPath}/students/addStudent">Thêm sinh viên mới</a>
<br/><br/>
<table border="1" cellpadding="8" cellspacing="0">
    <tr>
        <th>Tên</th>
        <th>Email</th>
        <th>Ngày sinh</th>
        <th>Hành động</th>
    </tr>
    <c:forEach var="student" items="${students}">
        <tr>
            <td>${student.name}</td>
            <td>${student.email}</td>
            <td>${student.dob}</td>
            <td>
                <a href="${pageContext.request.contextPath}/students/updateStudent?id=${student.id}">Sửa</a> |
                <a href="${pageContext.request.contextPath}/students/deleteStudent?id=${student.id}"
                   onclick="return confirm('Bạn có chắc chắn muốn xóa danh mục này?');">Xóa</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
