package util;

/*
 * QR Decomposition using the Gram Schmidt Process, combining orthogonalization and normalization
 * 
 * APPLICATIONS:
 * Least squares solution using QR decomposition
 * Determinant for square matrices
 * Rank of matrices
 * 
 */

public class QRDecomposition {
	
	private double [][] Q;
	private double [][] QTranspose;
	private double [][] R;
	
	// Constructor gets QR Decomposition and stores in matrices Q, R, and QTranspose
	public QRDecomposition (double [][] matrix) {
		
		Q = new double [matrix.length][matrix[0].length];
		R = new double [matrix[0].length][matrix[0].length];
		QTranspose = new double [matrix[0].length][matrix.length]; 
			
		getQRDecomposition(MatrixOperations.transpose(matrix));
		
	}
	
	// Gets QR Decomposition of a matrix 
	// Input must be the transpose of the matrix
	private void getQRDecomposition (double [][] matrix) {
		
		double [] u = new double [matrix[0].length];
		double mag;
		
		for(int i = 0; i < matrix.length; i ++) {
			
			for (int j = 0; j < i; j ++) {
				R[j][i] = dotProduct(matrix[i], QTranspose[j]);
			}
			
			u = matrix[i].clone();
			
			for(int j = 0; j < i; j++) {
				for (int k = 0; k < u.length; k ++) {
					u[k] -= R[j][i] * QTranspose[j][k];
				}
			}
			
			mag = magnitude(u);
			for (int j = 0; j < u.length; j ++) {
				QTranspose[i][j] = u[j] / mag;
			}
			
			R[i][i] = dotProduct(matrix[i], QTranspose[i]);
			
		}
		
		Q = MatrixOperations.transpose(QTranspose);
		
	}
	
	// Gets dot product of two vectors
	private double dotProduct (double [] a, double [] e) {
		
		double dotProduct = 0;
		
		for (int i = 0; i < a.length ;i ++) {
			dotProduct += a[i] * e[i];
		}
		
		return dotProduct;
		
	}
	
	// Gets magnitude of a vector
	private double magnitude (double [] u) {
		
		double magnitude = 0;
		
		for(int i = 0; i < u.length; i ++) {
			magnitude += Math.pow(u[i], 2);
		}
		
		return Math.sqrt(magnitude);
			
	}
	 
	// Accessor Method: Q
	public double [][] getQ (){
		return Q;
	}
	
	// Accessor Method: R
	public double [][] getR (){
		return R;
	}
	
	// Solves system Ax = b using back substitution and QR decomposition 
	// b is 2D array
	public double [] solve (double [][] b) {
		
		if (QTranspose[0].length != b.length) {
			throw new IllegalArgumentException ("Matrices A and b must be of same length");
		}
		
		return solveByBackSubstitution(R, 
				MatrixOperations.matrixMultiply(QTranspose, b)
				);
		
	}
	
	// Solves system Ax = b using back substitution and QR decomposition 
	// b is 1D array
	public double [] solve (double [] b) {
		
		if (QTranspose[0].length != b.length) {
			throw new IllegalArgumentException ("Matrices A and b must be of same length");
		}
		
		return solveByBackSubstitution(R, 
				MatrixOperations.matrixMultiply(QTranspose, MatrixOperations.transpose1D(b))
				);
		
	}
	
	// Solves system using back substitution, input must be upper triangular matrix
	private double [] solveByBackSubstitution (double [][] A, double [][] b) {
		
		double [] solution = new double [A.length];
		
		for (int i = solution.length - 1; i >= 0; i --) {
			
			solution[i] = b[i][0];
			
			for(int j = solution.length - 1; j > i; j --) {
				solution[i] -= A[i][j] * solution[j];
			}
			
			solution[i] /= A[i][i];
		}
		
		return solution;
		
	}
	
	// Gets determinant using QR decomposition
	public double determinant () {
		
		if (Q[0].length != Q.length) {
			throw new IllegalArgumentException ("Matrix must be a square matrix");
		}
		
		return MatrixOperations.diagonalProduct(R);
	}
	
	// Gets rank using QR decomposition	
	public int rank () {
		
		int rank = 0;
		
		for (int i = 0; i < Math.min(R.length, R[0].length); i ++) {
			if (R[i][i] !=0) rank ++;
		}
		
		return rank;
	}
	
	// Returns if a matrix is full rank using QR decomposition
	public boolean isFullRank () {
		return rank() == R[0].length;
	}
}
