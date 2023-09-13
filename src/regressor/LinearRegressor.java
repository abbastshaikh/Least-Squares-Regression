package regressor;
import util.MatrixOperations;

public class LinearRegressor extends UnivariateRegressor {
	
	public LinearRegressor (double [][] X, double [] y) {
		
		super(X, y);
		
		this.type = "linear";
		this.coefficients = getSolution(toDataMatrix(this.X), toTargetMatrix(this.y));
		this.equation = "y = " + String.format("%.4f", this.coefficients[0]) + "x + " + String.format("%.4f", this.coefficients[1]);
		System.out.println(toString());
		
	}
	
	protected double [][] toDataMatrix (double [][] X){
		return MatrixOperations.join(
				X, 
				MatrixOperations.constantMatrix(X.length, 1, 1)
			);
	}
	
}
