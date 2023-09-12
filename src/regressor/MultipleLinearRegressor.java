package regressor;
import util.MatrixOperations;

public class MultipleLinearRegressor extends MultivariateRegressor {
	
	public MultipleLinearRegressor (double [][] xCor, double [] yCor) {
		
		super(xCor, yCor);
		
		coefficients = getSolution(toA(x), toB(y));
		equation = "y = ";
		
		for(int i = 0; i < x.length; i ++) {
			
			if(i == x.length - 1) {
				equation += String.format("%.4f", coefficients[i]) + "x" + (i + 1);
			}
			
			else {
				equation += String.format("%.4f", coefficients[i]) + "x" + (i + 1) + " ";
				if (coefficients[i + 1] >= 0) equation += "+ ";
			}
			
		}
		
		System.out.println(toString());
		
	}

	
	public double [][] toA (double [][] xCor){
		
		//return MatrixOperations.join(MatrixOperations.transpose(xCor), MatrixOperations.constantMatrix(xCor[0].length, 1, 1));
		return MatrixOperations.transpose(xCor);
	}

	public double [][] toB (double [] yCor) {		
		return MatrixOperations.transpose1D(yCor);
	}
	
	public double [] predict (double [][] xCor) {
		
		double [] predictions = new double [xCor[0].length];
		
		for (int i = 0; i < xCor[0].length; i ++) {
			predictions[i] = 0;
			for (int j = 0; j < xCor.length; j ++) {
				predictions[i] += coefficients[j] * xCor[j][i];
			}	
		}
		
		return predictions;
		
	}
	
}
