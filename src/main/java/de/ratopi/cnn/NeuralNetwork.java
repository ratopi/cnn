package de.ratopi.cnn;

public class NeuralNetwork
{
	private NeuralNetworkDescriptor neuralNetworkDescriptor;

	public NeuralNetwork( NeuralNetworkDescriptor neuralNetworkDescriptor )
	{
		this.neuralNetworkDescriptor = neuralNetworkDescriptor;
	}

	public NeuralNetworkDescriptor getNeuralNetworkDescriptor()
	{
		return neuralNetworkDescriptor;
	}

	public double[] propagate( final double[] in )
	{
		double[] values = in;

		for ( int layer = 1; layer < neuralNetworkDescriptor.getLayerCount(); layer++ )
		{
			values = calculateValues( layer, values );
		}

		return values;
	}

	private double[] calculateValues( final int layer, final double[] in )
	{
		final double[][] weights = neuralNetworkDescriptor.getWeightsFor( layer );

		final double[] out = new double[ weights.length ];

		for ( int neuron = 0; neuron < weights.length; neuron++ )
		{
			final double sum = calculateWeightedSum( in, weights[ neuron ] );
			out[ neuron ] = Math.tanh( sum );
		}

		return out;
	}

	private double calculateWeightedSum( double[] in, double[] weight )
	{
		double sum = 0;
		for ( int i = 0; i < weight.length - 1; i++ )
		{
			sum += in[ i ] * weight[ i ];
		}

		sum += weight[ weight.length - 1 ]; // Zelle mit Wert "1"

		return sum;
	}

}
