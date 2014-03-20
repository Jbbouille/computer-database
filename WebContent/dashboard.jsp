<jsp:include page="include/header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<section id="main">
	<h1 id="homeTitle">456 Computers found</h1>
	<div id="actions">
		<form action="" method="GET">
			<input type="search" id="searchbox" name="search" value=""
				placeholder="Search name"> <input type="submit"
				id="searchsubmit" value="Filter by name" class="btn primary">
		</form>
		<a class="btn success" id="add" href="addComputer.jsp">Add
			Computer</a>
	</div>

	<table class="computers zebra-striped">
		<thead>
			<tr>
				<!-- Variable declarations for passing labels as parameters -->
				<!-- Table header for Computer Name -->
				<th>Computer Name</th>
				<th>Introduced Date</th>
				<!-- Table header for Discontinued Date -->
				<th>Discontinued Date</th>
				<!-- Table header for Company -->
				<th>Company</th>
			</tr>
		</thead>
		<tbody>
			<c:set var="companies" value="${Companies}"></c:set>
			<c:forEach var="computer" items="${Computers}">
				<tr>
					<td><c:out value="${computer.name}" /></td>
					<td><c:out value="${computer.introduced}" /></td>
					<td><c:out value="${computer.discontinued}" /></td>
					<c:if test="${computer.companyId != null }">
						<td><c:out value="${companies.get(computer.companyId).name}" /></td>
					</c:if>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</section>

<jsp:include page="include/footer.jsp" />
