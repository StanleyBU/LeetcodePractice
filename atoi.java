class Solution {
    public int myAtoi(String str) {
        
        if(str == null || str.equals("") || str.equals("-") || str.equals("+"))
            return 0; 
        
		// first, skip the white spaces
        int startIndex = 0; 
		for(int i = 0; i < str.length(); i++)
			if(str.charAt(i) - ' ' != 0)
			{
				startIndex = i; 
				break; 
			}
		
		// check whether the first non-whitespace character is valid
		char firstChar = str.charAt(startIndex);  
        String initialString = str.substring(startIndex); 
        // if the first char is not + or - or between '0' and '9', return 0
		if(firstChar - '-' != 0 && firstChar - '+' != 0 && (firstChar < '0' || firstChar > '9'))
			return 0; 
        if((firstChar - '-' == 0 || firstChar - '+' == 0) && initialString.length() > 1)
            if(initialString.charAt(1) < '0' || initialString.charAt(1) > '9')
                return 0; 
		
		// obtain the target string 
		int charIndex = startIndex + 1; 
		while(charIndex < str.length() && (str.charAt(charIndex) >= '0' && str.charAt(charIndex) <= '9'))
			charIndex++; 
		String targetString = str.substring(startIndex, charIndex); 
		
		
		// convert the string to integer, and test whether the converted integer will be out of bounds
		int sign = 1; 
		String finalString = ""; 
		if(targetString.charAt(0) - '-' == 0 || targetString.charAt(0) - '+' == 0)
		{
			finalString = targetString.substring(1); 
			if(targetString.charAt(0) - '-' == 0)
				sign = -1; 
		}
		else finalString = targetString; 
		System.out.println("finalString = " + finalString); 

		String temp = ""; 
		for(int i = 0; i < finalString.length(); i++)
		{
			if(!temp.equals(""))
			{
				if(sign > 0)
				{
					if(Integer.valueOf(temp) > (Integer.MAX_VALUE / 10) || 
							(Integer.valueOf(temp) == (Integer.MAX_VALUE / 10) && finalString.charAt(i) > '7')) 
						return Integer.MAX_VALUE; 
				}
				if(sign < 0) {
					if(Integer.valueOf(temp) > (Integer.MAX_VALUE / 10) ||
							(Integer.valueOf(temp) == (Integer.MAX_VALUE / 10) && finalString.charAt(i) > '7')) 
											return Integer.MIN_VALUE; 
				}
			}
			
            temp = finalString.substring(0, i+1); 
		}
		
		return sign * Integer.valueOf(temp); 
    }
}