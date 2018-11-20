package CS576.utils;

public class LinkInfoVO {
    private String linkName;
    private String originPathName;
    private int frame;
    private int boundaryX;
    private int boundaryY;
    private int boundaryWidth;
    private int boundaryHeight;
    private String linkPathName;
    private int linkFrameFrom;
    private int linkFrameTo;

    public String getLinkName() {
		return linkName;
	}
    
	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}

    public String getOriginPathName() {
		return originPathName;
	}
    
	public void setOriginPathName(String originPathName) {
		this.originPathName = originPathName;
	}
	
	public int getFrame() {
		return frame;
	}
	
	public void setFrame(int frame) {
		this.frame = frame;
	}
	
	public int getBoundaryX() {
		return boundaryX;
	}
	
	public void setBoundaryX(int boundaryX) {
		this.boundaryX = boundaryX;
	}
	
	public int getBoundaryY() {
		return boundaryY;
	}
	
	public void setBoundaryY(int boundaryY) {
		this.boundaryY = boundaryY;
	}
	
	public int getBoundaryWidth() {
		return boundaryWidth;
	}
	
	public void setBoundaryWidth(int boundaryWidth) {
		this.boundaryWidth = boundaryWidth;
	}
	
	public int getBoundaryHeight() {
		return boundaryHeight;
	}
	
	public void setBoundaryHeight(int boundaryHeight) {
		this.boundaryHeight = boundaryHeight;
	}
	
	public String getLinkPathName() {
		return linkPathName;
	}
	
	public void setLinkPathName(String linkPathName) {
		this.linkPathName = linkPathName;
	}
	
	public int getLinkFrameFrom() {
		return linkFrameFrom;
	}
	
	public void setLinkFrameFrom(int linkFrameFrom) {
		this.linkFrameFrom = linkFrameFrom;
	}
	
	public int getLinkFrameTo() {
		return linkFrameTo;
	}
	
	public void setLinkFrameTo(int linkFrameTo) {
		this.linkFrameTo = linkFrameTo;
	}
}