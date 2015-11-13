import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class KeywordsMatcher {

	private List<String> keywords;
	public ArrayList<SearchResult> searchResultList = new ArrayList<SearchResult>();
	private String[] stopWords = {"the","a","an","of"};
	private double tf;
	private int times;	
		
	private List<String> buildStopList()
	{
		List<String> stopList = Arrays.asList(stopWords);
		return stopList;
	}
		
	private List<String> fetchKeywords(String text)
	{
		//if(text!=null){
			keywords = Arrays.asList(text.split("\\s"));
		//}
		return keywords;
	}
		
	public void calTF(ArrayList<ProfessorInfo> professorList)
	{
		for(int i=0;i<professorList.size();i++)
		{
			ProfessorInfo professor = professorList.get(i);
			String str = professor.getName()+professor.getEducationBackground()+professor.getResearchInterests()
					+professor.getEmail()+professor.getPhone();
			//System.out.print(str);
			List<String> list = Arrays.asList(str.split("\\s"));
			//for(int j=0;j<list.size();j++){
				//System.out.println(list.get(j));
			//}
			int total = list.size();
			//System.out.println(total);
			List<String> stopList = buildStopList();
			keywords = fetchKeywords(new MainWindow().getKeywords());
			for(int j=0;j<list.size();j++){
				for(int k=0;k<stopList.size();k++)
				{
					if(list.get(j).equals(stopList.get(k)))
					{
						total--;
					}
				}
			}
			for(int m=0;m<list.size();m++)
			{
				for(int n=0;n<keywords.size();n++)
				{
					if(list.get(m).equals(keywords.get(n)))
					{
						times++;
					}
				}
			}
			//System.out.println(times);
			if(times!=0)
			{
				//System.out.println(times);
				//System.out.println(total);
				tf = (double)times/total;
				//System.out.println(tf);
				SearchResult result = new SearchResult(professor,tf);
				result.setPi(professor);
				result.setTf(tf);
				searchResultList.add(result);
				//System.out.println(tf);
			}
		}
	}
	
	public ArrayList<SearchResult> sort()
	{
		ResultComparator comp = new ResultComparator();
		SearchResult[] result = new SearchResult[searchResultList.size()];
		for(int i=0;i<searchResultList.size();i++)
		{
			result[i]=searchResultList.get(i);
		}
		Arrays.sort(result,comp);
		return searchResultList;
	}
	
}
