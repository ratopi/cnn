package de.ratopi.cnn;

public class TrainingTuple
{
	private double[] in;
	private double[] expected;

	public TrainingTuple( double[] in, double[] expected )
	{
		this.in = in;
		this.expected = expected;
	}

	public double[] getIn()
	{
		return in;
	}

	public double[] getExpected()
	{
		return expected;
	}
}
