package CS576.utils;

import org.json.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class LinkInfoMap {

	private HashMap<Integer, List<LinkInfo>> hmap = new HashMap<Integer, List<LinkInfo>>();

	public void print() {
		for (List<LinkInfo> list : hmap.values()) {
			for (LinkInfo linkInfo : list) {
				System.out.println(linkInfo);
			}
		}
	}

	public void openFile(String filename) {
		Json2LinkInfoMap(filename);
	}

	public void LinkInfoMap2Json(String filename) {
		JSONObject links = new JSONObject(hmap);
		try (PrintWriter out = new PrintWriter(filename)) {
			out.println(links.toString());
		} catch (Exception e) {
			System.out.println("Error: cannot write to json.");
		}
	}

	public void Json2LinkInfoMap(String filename) {
		JSONObject obj = null;
		hmap.clear();
		try {

			String content = new String(Files.readAllBytes(Paths.get(filename)));
			obj = new JSONObject(content);
		} catch (Exception e) {
			System.out.println("No hyperlink file.");
			return;
		}

		try {
			JSONArray links = obj.getJSONArray("links");
			for (int i = 0; i < links.length(); i++) {
				JSONObject link = links.getJSONObject(i);
				String object = link.getString("linkName");
				String oriPathName = link.getString("oriPathName");
				String destPathName = link.getString("destPathName");
				int destFrameNum = link.getInt("destFrameNum");
				JSONArray oriFrames = link.getJSONArray("oriFrames");
				for (int j = 0; j < oriFrames.length(); j++) {
					LinkInfo linkInfo = new LinkInfo();
					JSONObject oriFrame = oriFrames.getJSONObject(j);
					linkInfo.setObject(object);
					linkInfo.setOriPathName(oriPathName);
					linkInfo.setOriFrameNum(oriFrame.getInt("frameNum"));
					linkInfo.setDestPathName(destPathName);
					linkInfo.setDestFrameNum(destFrameNum);
					linkInfo.setBoundaryX(oriFrame.getInt("x"));
					linkInfo.setBoundaryY(oriFrame.getInt("y"));
					linkInfo.setBoundaryWidth(oriFrame.getInt("width"));
					linkInfo.setBoundaryHeight(oriFrame.getInt("height"));
					if (hmap.containsKey(linkInfo.getOriFrameNum()))
						hmap.get(linkInfo.getOriFrameNum()).add(linkInfo);
					else
						hmap.put(linkInfo.getOriFrameNum(), new ArrayList<LinkInfo>() {
							{
								add(linkInfo);
							}
						});
				}
			}
		} catch (Exception e) {
			System.out.println("Failed to format hyperlink file.");
			System.out.println(e.fillInStackTrace());
		}
	}

	public LinkInfoMap(String filename) {
		Json2LinkInfoMap(filename);
	}

	public LinkInfoMap() {
	}

	public void addLinkInfo(LinkInfo linkInfo) {
		if (hmap.containsKey(linkInfo.getOriFrameNum()))
			hmap.get(linkInfo.getOriFrameNum()).add(linkInfo);
		else
			hmap.put(linkInfo.getOriFrameNum(), new ArrayList<LinkInfo>() {
				{
					add(linkInfo);
				}
			});
	}

	public List<LinkInfo> getLinkInfo(int frame) {
		if (hmap.containsKey(frame))
			return hmap.get(frame);
		else
			return new ArrayList<LinkInfo>();
	}
}
