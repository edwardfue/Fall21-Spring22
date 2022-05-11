
public class TestTimes implements TestTimesInterface {
	private double[] testTimes   = new double[10];
	private double[] memoryUsage = new double[10];
	private int counts = 0;
	private TimeUnits timeUnits = TimeUnits.NanoSeconds;
	private MemoryUnits memoryUnits = MemoryUnits.Bytes;
	
	private double seconds = 1_000_000_000;
	private double milliSeconds = 1_000_000;
	private double microSeconds = 1_000;
	private double kiloBytes = 1_024;
	private double megaBytes = 1_048_576;
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public TimeUnits getTimeUnits() {
		// TODO Auto-generated method stub	
		return timeUnits;
	}

	@Override
	public void setTimeUnits(TimeUnits timeUnits) {
		// TODO Auto-generated method stub
			this.timeUnits = timeUnits;	
	}

	@Override
	public MemoryUnits getMemoryUnits() {
		// TODO Auto-generated method stub
		return memoryUnits;
	}

	@Override
	public void setMemoryUnits(MemoryUnits memoryUnits) {
		// TODO Auto-generated method stub
		this.memoryUnits = memoryUnits;		
	}


	@Override
	public double getLastTestTime() {
		// TODO Auto-generated method stub
		
		if(counts != 0) {
			
			switch (this.timeUnits){
				case NanoSeconds:
					return (double)this.testTimes[counts -1];
				case MilliSeconds:
					double time = (double)this.testTimes[counts -1] / 1_000.0;
					return time;
				case MicroSeconds:
					double time1 = (double)this.testTimes[counts -1] / 1_000_000.0;
					return time1;
				case Seconds:
					double time2 = (double)this.testTimes[counts -1]/ 1_000_000_000.0;
					return time2;
			}
			
			return this.testTimes[counts -1];
		}
	
		else {
			return 0;
		}
		/*
		if (this.timeUnits == TimeUnits.Seconds) {
			return ((this.testTimes[counts - 1]) / this.seconds);
			} else if (this.timeUnits == TimeUnits.MilliSeconds) {
			return ((this.testTimes[counts - 1]) / this.milliSeconds);
			} else if (this.timeUnits == TimeUnits.MicroSeconds) {
			return ((this.testTimes[counts - 1]) / this.microSeconds);
			} else {
			return this.testTimes[counts - 1];
			}
			*/
	}



	@Override
	public double getLastMemoryUsage() {
		// TODO Auto-generated method stub
		
		if(counts != 0) {
			switch(this.memoryUnits){
			case Bytes:
				return (double) this.memoryUsage[counts -1];
			case KiloBytes:
				double mem = (double) this.memoryUsage[counts -1] / 1024;
				return mem;
			case MegaBytes:
				double mem1 = (double) this.memoryUsage[counts -1] /(1024*1024) ;
				return mem1;
			}
			return this.memoryUsage[counts -1];
		}
		else {
			return 0;
		}
		/*
		if (this.memoryUnits == MemoryUnits.KiloBytes) {
			return (this.memoryUsage[this.counts - 1]) / this.kiloBytes;
			} else if (this.memoryUnits == MemoryUnits.MegaBytes) {
			return (memoryUsage[this.counts - 1]) / this.megaBytes;
			} else {
			// bytes
			return memoryUsage[counts - 1];
			}
			*/
	}

	
	@Override
	public double[] getTestTimes() {
		// TODO Auto-generated method stub
		/*
		//double[] newTestTimes = new double[10];
		for(int i = 0; i < this.testTimes.length; i++) {
			double[] newTestTimes = new double[10];
			switch(this.timeUnits){
			//switch(this.timeUnits){
			case NanoSeconds:
				return newTestTimes;
				//break;
			case MilliSeconds:
				
				newTestTimes[i] = newTestTimes[i]/1_000 ;	
				//break;
				return newTestTimes;
			case MicroSeconds:
				newTestTimes[i] = newTestTimes[i]/1_000_000 ;
				//break;
				return newTestTimes;
			case Seconds:
				
				newTestTimes[i] = (newTestTimes[i])/1_000_000 ;
				return newTestTimes;
			}
		}
		*/
		if (this.timeUnits == TimeUnits.Seconds) {
			double[] secArray = new double[10];
			for (int i = 0; i < 10; i++) {
			secArray[i] = (this.testTimes[i]) / this.seconds;
			}
			return secArray;
			}

			  
			else if (this.timeUnits == TimeUnits.MilliSeconds) {
			double[] milliArray = new double[10];
			for (int i = 0; i < 10; i++) {
			milliArray[i] = (this.testTimes[i]) / this.milliSeconds;
			}
			return milliArray;
			}

			else if (this.timeUnits == TimeUnits.MicroSeconds) {
			double[] microArray = new double[10];
			for (int i = 0; i < 10; i++) {
			microArray[i] = (this.testTimes[i]) / this.microSeconds;
			}

			return microArray;
			}
			// nanoseconds
			return testTimes;
		
		//return newTestTimes;
		//return testTimes;

	}



