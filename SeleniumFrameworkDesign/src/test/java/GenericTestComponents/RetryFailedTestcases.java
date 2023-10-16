package GenericTestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryFailedTestcases implements IRetryAnalyzer{

	int count=0;
	int maxTry=2;
	
	//Implement IRetryAnalyzer interface to run only failed testcases
	@Override
	public boolean retry(ITestResult result) {
		
		if(count<maxTry)
		{
			count++;
			return true;
			
			
		}	
		return false;
	}

}
