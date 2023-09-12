package regressor;
import util.MatrixOperations;

public abstract class MultivariateRegressor {
	
	protected double [][] x;
	protected double [] y;
	protected double [] coefficients;
	protected String equation;
	
	public MultivariateRegressor (double [][] xCor, double [] yCor) {
		
		if (xCor[0].length != yCor.length) {
			throw new IllegalArgumentException ("Must provide same number of X and Y coordinates");
		}
		
		this.x = xCor;
		this.y = yCor;
		
	}
	
	abstract double [][] toA (double [][] xCor);
	abstract double [][] toB (double [] yCor);
	
	abstract double [] predict (double [][] xCor);
	
	public double [] getSolution (double [][] A, double [][] b) {
		
		return MatrixOperations.solve(
				MatrixOperations.matrixMultiply(MatrixOperations.transpose(A), A), 
				MatrixOperations.matrixMultiply(MatrixOperations.transpose(A), b)
				);
	
	}
	
	public double evaluate (double [][] xCor, double [] yCor) {
		return rSquared(predict(xCor), yCor);
	}
	
	public static double rSquared (double [] predicted, double [] actual) {
		
		if (predicted.length != actual.length) {
			throw new IllegalArgumentException("Must have same number of values in predicted and actual data sets");
		}
		
		double unexplainedVariance = 0;
		for (int i = 0; i < predicted.length; i ++) {
			unexplainedVariance += Math.pow(predicted[i] - actual[i], 2);
		}
		
		double average = 0;
		for (int i = 0; i < actual.length; i ++) {
			average += actual[i];
		}
		average /= actual.length;
		
		double totalVariance = 0;
		for (int i = 0; i < actual.length; i ++) {
			totalVariance += Math.pow(actual[i] - average, 2);
		}
		
		return 1.0 - unexplainedVariance / totalVariance;
		
	}
	
	public String toString () {
		return "Regression: " + equation;
	}

}
