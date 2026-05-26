<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Product</title>
<style type="text/css">
h1 {
	text-align: center;
	color: blue;
}
table, table th, table  td {
	border: 2px solid coral;
	border-collapse: collapse;
	font-size: 22px;
	background-color: wheat;
	margin: auto;
	color: blue;
}
table  th, table td {
	padding: 10px;
}
input {
font-size : 22px;
color:blue;
}
</style>
</head>
<body>
	<h1>Add Product</h1>
	<form action="save" method="post">
		<table>
			<tr>
				<td><label for="productId"> Product Id</label></td>
				<td><input type="number" required="required" id="productId"
					name="productId"></td>
			</tr>
			<tr>
				<td><label for="productName"> Product Name</label></td>
				<td><input type="text" required="required" id="productName"
					name="productName"></td>
			</tr>
			<tr>
				<td><label for="price"> Price</label></td>
				<td><input type="number" required="required" id="price"
					name="price"></td>
			</tr>
			<tr> 
			 <td> <input type="reset" value="Clear"> </td>
			 <td> <input type="submit" value="Add"> </td>
 			</tr>
		</table>
	</form>
	<h1> <a href="products">Goto Product Page </a></h1>
	<h2 style="color:green;">${msg1 } </h2>
	<h2 style="color:red;">${msg2 } </h2>

</body>
</html>