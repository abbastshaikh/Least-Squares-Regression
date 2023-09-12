# Ordinary Least Squares Regression

This repository provides an efficient implementation of the ordinary least squares regression algorithm in Java. The implementation allows for the computation of the least squares estimate of the parameters for a regression model given input data and labels, evaluation using the $R^2$ coefficient, and graphing of the least squares solution along with the input data for univariate regressors.

This code supports least squares analysis for models that are linear in their parameters, i.e. linear regression, multivariate linear regression, as well as polynomial and exponential linear regression through a change of basis.

To improve efficiency, the least squares estimate is computed using the Gram-Schmidt process to generate a QR decomposition of the data matrix and back-substitution to solve the resulting system. This implementation garners signficant improvements in the quantity and speed of computations.
