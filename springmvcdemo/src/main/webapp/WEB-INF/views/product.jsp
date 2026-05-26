<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product</title>
</head>
<style type="text/css">
h1 {
	text-align: center;
	color: blue;
}
#prod-tab, #prod-tab th, #prod-tab td {
	border: 2px solid coral;
	border-collapse: collapse;
	font-size: 22px;
	background-color: wheat;
	margin: auto;
	color: blue;
}
#prod-tab th, #prod-tab td {
	padding: 10px;
}
</style>
<body>
	<h1>List Of Products</h1>
	<table id="prod-tab">
		<thead>
			<tr>
				<td>Product Id</td>
				<td>Product Name</td>
				<td>Product Price</td>
				<td>Action</td>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="product" items="${productList}">
				<tr>
					<td>${product.productId}</td>
					<td>${product.productName}</td>
					<td>${product.price}</td>
					<td> <a href="delete?id=${product.productId}"
					onclick="return confirm('Are you sure to delete this product?');"
					> Delete</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<h2 style="color:green;"> ${message} </h2>
	
	<h2> <a href="add"> Add Product</a></h2>
</body>
</html>