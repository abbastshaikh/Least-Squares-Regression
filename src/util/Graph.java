package util;
import java.awt.*;
import javax.swing.*;

import regressor.ExponentialRegressor;
import regressor.Regressor;

public class Graph extends JPanel {

	// Data / Regression
    private double [] x;
	private double [] y;
	private String type;
	private String display;
	private String r2;
	private double [] coefficients;
	private double base;
	
	// Graph Properties
	private int width = 800;
	private int height = 600;
	
	private int padding = 25;
	private int labelPadding = 25;
	private Color lineColor = new Color(44, 102, 230, 180);
	private Color pointColor = new Color(100, 100, 100, 180);
	private Color gridColor = new Color(200, 200, 200, 200);
	private int pointWidth = 7;
	
	// Graph Dimensions
	private double xAxisMin;
	private double xAxisMax;
	private double yAxisMin;
	private double yAxisMax;
	private double xAxisRange;
	private double yAxisRange;
	private double xScale;
	private double yScale;
	
    public Graph(Regressor r) {
    	
    	setPreferredSize(new Dimension(width, height));
        
    	if (r.getX().length == r.getY().length) {
    		
	    	this.x = r.getX();
	        this.y = r.getY();
	        this.coefficients = r.getCoefficients();
	        this.type = r.getType();
	        this.display = "Equation: " + r.getEquation();
	        this.r2 = "R-Squared: " + r.evaluate(r.getX(), r.getY());
	        
	        xAxisMin = Math.floor(min(this.x));
			xAxisMax = Math.ceil(max(this.x));
			yAxisMin = Math.floor(min(this.y));
			yAxisMax = Math.ceil(max(this.y));
			xAxisRange = xAxisMax - xAxisMin;
			yAxisRange = yAxisMax - yAxisMin;
			xScale = (width - (2 * padding) - labelPadding) / (xAxisMax - xAxisMin);
			yScale = (height - (2 * padding) - labelPadding) / (yAxisMax - yAxisMin);
			
    	}
    	
    	else throw new IllegalArgumentException("Must provide same number of X and Y coordinates");
    }
    
    public Graph (ExponentialRegressor r) {
	
    	setPreferredSize(new Dimension(width, height));
        
    	if (r.getX().length == r.getY().length) {
    		
    		this.x = r.getX();
    		this.y = r.getY();
	        this.coefficients = r.getCoefficients();
	        this.type = r.getType();
	        this.display = "Equation: " + r.getEquation();
	        this.r2 = "R-Squared: " + r.evaluate(r.getX(), r.getY());
	        this.base = r.getBase();
	        
	        xAxisMin = Math.floor(min(this.x));
			xAxisMax = Math.ceil(max(this.x));
			yAxisMin = Math.floor(min(this.y));
			yAxisMax = Math.ceil(max(this.y));
			xAxisRange = xAxisMax - xAxisMin;
			yAxisRange = yAxisMax - yAxisMin;
			xScale = (width - (2 * padding) - labelPadding) / (xAxisMax - xAxisMin);
			yScale = (height - (2 * padding) - labelPadding) / (yAxisMax - yAxisMin);
			
    	}
    	
    	else throw new IllegalArgumentException("Must provide same number of X and Y coordinates");
    }

