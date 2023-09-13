package regressor;
import util.MatrixOperations;

public class MultipleLinearRegressor extends MultivariateRegressor {
	
	public MultipleLinearRegressor (double [][] X, double [] y) {
		
		super(X, y);
		
		this.coefficients = getSolution(toDataMatrix(this.X), toTargetMatrix(this.y));
		this.equation = "y = ";
		
		for(int i = 0; i < X[0].length + 1; i ++) {
			
			if(i == X[0].length) {
				this.equation += String.format("%.4f", this.coefficients[i]);
			}
			
			else {
				this.equation += String.format("%.4f", this.coefficients[i]) + "x" + (i + 1) + " ";
				if (this.coefficients[i + 1] >= 0) this.equation += "+ ";
			}
			
		}
		
		System.out.println(toString());
		
	}

	
	protected double [][] toDataMatrix (double [][] X){
		return MatrixOperations.join(
				X, 
				MatrixOperations.constantMatrix(X.length, 1, 1)
			);
	}
	
}
