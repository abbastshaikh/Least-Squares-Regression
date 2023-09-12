package regressor;
import util.MatrixOperations;

public class PolynomialRegressor extends Regressor {
		
	protected int degree;
	
	public PolynomialRegressor (double [] xCor, double [] yCor, int d) {
		
		super(xCor, yCor);	
		degree = d;
		type = "polynomial";
		
		coefficients = getSolution(toA(x), toB(y));

		equation = "y = ";
		
		for(int i = 0; i < degree + 1; i ++) {
			if (i == degree) {
				equation += String.format("%.4f", coefficients[i]); 
			}
			else if (i == degree - 1) {
				equation += String.format("%.4f", coefficients[i]) + "x ";
				if (coefficients[i + 1] >= 0) equation += "+ ";
			}
			else {
				equation += String.format("%.4f", coefficients[i]) + "x^" + (degree - i) + " ";
				if (coefficients[i + 1] >= 0) equation += "+ ";
			}
		}
		
		System.out.println(toString());
		
	}

	public double [][] toA (double [] xCor){
		
		double [][] A = new double [xCor.length][degree + 1];
		
		for (int i = 0; i < xCor.length; i ++) {
			for (int j = degree; j >= 0; j --) {
				A[i][degree - j] = Math.pow(xCor[i], j);
			}
		}
		
		return A;
	}

	public double [][] toB (double [] yCor) {		
		return MatrixOperations.transpose1D(yCor);
	}
	
	public double [] predict (double [] xCor) {
		
		double [] predictions = new double [xCor.length];
		
		for (int i = 0; i < xCor.length; i ++) {
			predictions[i] = 0;
			for (int j = 0; j < coefficients.length; j ++) {
				predictions[i] += coefficients[j] * Math.pow(xCor[i], coefficients.length - j - 1);
			}	
		}
		
		return predictions;

	}
	
}
