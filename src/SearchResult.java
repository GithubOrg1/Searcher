
public class SearchResult {
	
	private ProfessorInfo pi;
	private double tf;
	
	/*public SearchResult(ProfessorInfo pi)
	{
		
	}*/
	
	public SearchResult(ProfessorInfo pi,double tf)
	{
		this.pi = pi;
		this.tf = tf;
	}

	public double getTf() {
		return tf;
	}

	public void setTf(double tf) {
		this.tf = tf;
	}

	public ProfessorInfo getPi() {
		return pi;
	}

	public void setPi(ProfessorInfo pi) {
		this.pi = pi;
	}
}