	@Override
	public double[] getMemoryUsages() {
		// TODO Auto-generated method stub
		/*
		double[] newMemoryUsage = new double[10];
		for(int i = 0; i < this.memoryUsage.length; i++) {
			newMemoryUsage[i] = this.memoryUsage[i];
		}
		return newMemoryUsage;
		*/
		if(this.memoryUnits == MemoryUnits.KiloBytes) {
			double [] kilMemory = new double[10];
			for(int i = 0; i <10; i++) {
				kilMemory[i] = (this.memoryUsage[i]) / this.kiloBytes;
			}
			return kilMemory;
		}
		else if(this.memoryUnits == MemoryUnits.MegaBytes) {
			double[] megMemory = new double[10];
			for(int i = 0; i < 10; i++) {
				megMemory[i] = (this.memoryUsage[i]) / this.megaBytes;
			}
			return megMemory;
		}
		return this.memoryUsage;
	}
	
	



	@Override
	public void resetTestTimes() {
		// TODO Auto-generated method stub
		for(int i = 0; i < this.testTimes.length; i++) {
			this.testTimes[i] = 0;
			this.memoryUsage[i] = 0;
		}
		this.counts = 0;
	}



	@Override
	public void addTestTime(long runTime) {
		// TODO Auto-generated method stub
		if (this.counts < this.testTimes.length) {
			this.testTimes[this.counts] = runTime;
			this.memoryUsage[this.counts] = ((Runtime.getRuntime().totalMemory()) - (Runtime.getRuntime().freeMemory()));
			this.counts++;

			}

			else {
			for (int i = 0; i < (this.testTimes.length - 1); i++) {
			this.testTimes[i] = this.testTimes[i + 1];
			this.memoryUsage[i] = this.memoryUsage[i + 1];

			}
			this.memoryUsage[this.memoryUsage.length - 1] = ((Runtime.getRuntime().totalMemory())
			- (Runtime.getRuntime().freeMemory()));
			this.testTimes[this.testTimes.length - 1] = runTime;

			}
		
	}



	@Override
	public double getAverageTestTime() {
		// TODO Auto-generated method stub
		
		int nonZero = 0;
		double sum =  0;
		for(int i = 0; i < 10; i++) {
			if(this.testTimes[i] != 0) {
				switch(this.timeUnits){
				case NanoSeconds:
					return (double) this.testTimes[counts -1];
					//break;
				case MilliSeconds:
					double time = (double)this.testTimes[counts -1] / 1_000;
					return time;
					//break;
				case MicroSeconds:
					double time1 = (double) this.testTimes[counts -1] / 1_000_000;
					return time1;
					//break;
				case Seconds:
					double time2= (double) this.testTimes[counts -1] / 1_000_000_000;
					return time2;
				}

				sum += this.testTimes[i];
				nonZero++;
			}
		}
		if(nonZero > 0) {
			return sum/nonZero;
		}
		else {
			return 0;
		}
		/*
		double count = 0;
		for (int i = 0; i < counts; i++) {
			count += testTimes[i];
		}
		double meanRunTime = (double) count / counts;
		
		if(this.timeUnits == TimeUnits.Seconds) {
			return meanRunTime/ this.seconds;
		}
		else if(this.timeUnits == TimeUnits.MilliSeconds) {
			return meanRunTime/ this.milliSeconds;
		}
		else if(this.timeUnits == TimeUnits.MicroSeconds) {
			return meanRunTime/ this.microSeconds;
		}
		else{
			return meanRunTime;
		}
		*/
	}



	@Override
	public double getAverageMemoryUsage() {
		// TODO Auto-generated method stub
		
		int nonZero = 0;
		double sum  = 0;
		
		for(int i = 0; i < 10; i++ ) {
			if(this.memoryUsage[i] != 0) {
				switch(this.memoryUnits){
				case Bytes:
					return (double) this.memoryUsage[counts -1];
					
				case KiloBytes:
					double mem = (double) this.memoryUsage[counts -1] / this.kiloBytes;
					return mem;
					
				case MegaBytes:
					double mem1 = (double) this.memoryUsage[counts -1] / this.megaBytes ;
					return mem1;
				}
				sum += this.memoryUsage[i];
				nonZero++;
			}
		}
		if(nonZero > 0) {
			return sum / nonZero;
		}
		else { 
			return 0;
		}
		/*
		double count = 0;
		double meanMemoryUsage;

		for (int i = 0; i < counts; i++) {
		count += this.memoryUsage[i];
		}
		meanMemoryUsage = count / this.counts;

		if (this.memoryUnits == MemoryUnits.KiloBytes) {
		return meanMemoryUsage / this.kiloBytes;
		}
		else if (this.memoryUnits == MemoryUnits.MegaBytes) {
		return meanMemoryUsage / this.megaBytes;
		}
		  
		return meanMemoryUsage;
		*/
	}

		

	

	
}