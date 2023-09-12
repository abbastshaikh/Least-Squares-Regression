package util;

public class UnivariateGaussian {

    private double mu;
    private double sigma;

    public UnivariateGaussian (double mu, double sigma){
        this.mu = mu;
        this.sigma = sigma;
    }
    public double sample (){
        double uniform_sample = Math.random();
        double z = UnivariateGaussian.quantileApproximation(uniform_sample);
        return this.mu + z * this.sigma;
    }  

    private static double quantileApproximation (double p) {
        if (p > 0.5) return 5.5556 * (1 - Math.pow(((1 - p) / p), 0.1186));
        else return - 5.5556 * (1 - Math.pow((p / (1 - p)), 0.1186));
    }
    
}
