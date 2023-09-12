package regressor;
import util.MatrixOperations;

public class PolynomialRegressor extends UnivariateRegressor {
		
	protected int degree;
	
	public PolynomialRegressor (double [][] X, double [] y, int d) {
		
		super(X, y);	
		this.degree = d;
		this.type = "polynomial";
		
		this.coefficients = getSolution(toA(this.X), toB(this.y));

		this.equation = "y = ";
		
		for(int i = 0; i < this.degree + 1; i ++) {
			if (i == this.degree) {
				this.equation += String.format("%.4f", this.coefficients[i]); 
			}
			else if (i == this.degree - 1) {
				this.equation += String.format("%.4f", this.coefficients[i]) + "x ";
				this.equation += "+ ";
			}
			else {
				this.equation += String.format("%.4f", this.coefficients[i]) + "x^" + (this.degree - i) + " ";
				this.equation += "+ ";
			}
		}
		
		System.out.println(toString());
		
	}

	public double [][] toA (double [][] X){
		
		double [][] A = new double [X.length][degree + 1];
		
		for (int i = 0; i < X.length; i ++) {
			for (int j = degree; j >= 0; j --) {
				A[i][degree - j] = Math.pow(X[i][0], j);
			}
		}
		
		return A;
	}

	public double [][] toB (double [] y) {		
		return MatrixOperations.transpose1D(y);
	}
	
	public int getDegree (){
		return this.degree;
	}
	
}
