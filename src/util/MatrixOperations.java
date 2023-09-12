package util;
import java.util.Arrays;

/* Matrix Constructors:
 * 
 * Get MxN Constant Matrix
 * Get MxN Zero Matrix
 * Get MxN Random Integer Matrix in specified range
 * Get MxN Random Double Matrix in specified range
 * Get Copy of Matrix
 * Get NxN Identity Matrix
 * 
 */

/* Basic Matrix Operations:
 * 
 * Addition
 * Subtraction
 * Scalar Multiplication
 * Matrix Multiplication
 * Get Transpose
 * Get Transpose of 1D array as 2D array
 * Get Trace of a Matrix
 * Get Diagonal Product of a Matrix
 * 
 */

/* Solving Systems:
 * 
 * Convert to Row Echelon Form
 * Convert to Reduced Row Echelon Form
 * Convert Matrix to Upper Triangular Matrix
 * Convert Matrix to Lower Triangular Matrix
 * Solve System by Gauss-Jordan Reduction
 * Get Least Squares Solution
 * 
 */

/* Inverse and Related Properties:
 * 
 * Get Inverse of Matrix using Augmented Matrix
 * Get Inverse of Matrix using Adjoint and Determinant
 * Get Pseudo-Inverse
 * Get if Matrix is Singular
 * 
 */

/* Determinant and Related Properties:
 * 
 * Get a Minor of a Matrix
 * Get Determinant of 2x2 Matrix
 * Get Determinant of NxN Matrix using Cofactor Expansion
 * Get Determinant of NxN Matrix as Trace of Upper Triangular Matrix
 * Get Adjoint of Matrix
 * 
 */

/* Rank and Related Properties:
 * 
 * Get Rank of a Matrix
 * Get if matrix is of full rank
 * Get Nullity of a Matrix
 * 
 */

/* Matrix Manipulation:
 * 
 * Check Equality of Two Matrices
 * Join Two Matrices Horizontally
 * Swap Rows
 * Swap Columns
 * Get a Column from a Matrix
 * Get Multiple Columns from a Matrix
 * 
 */

public class MatrixOperations {
		
	//#################### Matrix Constructors ####################
	
	// Creates M x N Double Matrix and fills with constant c
	public static double [][] constantMatrix(int M, int N, double c) {
		
		double [][] matrix = new double [M][N];
		
		for (int i = 0; i < M; i ++) {
			for (int j = 0; j < N; j ++) {
				matrix[i][j] = c;
			}
		}
		
		return matrix;
	}
	
	// Creates M x N Double Matrix and fills with zeroes
	public static double [][] zeroMatrix(int M, int N) {
		
		double [][] matrix = new double [M][N];
		
		for (int i = 0; i < M; i ++) {
			for (int j = 0; j < N; j ++) {
				matrix[i][j] = 0;
			}
		}
		
		return matrix;
	}
	
	// Creates M x N Double Matrix and fills with random integers between minimum and maximum values
	public static double [][] randomMatrix(int M, int N, int min, int max) {
		
		double [][] matrix = new double [M][N];
		
		for (int i = 0; i < M; i ++) {
			for (int j = 0; j < N; j ++) {
				matrix[i][j] = (int) (Math.random() * (max - min) + min);
			}
		}
		
		return matrix;
	}
	
	// Creates matrix by copying another matrix
	public static double [][] copyMatrix (double [][] toCopy) {
		
		double [][] matrix = new double [toCopy.length][toCopy[0].length];
		
		for(int i = 0; i < matrix.length; i++) {
			matrix[i] = toCopy[i].clone();
		}
		
		return matrix;
	}
	
	// Creates N x N Identity Matrix
	public static double [][] identityMatrix(int N) {
		
		double [][] identity = new double[N][N];
		
		for (int i = 0; i < N; i ++) {
			for (int j = 0; j < N; j ++) {
				if (i == j) identity[i][j] = 1;
				else identity[i][j] = 0;
			}
		}
		
		return identity;
	}
	
	
	//#################### Basic Matrix Operations ####################
	
	// Add two matrices
	public static double [][] add (double [][] matrix1, double [][] matrix2){
		
		if (matrix1.length != matrix2.length || matrix1[0].length != matrix2[0].length) {
			throw new IllegalArgumentException("Matrices of must be of same dimensions.");
		}
		
		double [][] sum = new double [matrix1.length][matrix1[0].length];
		
		for (int i = 0; i < matrix1.length; i ++) {
			for (int j = 0; j < matrix1[0].length; j ++) {
				sum[i][j] = matrix1[i][j] + matrix2[i][j];
			}
		}
		
		return sum;
	}
	
