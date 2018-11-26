package CS576.utils;

public class LinkInfo {
	private String object;
	private String oriPathName;
	private int oriFrameNum;
	private String destPathName;
	private int destFrameNum;
	private int boundaryX;
	private int boundaryY;
	private int boundaryWidth;
	private int boundaryHeight;

	public LinkInfo() {
		object = new String();
		destPathName = new String();
	}

	@Override
	public String toString() {
		return "object: " + object + ", oriFrameNum: " + oriFrameNum + "\ndestPathName: " + destPathName
				+ ", destFrameNum: " + destFrameNum + ", boundaryX: " + boundaryX + ", boundaryY: " + boundaryY
				+ ", boundaryWidth: " + boundaryWidth + ", boundaryHeight: " + boundaryHeight;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public void setOriPathName(String oriPathName) {
		this.oriPathName = oriPathName;
	}

	public void setOriFrameNum(int oriFrameNum) {
		this.oriFrameNum = oriFrameNum;
	}

	public void setDestPathName(String destPathName) {
		this.destPathName = destPathName;
	}

	public void setDestFrameNum(int destFrameNum) {
		this.destFrameNum = destFrameNum;
	}

	public void setBoundaryX(int boundaryX) {
		this.boundaryX = boundaryX;
	}

	public void setBoundaryY(int boundaryY) {
		this.boundaryY = boundaryY;
	}

	public void setBoundaryWidth(int boundaryWidth) {
		this.boundaryWidth = boundaryWidth;
	}

	public void setBoundaryHeight(int boundaryHeight) {
		this.boundaryHeight = boundaryHeight;
	}

	public String getObject() {
		return object;
	}

	public String getOriPathName() {
		return oriPathName;
	}

	public int getOriFrameNum() {
		return oriFrameNum;
	}

	public String getDestPathName() {
		return destPathName;
	}

	public int getDestFrameNum() {
		return destFrameNum;
	}

	public int getBoundaryX() {
		return boundaryX;
	}

	public int getBoundaryY() {
		return boundaryY;
	}

	public int getBoundaryWidth() {
		return boundaryWidth;
	}

	public int getBoundaryHeight() {
		return boundaryHeight;
	}
}