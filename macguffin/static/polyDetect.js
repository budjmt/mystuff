	function pointInPoly(polyCords, pointX, pointY)
	{
		
		var i, j, c = false;
		
		for (i = 0, j = polyCords.length - 1; i < polyCords.length; j = i++)
		{
			
			if (((polyCords[i][1] > pointY) != (polyCords[j][1] > pointY))
			&& (pointX < (polyCords[j][0] - polyCords[i][0]) *
			(pointY - polyCords[i][1]) / (polyCords[j][1] - polyCords[i][1]) + polyCords[i][0]))
			{
				c = !c;
			}
			
		}
		
		return c;
		
	}
	
	var poly = [];
	var polyPoint = 0;
	
	poly[polyPoint] = [];
	poly[polyPoint][0] = 27;
	poly[polyPoint][1] = 24;
	polyPoint++;
	
	poly[polyPoint] = [];
	poly[polyPoint][0] = 119;
	poly[polyPoint][1] = 41;
	polyPoint++;
	
	poly[polyPoint] = [];
	poly[polyPoint][0] = 131;
	poly[polyPoint][1] = 134;
	polyPoint++;
	
	poly[polyPoint] = [];
	poly[polyPoint][0] = 46;
	poly[polyPoint][1] = 174;
	polyPoint++;
	
	poly[polyPoint] = [];
	poly[polyPoint][0] = 74;
	poly[polyPoint][1] = 101;
	polyPoint++;
	