	// Subtract matrix from another matrix
	public static double [][] subtract (double [][] matrix1, double [][] matrix2){
		
		if (matrix1.length != matrix2.length || matrix1[0].length != matrix2[0].length) {
			throw new IllegalArgumentException("Matrices of must be of same dimensions.");
		}
		
		double [][] difference = new double [matrix1.length][matrix1[0].length];
		for (int i = 0; i < matrix1.length; i ++) {
			for (int j = 0; j < matrix1[0].length; j ++) {
				difference[i][j] = matrix1[i][j] - matrix2[i][j];
			}
		}
		
		return difference; 
		
	}
	
	// Multiply matrix by scalar value
	public static double [][] scalarMultiply (double [][] matrix, double n){
		
		double [][] product = new double [matrix.length][matrix[0].length];
		for (int i = 0; i < matrix.length; i ++) {
			for (int j = 0; j < matrix[0].length; j ++) {
				product[i][j] = matrix[i][j] * n;
			}
		}
		
		return product; 
		
	}
	
	// Multiple two matrices
	public static double [][] matrixMultiply (double [][] matrix1, double [][] matrix2){
		
		if (matrix1[0].length != matrix2.length) {
			throw new IllegalArgumentException("Matrices of must be of correct dimensions.");
		}
		
		double [][] product = new double [matrix1.length][matrix2[0].length];
		
		for (int i = 0; i < matrix1.length; i ++) {
			for (int j = 0; j < matrix2[0].length; j ++) {
				for (int k = 0; k < matrix1[0].length; k++) {
                    product[i][j] += matrix1[i][k] * matrix2[k][j];
                }
			}
		}
		
		return product; 
		
	}
	
	// Get transpose of matrix
	public static double [][] transpose (double [][] matrix){
		
		double [][] transpose = new double [matrix[0].length][matrix.length];
		for (int i = 0; i < matrix.length; i ++) {
			for (int j = 0; j < matrix[0].length; j ++) {
				transpose[j][i] = matrix[i][j];
			}
		}
		
		return transpose;
		
	}
	
	// Get transpose of 1D matrix as 2D matrix
	public static double [][] transpose1D (double [] matrix){
		
		double [][] transpose = new double [matrix.length][1];
		for (int i = 0; i < matrix.length; i ++) {
			transpose[i][0] = matrix[i];
		}
		
		return transpose;
	}
	
	// Calculate trace of a square matrix
	public static double trace (double [][] matrix) {
		
		if (matrix.length != matrix[0].length) {
			throw new IllegalArgumentException("Matrix must be a square matrix.");
		}
		
		double trace = 0;
		
		for (int i = 0; i < matrix.length; i ++) {
			trace += matrix[i][i];
		}
		
		return trace;
		
	}
	
	public static double diagonalProduct (double [][] matrix) {
		
		if (matrix.length != matrix[0].length) {
			throw new IllegalArgumentException("Matrix must be a square matrix.");
		}
		
		double product = 1;
		
		for (int i = 0; i < matrix.length; i ++) {
			product *= matrix[i][i];
		}
		
		return product;
		
	}
	
	
	//#################### Solving Systems ####################
	
	// Convert a matrix to Row-Echelon Form using Partial Pivoting
	public static double [][] ref (double [][] matrix){
		
		double [][] ref = copyMatrix(matrix);
		
		int count = 0;
		int maxIndex;
		double divide;
		double factor;
		
		for (int i = 0; i < Math.min(ref.length, ref[0].length); i ++) {
			
			maxIndex = count;
			
			for (int j = count; j < ref.length; j++) {
				if (Math.abs(ref[j][i]) > Math.abs(ref[maxIndex][i])) {
					maxIndex = j;
				}
			}
			
			if (maxIndex != count) {
				swapRows(ref, maxIndex, count);
			}
			
			divide = ref[count][i];
			
			if (divide != 0) {
				
				for (int j = 0; j < ref[0].length; j ++) {
					ref[count][j] /= divide;
				}
			
				for (int j = count + 1; j < ref.length; j ++) {
					
					factor = ref[j][i];
					
					for (int k = 0; k < ref[0].length; k ++) {
						ref[j][k] -= (ref[count][k] * factor);
					}
					
				}
				
				count ++;
			}
			
		}

		return ref;
	}
	