    @Override
    protected void paintComponent(Graphics g) {
    	
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		// Background
	    g2.setColor(Color.WHITE);
	    g2.fillRect(padding + labelPadding, padding, width - (2 * padding) - labelPadding, height - 2 * padding - labelPadding);
	    g2.setColor(Color.BLACK);
		
		// Y - Axis Labels and Gridlines
		for (int i = 0; i < (int) yAxisRange + 1; i++) {
		    	
			int x1 = padding + labelPadding;
			int x2 = pointWidth + padding + labelPadding;
		
			int y1 = height - ((i * (height - padding * 2 - labelPadding)) / (int) yAxisRange + padding + labelPadding);
			int y2 = y1;
		        
			g2.setColor(gridColor);
			g2.drawLine(padding + labelPadding + 1 + pointWidth, y1, width - padding, y2);
			
			g2.setColor(Color.BLACK);     
			String yLabel =  (int) (yAxisMin + i) + "";
		    FontMetrics metrics = g2.getFontMetrics();
		    int labelWidth = metrics.stringWidth(yLabel);
		    g2.drawString(yLabel, x1 - labelWidth - 5, y1 + (metrics.getHeight() / 2) - 3);   
		    g2.drawLine(x1, y1, x2, y2);
		    
		}
		
		// X - Axis Labels and Gridlines
		for (int i = 0; i < (int) xAxisRange + 1; i++) {
			
		    int x1 = i * (width - padding * 2 - labelPadding) / ((int) xAxisRange) + padding + labelPadding;
		    int x2 = x1;
		    
		    int y1 = height - padding - labelPadding;
		    int y2 = y1 - pointWidth;
		    
		    g2.setColor(gridColor);
		    g2.drawLine(x1, height - padding - labelPadding - 1 - pointWidth, x2, padding);
		    
		    g2.setColor(Color.BLACK);
		    String xLabel = (int) (xAxisMin + i) + "";
		    FontMetrics metrics = g2.getFontMetrics();
		    int labelWidth = metrics.stringWidth(xLabel);
		    g2.drawString(xLabel, x1 - labelWidth / 2, y1 + metrics.getHeight() + 3);
		    g2.drawLine(x1, y1, x2, y2);
		}
		
		// X and Y Axes  
		g2.drawLine(padding + labelPadding, height - padding - labelPadding, padding + labelPadding, padding);
		g2.drawLine(padding + labelPadding, height - padding - labelPadding, width - padding, height - padding - labelPadding);
		   
		        
		// Points
		g2.setColor(pointColor);
		
		for (int i = 0; i < x.length; i++) {
		    int x0 = (int) (padding + labelPadding + (x[i] - xAxisMin) * xScale) - pointWidth / 2;
		    int y0 = (int) (height - padding - labelPadding - ((y[i] - yAxisMin) * yScale)) - pointWidth / 2;
		    int ovalW = pointWidth;
		    int ovalH = pointWidth;
		    g2.fillOval(x0, y0, ovalW, ovalH);
		}
		
		
		// Regression
		g2.setColor(lineColor);
		
		if (type.equals("linear")) graphLinear(g2);
		else if (type.equals("polynomial")) graphPolynomial(g2);
		else if (type.equals("exponential")) graphExponential(g2);
		
		
		// Equation and R-Squared
	    g2.drawString(display, padding + labelPadding + 25, padding + 25);
	    g2.drawString(r2, padding + labelPadding + 25, padding + 25 + 15);
		    
	}
    
    private void graphLinear (Graphics g) {
    	
    	int x1 = (int) (padding + labelPadding);
	    int y1 = (int) (height - padding - labelPadding - ((coefficients[0] * xAxisMin + coefficients[1] - yAxisMin) * yScale));
	    int x2 = (int) (padding + labelPadding + xAxisRange * xScale);
	    int y2 = (int) (height - padding - labelPadding - ((coefficients[0] * xAxisMax + coefficients[1] - yAxisMin) * yScale));
	    
	    g.drawLine(x1, y1, x2, y2);
    	
    }
    
    private void graphPolynomial (Graphics g) {
    	
    	double increment = xAxisRange / 1000;
		
		int x1 = (int) (padding + labelPadding);
		
		double y0 = 0;
		for (int j = 0; j < coefficients.length; j ++) {
			y0 += coefficients[j] * Math.pow(xAxisMin, coefficients.length - j - 1);
		}
		
	    int y1 = (int) (height - padding - labelPadding - ((y0 - yAxisMin) * yScale));
	    
	    for (int i = 1; i < 1000; i++) {
	    	
	    	if (y1 < height - padding - labelPadding - yAxisRange * yScale) {
	    		break;
	    	}
	    	
	    	int x2 = (int) (padding + labelPadding + increment * i * xScale);
	    	
	    	y0 = 0;
			for (int j = 0; j < coefficients.length; j ++) {
				y0 += coefficients[j] * Math.pow(xAxisMin + increment * i, coefficients.length - j - 1);
			}	
	    	
		    int y2 = (int) (height - padding - labelPadding - ((y0 - yAxisMin) * yScale));
	    	
	    	g.drawLine(x1, y1, x2, y2);
	    	
	    	x1 = x2;
	    	y1 = y2;

        }
    }
    
    private void graphExponential (Graphics g) {
    	
    	double increment = xAxisRange / 1000;
		
		int x1 = (int) (padding + labelPadding);
		int y1 = (int) (height - padding - labelPadding - ((coefficients[1] * Math.pow(base, coefficients[0] * xAxisMin) - yAxisMin) * yScale));
		
		for (int i = 1; i < 1000; i++) {
	    	
			if (y1 < height - padding - labelPadding - yAxisRange * yScale) {
	    		break;
	    	}
			
			int x2 = (int) (padding + labelPadding + increment * i * xScale); 	
	    	int y2 = (int) (height - padding - labelPadding - ((coefficients[1] * Math.pow(base, coefficients[0] * (xAxisMin + increment * i)) - yAxisMin) * yScale));
		    
	    	g.drawLine(x1, y1, x2, y2);
	    	
	    	x1 = x2;
	    	y1 = y2;

        }
    	
    }

    private double min (double [] arr) {
    	
        double min = Double.MAX_VALUE;
        for (double val : arr) {
            min = Math.min(min, val);
        }
        return min;
    }

    private double max (double [] arr) {
    	
    	double max = Double.MIN_VALUE;
        for (double val : arr) {
        	max = Math.max(max, val);
        }
        return max;
        
    }
    
}