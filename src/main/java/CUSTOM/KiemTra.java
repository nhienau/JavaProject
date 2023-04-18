package CUSTOM;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KiemTra {
	public KiemTra() {}
	public boolean KTSo(String in)
	{
		for(int i=0;i<in.length();i++)
		{
			if(in.charAt(i)<48||in.charAt(i)>57)
			{
				return false;
			}
		}
		return true;
	}
	public boolean KTKhoangCach(String in)
	{
		for(int i=0;i<in.length();i++)
		{
			if(in.charAt(i)==' ')
			{
				return false;
			}
		}
		return true;
	}
	public boolean KTKyTuDacBiet(String in)
	{
		Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(in);
		boolean b = m.find();
		if(b)
			return true;
		else
			return false;
	}
	public boolean isValid(String dateStr) {
        DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        try {
            sdf.parse(dateStr);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}
