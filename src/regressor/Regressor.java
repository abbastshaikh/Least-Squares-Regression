package regressor;
import util.MatrixOperations;
import util.QRDecomposition;

// Abstract regressor to determine regression model of the form Ax = b
// With input data matrix (A), parameters (x), and target data matrix (b).

public abstract class Regressor {
	
	protected double [][] X;
	protected double [] y;
	protected double [] coefficients;
	protected String equation;
	protected String type;
	
	// Input matrices X and y to perform least squares regression
	// Each row in X represents an observation and each column a regressor
	// Each column in y represents an observation
	public Regressor (double [][] X, double [] y) {
		
		if (X.length != y.length) {
			throw new IllegalArgumentException ("X and y must be of same dimension.");
		}
		
		this.X = X;
		this.y = y;
	}
	
	abstract double [][] toA (double [][] X);
	abstract double [][] toB (double [] y);
	// abstract double [] predict (double [][] X);
	
	public double [] getSolution (double [][] A, double [][] b) {
		
		QRDecomposition qr = new QRDecomposition(A);
		return qr.solve(b);
		
	}

	public double [] predict (double [][] X){
		return MatrixOperations.transpose(
			MatrixOperations.matrixMultiply(toA(X), MatrixOperations.transpose1D(this.coefficients))
		)[0];
	}
	
	public double evaluate (double [][] X, double [] y) {
		return rSquared(predict(X), y);
	}
	
	public static double rSquared (double [] predicted, double [] actual) {
		
		if (predicted.length != actual.length) {
			throw new IllegalArgumentException("Must have same number of values in predicted and actual data sets");
		}
		
		double average = 0;
		for (int i = 0; i < actual.length; i ++) {
			average += actual[i] / actual.length;
		}
		
		double unexplainedVariance = 0;
		double totalVariance = 0;
		for (int i = 0; i < actual.length; i ++) {
			unexplainedVariance += Math.pow(predicted[i] - actual[i], 2);
			totalVariance += Math.pow(actual[i] - average, 2);
		}
		
		return 1.0 - unexplainedVariance / totalVariance;
		
	}
	
	public String toString () {
		return "Equation: " + this.equation;
	}
	
	public double[][] getX () {
		return this.X;
	}
	
	public double[] getY () {
		return this.y;
	}
	
	public double[] getCoefficients () {
		return this.coefficients;
	}
	
	public String getEquation () {
		return this.equation;
	}
	
	public String getType () {
		return this.type;
	}

}
