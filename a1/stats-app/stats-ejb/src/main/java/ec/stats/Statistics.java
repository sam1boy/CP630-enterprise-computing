package ec.stats;

public class Statistics {
	public int count;
	public Double mini,
			max,
			mean,
			varianc,
			std;

	public Statistics(int count, Double min, Double max, Double mean, Double variance, Double std) {
		this.count = count;
		this.mini = min;
		this.max = max;
		this.mean = mean;
		this.varianc = variance;
		this.std = std;
	}

	public Statistics() {
		this.count = 0;
		this.mini = 0.0;
		this.max = 0.0;
		this.mean = 0.0;
		this.varianc = 0.0;
		this.std = 0.0;
	}
}