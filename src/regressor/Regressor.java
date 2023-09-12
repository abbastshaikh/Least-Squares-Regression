package regressor;
import javax.swing.JFrame;

import util.Graph;
import util.QRDecomposition;

public abstract class Regressor {
	
	protected double [] x;
	protected double [] y;
	protected double [] coefficients;
	protected String equation;
	protected String type;
	
	public Regressor (double [] xCor, double [] yCor) {
		
		if (xCor.length != yCor.length) {
			throw new IllegalArgumentException ("Must provide same number of X and Y coordinates");
		}
		
		this.x = xCor;
		this.y = yCor;
		
		type = "none";
	}
	
	abstract double [][] toA (double [] xCor);
	abstract double [][] toB (double [] yCor);
	
	abstract double [] predict (double [] xCor);
	
	public double [] getSolution (double [][] A, double [][] b) {
		
		QRDecomposition qr = new QRDecomposition(A);
		return qr.solve(b);
		
	}
	
	public double evaluate (double [] xCor, double [] yCor) {
		return rSquared(predict(xCor), yCor);
	}
	
	public static double rSquared (double [] predicted, double [] actual) {
		
		if (predicted.length != actual.length) {
			throw new IllegalArgumentException("Must have same number of values in predicted and actual data sets");
		}
		
		double unexplainedVariance = 0;
		for (int i = 0; i < predicted.length; i ++) {
			unexplainedVariance += Math.pow(predicted[i] - actual[i], 2);
		}
		
		double average = 0;
		for (int i = 0; i < actual.length; i ++) {
			average += actual[i];
		}
		average /= actual.length;
		
		double totalVariance = 0;
		for (int i = 0; i < actual.length; i ++) {
			totalVariance += Math.pow(actual[i] - average, 2);
		}
		
		return 1.0 - unexplainedVariance / totalVariance;
		
	}
	
	public String toString () {
		return "Equation: " + equation;
	}
	
	public Graph getGraph () {
		return new Graph(this);
	}
	
	public void graph () {
        JFrame frame = new JFrame("Plot Regression");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(getGraph());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
   }
	
	public double[] getX () {
		return x;
	}
	
	public double[] getY () {
		return y;
	}
	
	public double[] getCoefficients () {
		return coefficients;
	}
	
	public String getEquation () {
		return equation;
	}
	
	public String getType () {
		return type;
	}

}
