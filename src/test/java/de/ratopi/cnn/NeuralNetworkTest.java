package de.ratopi.cnn;

public class NeuralNetworkTest
{
	public static void main( final String[] args )
	{
		final NeuralNetworkDescriptor descriptor = new NeuralNetworkDescriptor( new int[]{ 2, 2 } );

		/*
		descriptor.setWeightsFor( 1, 0, new double[]{ 0, 1, 1 } );
		descriptor.setWeightsFor( 1, 1, new double[]{ 1, 0, 1 } );
		*/

		final NeuralNetwork network = new NeuralNetwork( descriptor );

		final double[] result = network.propagate( new double[]{ 1, 0 } );
		for ( int i = 0; i < result.length; i++ )
		{
			System.out.println( i + " : " + result[ i ] );
		}

		System.out.println( descriptor );
	}
}
