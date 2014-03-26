<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ attribute name="numberOfPages" required="true" type="Integer" %>
<%@ attribute name="currentPage" required="true" type="Integer" %>
<%@ attribute name="search" required="true" type="String" %>

<ul class="pagination">
	<c:choose>
		<c:when test="${currentPage le 1}">
			<li class="disabled"><a href="#"><span>&laquo;</span></a></li>
		</c:when>
		<c:otherwise>
			<li><a href="dashboard?search=${search}&page=${currentPage -1}">&laquo;</a></li>
		</c:otherwise>
	</c:choose>
	<c:forEach var="i" begin="1" end="${numberOfPages}">
		<c:choose>
			<c:when test="${currentPage == i}">
				<li class="active"><a href="dashboard?search=${search}&page=${i}">${i}<span
						class="sr-only">(current)</span></a></li>
			</c:when>
			<c:otherwise>
				<li><a href="dashboard?search=${search}&page=${i}">${i}</a></li>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<c:choose>
		<c:when test="${currentPage ge numberOfPages }">
			<li class="disabled"><a href="#"><span>&raquo;</span></a></li>
		</c:when>
		<c:otherwise>
			<li><a href="dashboard?search=${search}&page=${currentPage +1}">&raquo;</a></li>
		</c:otherwise>
	</c:choose>

</ul>
