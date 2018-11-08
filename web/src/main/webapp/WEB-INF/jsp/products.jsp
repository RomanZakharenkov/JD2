<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
				<select name='brand'>
					<c:forEach items="${sessionScope.brands}" var="brand">
						<c:if test="${brand != sessionScope.filter.brand}">
							<option value="${brand}">${brand}</option>
						</c:if>
						<c:if test="${brand == sessionScope.filter.brand}">
							<option value="${brand}" selected>${brand}</option>
						</c:if>
					</c:forEach>
				</select>
			</td>
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
				<c:if test="${sessionScope.filter.maxPrice == ''}">
					<input type="text" name="maxPrice">
				</c:if>
				<c:if test="${sessionScope.filter.maxPrice != ''}">
					<input type="text" name="maxPrice" value="${sessionScope.filter.maxPrice}">
				</c:if>
			</td>
		</tr>
		<tr>
			<td>Отображать на странице по</td>
			<td>
				<select name='pageSize'>
					<c:forEach begin="5" end="20" step="5" var="size">
						<c:if test="${size != sessionScope.filter.pageSize}">
							<option value="${size}">${size}</option>
						</c:if>
						<c:if test="${size == sessionScope.filter.pageSize}">
							<option value="${size}" selected>${size}</option>
						</c:if>
					</c:forEach>
				</select> штук
			</td>
			<td>Выбор страницы</td>
			<td>
				<c:forEach var="page" begin="1" end="${requestScope.countPage}" step="1">
					<a href="/products?page=${page}">${page}</a>
				</c:forEach>
			</td>
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
