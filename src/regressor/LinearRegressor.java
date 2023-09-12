package regressor;
import util.MatrixOperations;

public class LinearRegressor extends Regressor {
	
	public LinearRegressor (double [] xCor, double [] yCor) {
		
		super(xCor, yCor);
		type = "linear";
		
		coefficients = getSolution(toA(x), toB(y));
		
		equation = "y = " + String.format("%.4f", coefficients[0]) + "x + " + String.format("%.4f", coefficients[1]);
		System.out.println(toString());
		
	}
	
	public double [][] toA (double [] xCor){
		return MatrixOperations.join(
				MatrixOperations.transpose1D(xCor), 
				MatrixOperations.constantMatrix(xCor.length, 1, 1)
				);
	}

	public double [][] toB (double [] yCor) {		
		return MatrixOperations.transpose1D(yCor);
	}
	
	public double [] predict (double [] xCor) {
		
		double [] predictions = new double [xCor.length];
		
		for (int i = 0; i < xCor.length; i ++) {
			predictions[i] = coefficients[0] * xCor[i]+ coefficients[1];	
		}
		
		return predictions;
	}
	
}
