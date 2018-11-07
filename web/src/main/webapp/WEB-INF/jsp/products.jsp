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
<form action="${pageContext.request.contextPath}/products" method="post">
	<table style="background-color: white; border-radius: 15px; width: 100%">
		<tr>
			<td>Фильтр</td>
			<td>
				<input type="hidden" name="form" value="filterForm">
				<input type="submit"
					   style="background-color: #aa0005; font-size: 15px; border-bottom-color: #aa0005; color: white; border-radius: 15px"
					   name="form"
					   value="Применить">
			</td>
			<td>
				<a href="${pageContext.request.contextPath}/clearfilter" style="color: black">Сбросить фильтр</a>
			</td>
		</tr>
		<tr>
			<td align="left">Категория</td>
			<td align="left">
				<c:if test="${sessionScope.filter.tv != '1'}">
					<input type="checkbox" name="tv" value="1"> Телевизоры
				</c:if>
				<c:if test="${sessionScope.filter.tv == '1'}">
					<input type="checkbox" name="tv" value="1" checked> Телевизоры
				</c:if>
			</td>
			<td align="left">
				<c:if test="${sessionScope.filter.audio != '2'}">
					<input type="checkbox" name="audio" value="2"> Акустика
				</c:if>
				<c:if test="${sessionScope.filter.audio == '2'}">
					<input type="checkbox" name="audio" value="2" checked> Акустика
				</c:if>
			</td>
			<td colspan="10"></td>
		</tr>
		<tr>
			<td>Производитель</td>
			<td>
				<%--TODO: переделать с запоминанием выбранного брэнда--%>
				<select name="brand">
					<option value="ALL" selected>Все</option>
					<c:forEach var="curBrand" items="${sessionScope.brands}">
						<option value=${curBrand}>
							<c:out value="${curBrand}"/>
						</option>
					</c:forEach>
				</select>
			</td>
			<%--<td>--%>
				<%--<select name="brand">--%>
					<%--&lt;%&ndash;<option value="ALL" selected>Все</option>&ndash;%&gt;--%>
					<%--<c:forEach var="curBrand" items="${sessionScope.brands}">--%>
						<%--<c:if test="${curBrand} == ${sessionScope.filter.brand}">--%>
							<%--<option value=${curBrand}, selected>--%>
								<%--&lt;%&ndash;<c:out value="${curBrand}"/>&ndash;%&gt;--%>
								<%--${curBrand}--%>
							<%--</option>--%>
						<%--</c:if>--%>
						<%--<c:if test="${curBrand} != ${sessionScope.filter.brand}">--%>
							<%--<option value=${curBrand}>--%>
								<%--&lt;%&ndash;<c:out value="${curBrand}"/>&ndash;%&gt;--%>
								<%--${curBrand}--%>
							<%--</option>--%>
						<%--</c:if>--%>
					<%--</c:forEach>--%>
				<%--</select>--%>
				<%--${sessionScope.filter.brand}--%>
			<%--</td>--%>
		</tr>
		<tr>
			<td>Сортировать товары</td>
			<c:if test="${sessionScope.filter.orderBy == 'desc'}">
				<td align="left">
					<input type="radio" name="rad" value="desc" checked>по убыванию цены
				</td>
				<td align="left">
					<input type="radio" name="rad" value="asc">по возрастанию цены
				</td>
			</c:if>
			<c:if test="${sessionScope.filter.orderBy == 'asc'}">
				<td align="left">
					<input type="radio" name="rad" value="desc">по убыванию цены
				</td>
				<td align="left">
					<input type="radio" name="rad" value="asc" checked>по возрастанию цены
				</td>
			</c:if>
		</tr>
		<tr>
			<td>Цена</td>
			<td>Минимальная</td>
			<td>
				<c:if test="${sessionScope.filter.minPrice == ''}">
					<input type="text" name="minPrice">
				</c:if>
				<c:if test="${sessionScope.filter.minPrice != ''}">
					<input type="text" name="minPrice" value="${sessionScope.filter.minPrice}">
				</c:if>
			</td>
			<td>Максимальная</td>
			<td>
				<c:if test="${sessionScope.filter.maxPrice == 2147483647}">
					<input type="text" name="maxPrice">
				</c:if>
				<c:if test="${sessionScope.filter.maxPrice != 2147483647}">
					<input type="text" name="maxPrice" value="${sessionScope.filter.maxPrice}">
				</c:if>
			</td>
		</tr>
		<tr>
			<td>Отображать на странице по</td>
			<td>
				<select name="pageSize">
					<option value="5" />5
					<option value="10", selected/>10
					<option value="15" />15
					<option value="20" />20
				</select>штук
			</td>
			<td>all ${sessionScope.filter.count}</td>
		</tr>
	</table>
</form>
<c:forEach var="product" items="${requestScope.products}">
	<form action="${pageContext.request.contextPath}/products" method="post">
		<table style="border-radius: 15px; background-color: white">
			<tr>
				<td style="width: 40%" align="center">
					<input type="image" src="${product.productDetail.image}" width="70%">
				</td>
				<td style="width: 40%; font-size: 18px">
					<a href="${pageContext.request.contextPath}/productInfo?id=${product.id}"
					   style="font-size: 32px">${product.productDetail.brand} ${product.productDetail.model}</a><br>
					<p>Описание</p>
					<p>${product.productDetail.description}</p>
				</td>
				<td style="width: 20%; font-size: 32px" align="center">
					<p>Цена</p>
					<p>
							${product.price} бел. руб.
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
</c:forEach>
</body>
</html>
