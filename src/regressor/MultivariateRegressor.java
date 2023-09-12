package regressor;

public abstract class MultivariateRegressor extends Regressor {
	
	public MultivariateRegressor (double [][] X, double [] y) {	
		super(X, y);

        if (X[0].length < 2) {
			throw new IllegalArgumentException ("Multivariate regressors should use multiple, i.e > 1, regressor.");
		}

    }

}

