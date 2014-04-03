<jsp:include page="include/header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<section id="main">
	<h1>Update Computer</h1>
	<c:set var="computer" value="${computer}"></c:set>
	<form id=frm action="modifycomputer" method="POST"
		class="col-md-4 col-md-offset-4">
		<div class="form-group">
			<label for="name">Name</label> <input type="text"
				class="form-control" name="name" placeholder="Enter name"
				value="${computer.name}">
			<c:if test="${errorMap.get('errorName') != null}">
				<label class="error" for="name"> <c:out
						value="${errorMap.get('errorName')}"></c:out>
				</label>
			</c:if>
		</div>
		<div class="form-group">
			<label for="text">Introduced</label> <input type=text
				class="form-control" name="introducedDate"
				placeholder="date introduced" value="${computer.introduced}"
				id="datepickerIntroduced">
			<c:if test="${errorMap.get('errorIntroduced') != null}">
				<label class="error" for="datepickerIntroduced"> <c:out
						value="${errorMap.get('errorIntroduced')}"></c:out>
				</label>
			</c:if>
		</div>
		<div class="form-group">
			<label for="text">Discontinued</label> <input type="text"
				class="form-control" name="discontinuedDate"
				placeholder="date discontinued" value="${computer.discontinued}"
				id="datepickerDiscontinued">
			<c:if test="${errorMap.get('errorDiscontinued') != null}">
				<label class="error" for="datepickerDiscontinued"> <c:out
						value="${errorMap.get('errorDiscontinued')}"></c:out>
				</label>
			</c:if>
		</div>
		<label for="company">Company Name:</label>
		<div class="controls">
			<select class="form-control" name="company">
				<option value="-1"></option>
				<c:forEach var="company" items="${companies}">
					<option ${computer.companyId == company.key ? 'selected':''}
						value="${company.key}">
						<c:out value="${company.value.name}" />
					</option>
				</c:forEach>
			</select>
			<c:if test="${errorMap.get('errorCompany') != null}">
				<label class="error" for="company"> <c:out
						value="${errorMap.get('errorCompany')}"></c:out>
				</label>
			</c:if>
		</div>
		<br>
		<button type="submit" class="btn btn-default">Submit</button>
		or <a class="btn btn-success" id="cancel" href="dashboard">Cancel</a>
		<input type="hidden" name="idComputer" value="${computer.id}">
		<c:if test="${errorMap.get('errorIdComp') != null}">
			<label class="error" for="id"> <c:out
					value="${errorMap.get('errorIdComp')}"></c:out>
			</label>
		</c:if>
	</form>
</section>

<jsp:include page="include/footer.jsp" />