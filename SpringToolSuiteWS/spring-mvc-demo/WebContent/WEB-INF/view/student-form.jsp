<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<! DOCTYPE html>
<html>
<head>
<title>Student Registration Form</title>
</head>
<body>
	<form:form action="processForm" modelAttribute="student">
First name: <form:input path="firstName" />
		<br>
Last name: <form:input path="lastName" />
		<br>
Country:
		<form:select path="country">
			<form:options items="${theCountryOptionsFromFile}" />
			<!-- 
			<form:options items="${student.countryOptions}" />
			<form:option value="Brazil" label="Brazil" />
			<form:option value="France" label="France" />
			<form:option value="Germany" label="Germany" />
			<form:option value="India" label="India" />
		 -->
		</form:select>
		<br>
Favorite Language:
<form:radiobuttons path="favoriteLanguage"
			items="${student.favoriteLanguageOptions}" />
		<!-- 
Java <form:radiobutton path="favoriteLanguage" value="Java" />
C# <form:radiobutton path="favoriteLanguage" value="C#" />
PHP <form:radiobutton path="favoriteLanguage" value="PHP" />
Ruby <form:radiobutton path="favoriteLanguage" value="Ruby" />
 -->
		<br>
		Operating Systems:
		Linux <form:checkbox path="operatingSystems" value="Linux" />
		Mac OS <form:checkbox path="operatingSystems" value="Mac OS" />
		MS Windows <form:checkbox path="operatingSystems" value="MS Windows" />
		<br>
		<input type="submit" value="Submit" />
	</form:form>
</body>
</html>