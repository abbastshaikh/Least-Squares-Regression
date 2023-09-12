package main;
import regressor.ExponentialRegressor;
import regressor.LinearRegressor;
import regressor.PolynomialRegressor;

/*
 * Least Squares Regression for:
 * 
 * Linear
 * Polynomial
 * Exponential
 * Multiple Linear 
 * 
 */

public class Main {

	public static void main (String [] args) {
		
		double [] xCor = {3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
		double [] yCor = {4.5, 5.5, 5.7, 6.6, 7.0, 7.7, 8.5, 8.7, 9.5, 9.7};				
		
		double [] xCor2 = {1, 1.5, 2, 2.5, 3, 3.5, 4, 4.5, 5};
		double [] yCor2 = {-0.15, 0.24, 0.68, 1.04, 1.21, 1.15, 0.86, 0.41, -0.08};
		
		double [] xCor3 = {2.0774, 2.3049, 3.0125, 4.7092, 5.5016};
		double [] yCor3 = {1.4509, 2.0089, 2.1536, 4.7438, 7.7260};
		
		LinearRegressor regressorOne = new LinearRegressor(xCor, yCor);
		System.out.println("R-Squared: " + regressorOne.evaluate(xCor, yCor));
		regressorOne.graph();
		
		PolynomialRegressor regressorTwo = new PolynomialRegressor(xCor2, yCor2, 2);
		System.out.println("R-Squared: " + regressorTwo.evaluate(xCor2, yCor2));
		regressorTwo.graph();
		
		ExponentialRegressor regressorThree = new ExponentialRegressor(xCor3, yCor3, Math.E);
		System.out.println("R-Squared: " + regressorThree.evaluate(xCor3, yCor3));
		regressorThree.graph();
		
	}

}
