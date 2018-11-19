package CS576.utils;

import java.awt.image.BufferedImage;
import org.bytedeco.javacv.Java2DFrameUtils;
import static org.bytedeco.javacpp.opencv_core.Mat;

public class FrameController {

	private static final int DEFAULT_FRAME_WIDTH = 352;
	private static final int DEFAULT_FRAME_HEIGHT = 288;
	
	private String pathName;
    private RgbReader reader;

	public FrameController() {
		this.pathName = null;
        reader = null;
	}

	public FrameController(String pathName) throws Exception {
		setPathName(pathName);
	}

	public void setPathName(String pathName) throws Exception{
		this.pathName = pathName;
        this.reader = new RgbReader(pathName, DEFAULT_FRAME_WIDTH, DEFAULT_FRAME_HEIGHT);
	}

	public String getPathName() {
		return this.pathName;
    }
    
    public int getTotalFrameCnt() {
        return this.reader.getTotalFrameCnt();
    }

	public int getCurFrameNum() {
        return this.reader.getCurFrameNum();
    }

    public Mat getFrameMat(int nbFrame) throws Exception {
        return reader.getFrame(nbFrame);
    }

    public BufferedImage getFrameImage(int nbFrame) throws Exception {
        Mat mat = getFrameMat(nbFrame);
        return Java2DFrameUtils.toBufferedImage(mat);
    }
}
