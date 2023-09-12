package regressor;
import util.Graph;
import util.MatrixOperations;

public class ExponentialRegressor extends Regressor{
		
	protected double base;
	
	public ExponentialRegressor (double [] xCor, double [] yCor, double b) {
		
		super(xCor, yCor);
		base = b;
		type = "exponential";
		
		coefficients = getSolution(toA(x), toB(y));
		coefficients[1] = Math.pow(base, coefficients[1]);
		
		equation = "y = " + String.format("%.4f", coefficients[1]) + " * " + (base == Math.E ? "e" : base) + "^(" + String.format("%.4f", coefficients[0]) + " * x)";
		System.out.println(toString());

		
	}
	
	public double [][] toA (double [] xCor){
		return MatrixOperations.join(
				MatrixOperations.transpose1D(xCor), 
				MatrixOperations.constantMatrix(xCor.length, 1, 1)
				);
	}

	public double [][] toB (double [] yCor) {		
		double [][] b = MatrixOperations.transpose1D(yCor);
		
		for (int i = 0; i < b.length; i ++) {
			for (int j = 0; j < b[0].length; j ++) {
				b[i][j] = Math.log(b[i][j]) / Math.log(base);
			}
		}
		
		return b;
	}
	
	public double [] predict (double [] xCor) {
		
		double [] predictions = new double [xCor.length];
		
		for (int i = 0; i < xCor.length; i ++) {
			predictions[i] = coefficients[1] * Math.pow(base, coefficients[0] * xCor[i]);	
		}
		
		return predictions;
	}
	
	
	@Override
	public Graph getGraph () {
    	return new Graph(this);
	}
	
	public double getBase() {
		return base;
	}

}
