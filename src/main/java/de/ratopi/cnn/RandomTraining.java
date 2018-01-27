package de.ratopi.cnn;

import java.util.Random;

public class RandomTraining
{
	private NeuralNetwork neuralNetwork;
	private TrainingSet trainingSet;

	private Random random = new Random();

	public RandomTraining( final NeuralNetwork neuralNetwork, final TrainingSet trainingSet )
	{
		this.neuralNetwork = neuralNetwork;
		this.trainingSet = trainingSet;
	}

	public void doTraining( final int loops )
	{
		final NeuralNetworkDescriptor descriptor = neuralNetwork.getNeuralNetworkDescriptor();

		final TrainingSet.Iterator iterator = trainingSet.getIterator();

		for ( int loop = 0; loop < loops; loop++ )
		{
			iterator.reset();
			while ( iterator.hasNext() )
			{
				final TrainingTuple trainingTuple = iterator.next();

				final double error = getError( trainingTuple );

				final int layer = random.nextInt( descriptor.getLayerCount() - 1 );
				final double[][] weightsFor = descriptor.getWeightsFor( layer + 1 );

				final int i = random.nextInt( weightsFor.length );
				final int j = random.nextInt( weightsFor[ i ].length );

				final double oldValue = weightsFor[ i ][ j ];
				weightsFor[ i ][ j ] += ( random.nextDouble() - .5 ) * error;

				final double newError = getError( trainingTuple );

				if ( newError > error )
				{
					weightsFor[ i ][ j ] = oldValue;
					System.out.println( error );
				}
				else
				{
					System.out.println( newError );
				}
			}
		}
	}

	private double getError( TrainingTuple trainingTuple )
	{
		final double[] outs = neuralNetwork.propagate( trainingTuple.getIn() );
		return calculateError( outs, trainingTuple.getExpected() );
	}

	private double calculateError( double[] outs, double[] expected )
	{
		if ( outs.length != expected.length ) throw new IllegalArgumentException( "Wrong dimension" );

		double error = 0;
		for ( int i = 0; i < outs.length; i++ )
		{
			final double diff = outs[ i ] - expected[ i ];
			error += diff * diff;
		}

		return Math.sqrt( error );
	}
}
