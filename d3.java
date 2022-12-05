import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

class Main
{
	public static void main(String args[])
	{
		String[] data = getData(args[0]);
		int total = 0;
		for(String s : data)
			if(s != null)
				total += getPriority(getShared(s));

		System.out.println(total);
	
		String[][]Teams = getTeams(data);
		
		int badgeTotal = 0;

		for(int i = 0; i < Teams.length; i++)
		{
			badgeTotal += getPriority(findCommon(Teams[i]));
		}

		System.out.println(badgeTotal);
	}

	static char findCommon(String[] team)
	{
		int minInd = 0;
		if(team[1].length() < team[minInd].length())
			minInd = 1;
		if(team[2].length() < team[minInd].length())
			minInd = 2;

		String S = team[minInd];

		int indBefore =(minInd + 2) % 3; 
		int indAfter  =(minInd + 1) % 3; 

		char badge = 0;

		for(int i = 0; i < S.length(); i++)
		{
			char t = S.charAt(i);
			if(team[indBefore].indexOf(t) != -1 && team[indAfter].indexOf(t) != -1)
				badge = t;
		}

		System.out.println(team[0] +  " " + team[1] + " " + team[2] + "\t" + badge);
		return badge;
	}

	static String[][] getTeams(String[] sacks)
	{
		ArrayList<String[]> teamList = new ArrayList<String[]>();

		for(int i = 2; i < sacks.length; i++)
		{
			if((i + 1) % 3 == 0)
			{
				String[] x = {sacks[i - 2], sacks[i - 1], sacks[i]};
				teamList.add(x);
			}
		}

		String[][] ret = new String[teamList.size()][3];
		teamList.toArray(ret);
		return ret;
	}

	static String[] getData(String path)
	{
		ArrayList<String> ret = new ArrayList<String>();
		try
		{
			BufferedReader reader = new BufferedReader(new FileReader(path));
			String line = reader.readLine();
			ret.add(line);
			while(line != null)
			{
				line = reader.readLine();
				ret.add(line);
			}
			reader.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

		String[] x = new String[ret.size()];

		for(int i = 0; i < ret.size(); i++)
			x[i] = ret.get(i);

		return x;
	}

	static int getPriority(char x)
	{
		int ret = (int) x;
		if(ret < 97)
		{
			ret -= 65;
			ret += 26;
		}
		else
			ret -= 97;

		ret++;
		return ret;
	}

	//Examining the sack!!
	static char getShared(String sack)
	{
		int midPoint = sack.length()/2;
		String[] compartments = {sack.substring(0,midPoint), sack.substring(midPoint)};
		char shared = 0;
		for(int i = 0; i < compartments[1].length(); i++)
		{
			char x = compartments[1].charAt(i);
			if(compartments[0].indexOf(x) != -1)
				shared = x;
		}
		int ret = (int)shared;
		
		System.out.println(shared + ":" + ret);
		return shared;
	}


}
