package assigOne;

public class Numerology {
	
	private int month = 0;
	private int day = 0;
	private int year = 0;
	private int result = 0;
	private String prediction = null;	
		
		
	public Numerology(int month, int day, int year, int result, String prediction) {
		this.month = month;
		this.day = day;
		this.year = year;
		this.result = result;
		this.prediction = prediction;
	}
			
	public String toString() {
		return month + "/" + day + "/" + year + " " + prediction;
	}
	
	public String getPrediction() {
		return prediction;
	}
	
	
		 
	}
	
	
	

