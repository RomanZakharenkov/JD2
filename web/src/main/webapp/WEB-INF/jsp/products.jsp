<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Рома
  Date: 04.11.2018
  Time: 1:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Все товары</title>
</head>
<body style="background-color: #cccccc">
<c:forEach var="product" items="${requestScope.products}">
	<form action="${pageContext.request.contextPath}/products" method="post">
		<table style="border-radius: 15px; background-color: white">
			<tr>
				<td style="width: 40%" align="center">
					<input type="image" src="${product.image}" width="70%">
				</td>
				<td style="width: 40%; font-size: 18px">
					<a href="${pageContext.request.contextPath}/productInfo?id=${product.id}"
						style="font-size: 32px">${product.brand} ${product.model}</a><br>
					<p>
						<%--<fmt:message key="allproducts.description"/>--%>
					</p>
					<p>${product.description}</p>
				</td>
				<td style="width: 20%; font-size: 32px" align="center">
					<p>
						<%--<fmt:message key="allproducts.price"/>--%>
					</p>
					<p>
						<%--${product.price} <fmt:message key="allproducts.typeprice"/>--%>
					</p>
					<p>
						<c:if test="${sessionScope.currentUser != null}">
							<input type="hidden" name="productId" value="${product.id}"/>
							<input type="hidden" name="form" value="basket"/>
							<input type="submit"
			   					style="background-color: #aa0005; font-size: 30px; border-bottom-color: #aa0005; color: white; border-radius: 15px"
			   					value="В корзину">
						</c:if>
					</p>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
