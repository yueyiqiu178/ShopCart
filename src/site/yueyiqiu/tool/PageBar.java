package site.yueyiqiu.tool;

public class PageBar {
	
	private int allR;
	private int perR;
	private int allP;
	private int perP;
	private int allG;
	private int currentP=1;
	private int currentG=1;
	private String pageLink;
	
	public PageBar(){
		this.allR=0;
		this.perP=3;
		this.perR=12;
		this.currentG=1;
		this.currentP=1;
		this.pageLink="";
	}

	public int getAllR() {
		return allR;
	}

	

	public int getPerR() {
		return perR;
	}

	

	public int getAllP() {
		return allP;
	}

	

	public int getPerP() {
		return perP;
	}

	

	public int getAllG() {
		return allG;
	}

	

	public int getCurrentP() {
		return currentP;
	}

	

	public int getCurrentG() {
		return currentG;
	}

	

	public String getPageLink() {
		return pageLink;
	}

	

	public void setAllR(int ptr) {
		this.allR=ptr;
	}

	

	public void setPerR(int ptr) {
		this.perR=ptr;
	}

	

	public void setAllP() {
		
		this.allP=this.allR%this.perR==0?this.allR/this.perR:this.allR/this.perR+1;
		
	}

	

	public void setPerP(int ptr) {
		this.perP=ptr;
	}

	

	public void setAllG() {
		
		this.allG=this.allP%this.perP==0?this.allP/this.perP:this.allP/this.perP+1;
		
	}

	

	public void setCurrentP(String ptr) {
		try{
			System.out.println("ptr="+ptr);
			this.currentP=Integer.parseInt(ptr);
			System.out.println("currentP="+currentP);
			}
		catch(Exception e){this.currentP=-1;}
	}

	

	public void setCurrentG(String ptr) {
		try{
			System.out.println("ptr="+ptr);
			this.currentG=Integer.parseInt(ptr);
			System.out.println("currentG="+currentG);
			}
		catch(Exception e){this.currentG=-1;}
	}

	

	public void setPageLink(String gowhich) {
		
	}
	
	
	private void setCurrent(){
		
		if(this.currentG==-1&&this.currentP==-1){
			this.currentG=1;
			this.currentP=1;
		}
		else if(this.currentG!=-1&&this.currentP!=-1){
			this.currentG=1;
			this.currentP=1;
		}
		else if(this.currentP!=-1){
			if(this.currentP>this.allP)
				this.currentP=this.allP;
			this.currentG=this.currentP/this.perP+1;
		}
		else if(this.currentG!=-1){
			if(this.currentG>this.allG)
				this.currentG=this.allG;
			this.currentP=this.currentG-1*this.perP+1;	
		}
	}
	
	
	public void setPageBar(String p,String g,String gowhich){
		System.out.println("inn p="+p);
		System.out.println("inn g="+g);
		this.setAllP();
		this.setAllG();
		this.setCurrentG(g);
		this.setCurrentP(p);
		System.out.println("tt"+this.currentG);
		System.out.println("tt"+this.currentP);
		this.setCurrent();
		this.setPageLink(gowhich);
		
	}
	

}