	// Convert a matrix to Reduced Row-Echelon Form using Partial Pivoting
	public static double [][] rref (double [][] matrix){
		
		double [][] rref = copyMatrix(matrix);
		
		int count = 0;
		int maxIndex;
		double divide;
		double factor;
		
		for (int i = 0; i < Math.min(rref.length, rref[0].length); i ++) {
			
			maxIndex = count;
			
			for (int j = count; j < rref.length; j++) {
				if (Math.abs(rref[j][i]) > Math.abs(rref[maxIndex][i])) {
					maxIndex = j;
				}
			}
			
			if (maxIndex != count) {
				swapRows(rref, maxIndex, count);
			}
			
			divide = rref[count][i];
			double tol = 1E-10;
			
			if (divide > tol) {
				
				for (int j = 0; j < rref[0].length; j ++) {
					rref[count][j] /= divide;
				}
			
				for (int j = 0; j < rref.length; j ++) {
					
					factor = rref[j][i];
					
					if (j != count) {
						
						for (int k = 0; k < rref[0].length; k ++) {
							rref[j][k] -= (rref[count][k] * factor);
						}
						
					}
					
				}
				
				count ++;
			}
			
		}

		return rref;
	}
	
	// Convert a matrix to an Upper Triangular Matrix using Partial Pivoting
	public static double [][] toUpperTriangular (double [][] matrix){
		
		if (matrix.length != matrix[0].length) {
			throw new IllegalArgumentException("Matrix must be a square matrix.");
		}
		
		double [][] U = copyMatrix(matrix);
		
		int maxIndex;
		double factor;
		
		for (int i = 0; i < U.length; i ++) {
			
			maxIndex = i;
			
			for (int j = i; j < U.length; j++) {
				if (Math.abs(U[j][i]) > Math.abs(U[maxIndex][i])) {
					maxIndex = j;
				}
			}
			
			if (maxIndex != i) {
				swapRows(U, maxIndex, i);
			}
			
			for (int j = i + 1; j < U.length; j ++) {
				
				factor = U[j][i] / U[i][i];
				
				for (int k = 0; k < U[0].length; k ++) {
					U[j][k] -= (U[i][k] * factor);
				}
				
			}
			
		}
	
		return U;
	
	}
	
	// Convert a matrix to an Lower Triangular Matrix using Partial Pivoting
	public static double [][] toLowerTriangular (double [][] matrix){
		
		if (matrix.length != matrix[0].length) {
			throw new IllegalArgumentException("Matrix must be a square matrix.");
		}
		
		double [][] L = copyMatrix(matrix);
		
		int maxIndex;
		double factor;
		
		for (int i = L.length - 1; i >= 0; i --) {
			
			maxIndex = i;
			
			for (int j = i; j >= 0; j --) {
				if (Math.abs(L[j][i]) > Math.abs(L[maxIndex][i])) {
					maxIndex = j;
				}
			}
			
			if (maxIndex != i) {
				swapRows(L, maxIndex, i);
			}
			
			for (int j = i - 1; j >= 0; j --) {
				
				factor = L[j][i] / L[i][i];
				
				for (int k = 0; k < L[0].length; k ++) {
					L[j][k] -= (L[i][k] * factor);
				}
				
			}
			
		}
	
		return L;
	
	}
	
	// Solve a system provided coefficient matrix and solutions as a 1D array by Gauss-Jordan Reduction
	// Only works for systems with one solution
	public static double [] solve (double [][] A, double [] b) {
		
		if (A.length != b.length) {
			throw new IllegalArgumentException ("Matrices A and b must be of same length");
		}
		
		return getColumn(rref(join(A, transpose1D(b))), A[0].length);
		
	}
	
	// Solve a system provided coefficient matrix and solutions as a 2D array by Gauss-Jordan Reduction
	// Only works for systems with one solution
	public static double [] solve (double [][] A, double [][] b) {
		
		if (A.length != b.length) {
			throw new IllegalArgumentException ("Matrices A and b must be of same length");
		}
		
		return getColumn(rref(join(A, b)), A[0].length);
		
	}
	
	// Get the least squares solution provided coefficient matrix and solutions as a 1D array
	public static double [] leastSquares (double [][] A, double [] b) {
		return solve(matrixMultiply(transpose(A), A), matrixMultiply(transpose(A), transpose1D(b)));
	}
	
	// Get the least squares solution provided coefficient matrix and solutions as a 2D array
	public static double [] leastSquares (double [][] A, double [][] b) {
		return solve(matrixMultiply(transpose(A), A), matrixMultiply(transpose(A), b));
	}
	
	
	//#################### Inverse and Related Properties ####################
	
