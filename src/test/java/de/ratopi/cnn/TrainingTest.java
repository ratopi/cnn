package de.ratopi.cnn;

public class TrainingTest
{
	public static void main( final String[] args )
	{
		final NeuralNetworkDescriptor descriptor = new NeuralNetworkDescriptor( new int[]{ 2, 2, 2 } );
		final NeuralNetwork network = new NeuralNetwork( descriptor );

		final TrainingSet trainingSet = new TrainingSet();

		trainingSet.add( new double[]{ 1, 0 }, new double[]{ 0, 1 } );
		trainingSet.add( new double[]{ 0, 1 }, new double[]{ 1, 0 } );

		final RandomTraining randomTraining = new RandomTraining( network, trainingSet );

		randomTraining.doTraining( 10000000 );

		System.out.println( descriptor );
	}
}
