<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        .error {
            color: red;
        }
    </style>
</head>
<body>
<h2>Chỉnh sửa sinh viên</h2>

<form:form action="${pageContext.request.contextPath}/students/updateStudent" method="post" modelAttribute="studentDTO">
    <form:hidden path="id"/>
    <label for="name">Tên sinh viên:</label>
    <form:input path="name" id="name"/>
    <form:errors path="name" cssClass="error"/><br/><br/>
    <label for="email">Email sinh viên:</label>
    <form:input path="email" id="email"/>
    <form:errors path="email" cssClass="error"/><br/><br/>
    <label for="dob">Ngày sinh:</label>
    <form:input path="dob" id="dob"/>
    <form:errors path="dob" cssClass="error"/><br/><br/>
    <input type="submit" value="Cập nhật"/>
</form:form>

<a href="${pageContext.request.contextPath}/students">← Quay lại danh sách</a>
</body>
</html>