	// Gets inverse of a matrix through the augmented matrix method
	// If matrix is singular, returns null and prints "Matrix is singular" to console
	public static double [][] inverseByAugmentedMatrix(double [][] matrix){
		
		if (matrix.length != matrix[0].length) {
			throw new IllegalArgumentException("Matrix must be a square matrix.");
		}
		
		double [][] identity = identityMatrix(matrix.length);		
		double [][] augmented = rref(join(matrix, identity));
		double [][] check = getColumns(augmented, 0, matrix.length);
        
        if(!isEqual(check, identity)) {
        	System.out.println("Matrix is singular.");
        	return null;
        }
     
        return getColumns(augmented, matrix.length, matrix.length);

	}
	
	// Gets inverse of a matrix using the adjoint and determinant
	// If matrix is singular, returns null and prints "Matrix is singular" to console
	public static double [][] inverseByAdjoint(double [][] matrix){
		
		if (matrix.length != matrix[0].length) {
			throw new IllegalArgumentException("Matrix must be a square matrix.");
		}
		
		double [][] adjoint = adjoint(matrix);
		double determinant = determinantByTriangularMatrix(matrix);
		
		if (determinant == 0) {
			System.out.println("Matrix is singular");
        	return null;
		}
		
		return scalarMultiply(adjoint, (1.0/determinant));

	}
	
	// Gets pseudo-inverse of a matrix
	public static double [][] pseudoInverse (double [][] matrix){
		return matrixMultiply(
				inverseByAdjoint(matrixMultiply(transpose(matrix), matrix)), 
				transpose(matrix)
				);
	}
	
	// Returns if a matrix is singular
	public static boolean isSingular (double [][] matrix) {
		return determinantByTriangularMatrix(matrix) == 0;
	}
	
	
	//#################### Determinant and Related Properties ####################
	
	// Get the minor M(hk) of a square matrix
	public static double [][] minor (double [][] matrix, int h, int k){
		
		if (matrix.length != matrix[0].length) {
			throw new IllegalArgumentException("Matrix must be a square matrix.");
		}
		
		double [][] minor = new double [matrix.length - 1][matrix.length - 1];
		int countI = 0;
		int countJ = 0;
		
		for (int i = 0; i < matrix.length; i ++) {
			
			if(i != h) {
				
				for (int j = 0; j < matrix[0].length; j ++) {
					if(j != k) {
						minor[countI][countJ] = matrix[i][j];
						countJ++;
					}
				}

				countJ = 0;
				countI ++;
			}
		}
		
		return minor;
		
	}
	
	// Calculate determinant of 2 x 2 Matrix
	public static double determinant2x2 (double [][] matrix) {
		
		if (matrix.length != 2 || matrix[0].length != 2) {
			throw new IllegalArgumentException("Matrix must be a 2 x 2 matrix.");
		}
		
		return (matrix[0][0] * matrix[1][1]) - (matrix[0][1] * matrix[1][0]);
		
	}
	
	// Calculate determinant of N x N Matrix recursively using Cofactor Expansion
	public static double determinantByCofactorExpansion (double [][] matrix) {
		
		if (matrix.length != matrix[0].length) {
			throw new IllegalArgumentException("Matrix must be a square matrix.");
		}
			
		if (matrix.length == 2) {
			return determinant2x2(matrix);
		}
	
		double determinant = 0;
		
		for (int i = 0; i < matrix.length; i++) {
			determinant += matrix[i][0] * Math.pow(-1, i) * determinantByCofactorExpansion(minor(matrix, i, 0));
		}
		
		return determinant;	

	}
	
	// Calculate determinant of N x N Matrix by converting to a triangular matrix through elementary row operations
	public static double determinantByTriangularMatrix (double [][] matrix){
		
		if (matrix.length != matrix[0].length) {
			throw new IllegalArgumentException("Matrix must be a square matrix.");
		}
		
		double [][] U = copyMatrix(matrix);
		
		int numPivots = 0;
		int maxIndex;
		double factor;
		
		for (int i = 0; i < U.length; i ++) {
			
			maxIndex = i;
			
			for (int j = i; j < U.length; j++) {
				if (Math.abs(U[j][i]) > Math.abs(U[maxIndex][i])) {
					maxIndex = j;
				}
			}
			
			if (maxIndex != i) {
				numPivots ++;
				swapRows(U, maxIndex, i);
			}
			
			if (U[i][i] == 0) {
				return 0;
			}
			
			for (int j = i + 1; j < U.length; j ++) {
				
				factor = U[j][i] / U[i][i];
				
				for (int k = 0; k < U[0].length; k ++) {
					U[j][k] -= (U[i][k] * factor);
				}
				
			}
			
		}

		return diagonalProduct(U) * (Math.pow(-1, numPivots));
	}
	
