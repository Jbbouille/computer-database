<jsp:include page="include/header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<section id="main">
	<h1>Add Computer</h1>

	<div class="panel panel-default">
		<div class="panel-body">
			<form id="frm" action="addcomputer" method="POST">

				<div class="form-group">
					<label for="name">Name</label> <input type="text"
						class="form-control" name="name" placeholder="Enter name"
						value="${name}">
					<c:if test="${errorName != null}">
						<label class="error" for="name"> <c:out
								value="${errorName}"></c:out>
						</label>
					</c:if>
				</div>
				<div class="form-group">
					<label for="introduced">Introduced</label> <input type=text
						class="form-control" name="introducedDate"
						placeholder="date introduced" id="datepickerIntroduced"
						value="${introducedDate}">
					<c:if test="${errorIntroduced != null}">
						<label class="error" for="datepickerIntroduced"> <c:out
								value="${errorIntroduced}"></c:out>
						</label>
					</c:if>
				</div>
				<div class="form-group">
					<label for="discontinued">Discontinued</label> <input type="text"
						class="form-control" name="discontinuedDate"
						placeholder="date discontinued" id="datepickerDiscontinued"
						value="${discontinuedDate}">
					<c:if test="${errorDiscontinued != null}">
						<label class="error" for="datepickerDiscontinued"> <c:out
								value="${errorDiscontinued}"></c:out>
						</label>
					</c:if>
				</div>
				<label for="company">Company Name:</label>
				<div class="controls">
					<select class="form-control" name="company">
						<option value="-1"></option>
						<c:forEach var="company" items="${companies}">
							<option ${companyParam == company.key ? 'selected':''}
								value="${company.key}">
								<c:out value="${company.value.name}" />
							</option>
						</c:forEach>
					</select>
					<c:if test="${errorCompany != null}">
						<label class="error" for="company"> <c:out
								value="${errorCompany}"></c:out>
						</label>
					</c:if>
				</div>
				<br>
				<button type="submit" class="btn btn-default">Submit</button>
				or <a class="btn btn-success" id="cancel" href="dashboard">Cancel</a>
			</form>
		</div>
	</div>
</section>
<jsp:include page="include/footer.jsp" />