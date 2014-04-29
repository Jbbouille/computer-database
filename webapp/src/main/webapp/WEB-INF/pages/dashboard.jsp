<jsp:include page="include/header.jsp" />
<%@ taglib prefix="m" tagdir="/WEB-INF/pages/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

	<section id="main">
		<h1 id="homeTitle">
			<c:out value="${wrap.numberOfComputer} "></c:out>
			<spring:message code="label.numberComputer" />
		</h1>
		<div id="actions">
			<form action="dashboard" method="GET" class="form-inline">
				<input type="search" id="searchbox" name="search"
					placeholder="<spring:message code="label.searchName" />"
					class="form-control" value="${wrap.search}"> <input
					type="submit" id="searchsubmit"
					value="<spring:message code="label.filterByName" />"
					class="btn btn-primary">
			</form>
			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<a class="btn btn-success" id="add" href="addcomputer"><spring:message
						code="label.addComputer" /></a>
			</sec:authorize>
			<m:pagination currentPage="${wrap.currentPage}"
				numberOfPages="${wrap.numberOfPage}" search="${wrap.search}"
				orderBy="${wrap.orderBy}" bool="${wrap.bool}"></m:pagination>
		</div>
		
		<table class="table table-striped table-hover">
			<thead>
				<tr>
					<th class="col-md-2"><a
						href="dashboard?search=${wrap.search}&orderby=name&bool=${wrap.orderBy=='name' ? !wrap.bool:'false'}"><spring:message
								code="label.computerName" /></a></th>
					<th class="col-md-2"><a
						href="dashboard?search=${wrap.search}&orderby=introduced&bool=${wrap.orderBy=='introduced' ? !wrap.bool:'false'}"><spring:message
								code="label.computerIntroduced" /></a></th>
					<th class="col-md-2"><a
						href="dashboard?search=${wrap.search}&orderby=discontinued&bool=${wrap.orderBy=='discontinued' ? !wrap.bool:'false'}"><spring:message
								code="label.computerDiscontinued" /></a></th>
					<th class="col-md-2"><a
						href="dashboard?search=${wrap.search}&orderby=companyId&bool=${wrap.orderBy=='companyId' ? !wrap.bool:'false'}"><spring:message
								code="label.computerCompany" /></a></th>
					<th class="col-md-2">Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="computer" items="${wrap.computerDTOs}">
					<tr>
						<sec:authorize access="hasRole('ROLE_ADMIN')">
							<td class="col-md-2"><a
								href="modifycomputer?id=${computer.id}"><c:out
										value="${computer.name}" /></a></td>
						</sec:authorize>
						<sec:authorize access="hasRole('ROLE_USER')">
							<td class="col-md-2"><c:out value="${computer.name}" /></td>
						</sec:authorize>
						<td class="col-md-2"><c:out value="${computer.introduced}" /></td>
						<td class="col-md-2"><c:out value="${computer.discontinued}" /></td>
						<td class="col-md-2">${computer.companyName}</td>
						<td class="col-md-2"><sec:authorize
								access="hasRole('ROLE_ADMIN')">
								<a href="deletecomputer?id=${computer.id}"
									class="btn btn-warning"
									onClick="return confirm( '<spring:message code="label.jsConfirmDelete" />' )"><spring:message
										code="label.delete" /></a>
							</sec:authorize></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</section>
<jsp:include page="include/footer.jsp" />
