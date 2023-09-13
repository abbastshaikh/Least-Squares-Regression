package regressor;

public class PolynomialRegressor extends UnivariateRegressor {
		
	protected int degree;
	
	public PolynomialRegressor (double [][] X, double [] y, int d) {
		
		super(X, y);	
		this.degree = d;
		this.type = "polynomial";
		
		this.coefficients = getSolution(toDataMatrix(this.X), toTargetMatrix(this.y));

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

	protected double [][] toDataMatrix (double [][] X){
		
		double [][] A = new double [X.length][this.degree + 1];
		
		for (int i = 0; i < X.length; i ++) {
			for (int j = this.degree; j >= 0; j --) {
				A[i][this.degree - j] = Math.pow(X[i][0], j);
			}
		}
		
		return A;
	}
	
	public int getDegree (){
		return this.degree;
	}
	
}
