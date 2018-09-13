var calculator = new Object();

calculator.add = function (n1, n2) n1 + n2;
calculator.subtract = function (n1, n2) n1 - n2;
calculator.multiply = function (n1, n2) n1 * n2;
calculator.divide = function (n1, n2) n1 / n2;
calculator.sum = function (n){
	var result = 0;
	for (var i = 1; i <= n; i++){
	  result = result + i;
	}
	return result;
};