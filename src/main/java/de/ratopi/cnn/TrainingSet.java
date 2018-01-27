package de.ratopi.cnn;

import java.util.ArrayList;
import java.util.List;

public class TrainingSet
{
	private List<TrainingTuple> trainingTuples = new ArrayList<>();

	public void add( final double[] ins, final double[] outs )
	{
		trainingTuples.add( new TrainingTuple( ins, outs ) );
	}

	public Iterator getIterator()
	{
		return new Iterator();
	}

	public class Iterator
	{
		private int nextIndex = 0;

		public TrainingTuple next()
		{
			return trainingTuples.get( nextIndex++ );
		}

		public boolean hasNext()
		{
			return nextIndex < trainingTuples.size();
		}

		public void reset()
		{
			nextIndex = 0;
		}
	}
}
