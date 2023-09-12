package regressor;

import util.Graph;
import util.MatrixOperations;

public class ExponentialRegressor extends UnivariateRegressor{
		
	protected double base;
	
	public ExponentialRegressor (double [][] X, double [] y, double b) {
		
		super(X, y);
		this.base = b;
		this.type = "exponential";
		
		this.coefficients = getSolution(toA(this.X), toB(this.y));
		this.coefficients[1] = Math.pow(base, this.coefficients[1]);
		
		this.equation = "y = " + String.format("%.4f", this.coefficients[1]) + " * " + (this.base == Math.E ? "e" : this.base) + "^(" + String.format("%.4f", this.coefficients[0]) + " * x)";
		System.out.println(toString());

	}
	
	public double [][] toA (double [][] X){
		return MatrixOperations.join(
				X, 
				MatrixOperations.constantMatrix(X.length, 1, 1)
				);
	}

	public double [][] toB (double [] y) {		
		double [][] b = MatrixOperations.transpose1D(y);
		
		for (int i = 0; i < b.length; i ++) {
			for (int j = 0; j < b[0].length; j ++) {
				b[i][j] = Math.log(b[i][j]) / Math.log(this.base);
			}
		}
		
		return b;
	}

	@Override
	public double [] predict (double [][] X) {
		
		double [] predictions = new double [X.length];
		
		for (int i = 0; i < X.length; i ++) {
			predictions[i] = this.coefficients[1] * Math.pow(this.base, this.coefficients[0] * X[i][0]);	
		}
		
		return predictions;
	}

	public double evaluate (double [][] X, double [] y) {
		return rSquared(predict(X), y);
	}
	
	@Override
	public Graph getGraph () {
    	return new Graph(this);
	}
	
	public double getBase() {
		return this.base;
	}

}