	// Get adjoint of a matrix
	public static double [][] adjoint (double [][] matrix){
		
		if (matrix.length != matrix[0].length) {
			throw new IllegalArgumentException("Matrix must be a square matrix.");
		}
		
		double [][] adjoint = new double [matrix.length][matrix.length];
		
		for (int i = 0; i < matrix.length; i ++) {
			for (int j = 0; j < matrix.length; j ++) {
				adjoint[i][j] = Math.pow(-1, i + j) * determinantByTriangularMatrix(minor(matrix, i, j));
			}
		}
		
		adjoint = transpose(adjoint);
		return adjoint;
		
	}
	
	
	//#################### Rank and Related Properties ####################
	
	// Returns rank of matrix as number of non-zero rows in row echelon form	
	public static int rank (double [][] matrix) {
		
		int rank = 0;
		double [][] ref = ref(matrix);
		
		boolean zeroed;
		for (int i = 0; i < ref.length; i ++) {
			
			zeroed = true;
			
			for (int j = 0; j < ref[0].length; j ++) {
				if (ref[i][j] != 0) {
					zeroed = false;
					break;
				}
			}
			
			if (!zeroed) rank ++;
		}
		
		return rank;
	}
	
	// Returns if a matrix is full rank
	public static boolean isFullRank (double [][] matrix) {
		return rank(matrix) == Math.min(matrix.length, matrix[0].length);
	}
	
	// Returns nullity of a matrix
	public static int nullity (double [][] matrix) {
		return matrix[0].length - rank(matrix);
	}

	
	//#################### Matrix Manipulation ####################
	
	// Returns equality of two matrices
	public static boolean isEqual (double [][] matrix1, double [][] matrix2) {
		
		if (matrix1.length != matrix2.length || matrix1[0].length != matrix2[0].length) {
			return false;
		}
		
		for(int i = 0; i < matrix1.length; i ++) {
			for (int j = 0; j < matrix1[0].length; j++) {
				if (matrix1[i][j] != matrix2[i][j]) return false;
			}
		}
		
		return true;
	}
	
	// Returns matrix produced by joining two matrix horizontally
	public static double [][] join (double [][] matrix1, double [][] matrix2){
		
		if (matrix1.length != matrix2.length) {
			throw new IllegalArgumentException ("Matrices must be of same length");
		}
		
		double [][] joined = new double [matrix1.length][matrix1[0].length + matrix2[0].length];
		
		for(int i = 0; i < matrix1.length; i ++) {
			
            for (int j = 0; j < matrix1[0].length + matrix2[0].length; j++) {
            	
                if (j < matrix1[0].length)
                	joined[i][j] = matrix1[i][j];
                else
                	joined[i][j] = matrix2[i][j - matrix1[0].length];
            }
		}
		
		return joined;
	}
	
	// Swap rows of matrix (in place)
	public static void swapRows (double [][] matrix, int rowOneIndex, int rowTwoIndex){
		
		double [] temp = matrix[rowOneIndex];
		matrix[rowOneIndex] = matrix[rowTwoIndex];
		matrix[rowTwoIndex] = temp;
		
	}
	
	// Swap columns of matrix (in place)
	public static void swapColumns (double [][] matrix, int colOneIndex, int colTwoIndex){
		
		double [] colOne = getColumn(matrix, colOneIndex);
		double [] colTwo = getColumn(matrix, colTwoIndex);
		
		for (int i = 0; i < matrix.length; i++) {
			matrix[i][colOneIndex] = colTwo[i];
			matrix[i][colTwoIndex] = colOne[i];
		}
		
	}
	
	// Get single column of matrix as 1D array
	public static double [] getColumn (double [][] matrix, int colNum) {
		
		double [] column = new double [matrix.length];
		
		for(int i = 0; i < matrix.length; i ++) {
			column[i] = matrix[i][colNum];
		}
		
		return column;
	}
	
	// Get range of columns of matrix as 2D array
	public static double [][] getColumns (double [][] matrix, int start, int numCols) {
		
		double [][] columns = new double [matrix.length][numCols];
		
		for(int i = 0; i < matrix.length; i ++) {
			columns[i] = Arrays.copyOfRange(matrix[i], start, start + numCols);
		}
		
		return columns;
		
	}

}