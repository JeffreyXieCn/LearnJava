<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<! DOCTYPE html>
<html>
<title>Customer Confirmation</title>
<body>
The customer is confirmed: ${customer.firstName} ${customer.lastName}
<br><br>
Free passes: ${customer.freePasses}
<br><br>
Postal code: ${customer.postalCode}
<br><br>
Course code: ${customer.courseCode}
<br><br>
</body>
</html>