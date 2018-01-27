package de.ratopi.cnn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class NeuralNetworkDescriptor
{
	private int[] neuronsPerLayer;

	private List<double[][]> weights = new ArrayList<>();

	private Random random = new Random();

	public NeuralNetworkDescriptor( int[] neuronsPerLayer )
	{
		this.neuronsPerLayer = neuronsPerLayer;

		for ( int i = 1; i < neuronsPerLayer.length; i++ )
		{
			weights.add( createWeights( neuronsPerLayer[ i ], neuronsPerLayer[ i - 1 ] ) );
		}

		randomizeWeights();
	}

	private void randomizeWeights()
	{
		for ( double[][] w : weights )
		{
			randomizeWeights( w );
		}
	}

	private void randomizeWeights( double[][] weights )
	{
		for ( double[] w : weights )
		{
			randomizeWeights( w );
		}
	}

	private void randomizeWeights( double[] w )
	{
		for ( int i = 0; i < w.length; i++ )
		{
			w[ i ] = Math.random() * 2 - 1;
		}
	}

	public int getLayerCount()
	{
		return neuronsPerLayer.length;
	}

	public double[][] getWeightsFor( int layer )
	{
		return weights.get( layer - 1 );
	}

	public double[] getWeightsFor( final int layer, final int neuron )
	{
		return getWeightsFor( layer )[ neuron ];
	}

	public void setWeightsFor( final int layer, final int neuron, final double[] weights )
	{
		final double[] actualWeights = getWeightsFor( layer, neuron );

		if ( weights.length != actualWeights.length ) throw new IllegalArgumentException( "Given weights have to have length " + actualWeights.length );

		for ( int i = 0; i < actualWeights.length; i++ )
		{
			actualWeights[ i ] = weights[ i ];
		}
	}

	private double[][] createWeights( int thisLayerNeuronsCount, int preLayerNeuronsCount )
	{
		final double[][] weights = new double[ thisLayerNeuronsCount ][ preLayerNeuronsCount + 1 ];

		for ( int i = 0; i < weights.length; i++ )
		{
			for ( int j = 0; j < weights[ i ].length; j++ )
			{
				weights[ i ][ j ] = random.nextDouble() * 2 - 1;
			}
		}

		return weights;
	}

	@Override
	public String toString()
	{
		return "NeuralNetworkDescriptor{" +
				"neuronsPerLayer=" + Arrays.toString( neuronsPerLayer ) +
				", weights=" + getWeightsString() +
				'}';
	}

	private String getWeightsString()
	{
		final StringBuilder stringBuilder = new StringBuilder();
		for ( double[][] weight : weights )
		{
			stringBuilder.append( "[" );
			getWeightsString( stringBuilder, weight );
			stringBuilder.append( "]" );
		}

		return stringBuilder.toString();
	}

	private void getWeightsString( StringBuilder stringBuilder, double[][] weight )
	{
		for ( double[] doubles : weight )
		{
			stringBuilder.append( "[" );
			getWeightsString( stringBuilder, doubles );
			stringBuilder.append( "]" );
		}
	}

	private void getWeightsString( StringBuilder stringBuilder, double[] weight )
	{
		boolean addComma = false;
		for ( double v : weight )
		{
			if (addComma )stringBuilder.append( "," );
			stringBuilder.append( v );
			addComma = true;
		}
	}

}
