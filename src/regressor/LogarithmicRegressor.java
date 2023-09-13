package regressor;
import util.MatrixOperations;

public class LogarithmicRegressor extends UnivariateRegressor {
	
	public LogarithmicRegressor (double [][] X, double [] y) {
		
		super(X, y);
		
		this.type = "logarithmic";
		this.coefficients = getSolution(toDataMatrix(this.X), toTargetMatrix(this.y));
		this.equation = "y = " + String.format("%.4f", this.coefficients[0]) + "ln(x) + " + String.format("%.4f", this.coefficients[1]);
		System.out.println(toString());
		
	}
	
	protected double [][] toDataMatrix (double [][] X){
		
		double [][] X_log = new double [X.length][X[0].length];
		for (int i = 0; i < X.length; i++){
			X_log[i][0] = Math.log(X[i][0]);
		}
		
		return MatrixOperations.join(
				X_log, 
				MatrixOperations.constantMatrix(X.length, 1, 1)
			);
	}
	
}
