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
<form:form method="post" action="/products/addProduct" modelAttribute="productDTO">
  Tên: <form:input path="name" /><br/>
  <form:errors path="name" cssClass="error"/><br/><br/>
  Giá: <form:input path="price" type="number" step="0.01" /><br/>
  <form:errors path="price" cssClass="error"/><br/><br/>
  Số lượng: <form:input path="quantity" type="number" /><br/>
  <form:errors path="quantity" cssClass="error"/><br/><br/>
  Hình ảnh (URL): <form:input path="image" /><br/>
  <form:errors path="image" cssClass="error"/><br/><br/>
  <button type="submit">Thêm mới</button>
</form:form>
</body>
</html>
