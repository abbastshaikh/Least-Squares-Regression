package regressor;
import javax.swing.JFrame;

import util.Graph;

public abstract class UnivariateRegressor extends Regressor {
	
	public UnivariateRegressor (double [][] X, double [] y) {	
		super(X, y);

    if (X[0].length != 1) {
			throw new IllegalArgumentException ("Univariate regressors can only use one regressor.");
		}

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

}
