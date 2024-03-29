<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<! DOCTYPE html>
<html>
<head>
<title>Customer Registration Form</title>
<style>
.error {
	color: red
}
</style>
</head>
<body>
	<i>Fill out the form. Asterisk (*) means required.</i>
	<form:form action="processForm" modelAttribute="customer">
        First name: <form:input path="firstName" />
		<br>
        Last name (*): <form:input path="lastName" />
		<form:errors path="lastName" cssClass="error"></form:errors>
		<br>
		Free passes: <form:input path="freePasses" />
		<form:errors path="freePasses" cssClass="error"></form:errors>
		<br>
		Postal code: <form:input path="postalCode" />
		<form:errors path="postalCode" cssClass="error"></form:errors>
		<br>
		Course code: <form:input path="courseCode" />
        <form:errors path="courseCode" cssClass="error"></form:errors>
        <br>
		<input type="submit" value="Submit" />

	</form:form>

</body>
</html>