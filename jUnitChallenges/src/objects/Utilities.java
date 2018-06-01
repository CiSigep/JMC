package objects;

public class Utilities {
	
	// Returns a char array containing every nth char. When
	// srcArray.length < n, returns srcArray.
	public char[] everyNthChar(char[] srcArray, int n) {
		if(srcArray == null || srcArray.length < n)
			return srcArray;
		
		int returnLength = srcArray.length / n;
		char[] result = new char[returnLength];
		
		for(int i = 1; i <= result.length; i++)
			result[i - 1] = srcArray[(n * i) - 1];
		
		return result;
	}

	// Removes pairs of the same char that are next to each other.
	public String removePairs(String srcString) {
		if(srcString == null || srcString.length() < 2)
			return srcString;
		
		
		StringBuilder sb = new StringBuilder();
		char[] srcChar = srcString.toCharArray();
		
		for(int i = 0; i < srcChar.length - 1; i++) {
			if(srcChar[i] != srcChar[i + 1]) {
				sb.append(srcChar[i]);
			}
			
		}
		
		sb.append(srcChar[srcChar.length - 1]);
		
		return sb.toString();
	}
	
	// Perform some conversion
	public int converter(int a, int b) {
		return (a / b) + (a * 30) - 2;
	}
	
	public String nullIfOddLength(String src) {
		if(src.length() % 2 == 1)
			return null;
		
		return src;
	}
}
