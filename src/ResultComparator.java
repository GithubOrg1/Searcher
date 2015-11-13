import java.util.Comparator;


public class ResultComparator implements Comparator <SearchResult>{
	
	@Override
	public int compare(SearchResult sr1,SearchResult sr2) {
		// TODO Auto-generated method stub
		if (sr1.getTf() > sr2.getTf()) 
        { 
            return 1; 
        } 
        else if (sr1.getTf() < sr2.getTf()) 
        { 
            return -1; 
        } 
        else 
        { 
            return 0; 
        }
	}
}
