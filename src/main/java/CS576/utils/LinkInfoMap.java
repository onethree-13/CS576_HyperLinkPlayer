package CS576.utils;

import org.json.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class LinkInfoMap {

	private HashMap<Integer, LinkInfo> hmap = new HashMap<Integer, LinkInfo>();

	public static JSONObject parseJSONFile(String filename) {
		try {
			String content = new String(Files.readAllBytes(Paths.get(filename)));
			return new JSONObject(content);
		} catch (Exception e) {
			System.out.println("Error: cannot parse json.");
			return null;
		}
	}

	public void print() {
		for (LinkInfo link : hmap.values()) {
			System.out.println(
					"object: " + link.object + ", oriPathName" + link.oriPathName + ", oriFrameNum" + link.oriFrameNum);
		}
	}

	public void json2LinkInfo(String filename) {
		JSONObject obj = parseJSONFile(filename);
		if (obj == null)
			return;
		hmap.clear();
		JSONArray links = obj.getJSONArray("links");
		for (int i = 0; i < links.length(); i++) {
			LinkInfo linkInfo = new LinkInfo();
			JSONObject link = links.getJSONObject(i);
			linkInfo.object = link.getString("object");
			linkInfo.oriPathName = link.getString("oriPathName");
			linkInfo.oriFrameNum = link.getInt("oriFrameNum");
			linkInfo.destPathName = link.getString("destPathName");
			linkInfo.destFrameNum = link.getInt("destFrameNum");
			linkInfo.boundaryX = link.getInt("boundaryX");
			linkInfo.boundaryY = link.getInt("boundaryY");
			linkInfo.boundaryWidth = link.getInt("boundaryWidth");
			linkInfo.boundaryHeight = link.getInt("boundaryHeight");
			hmap.put(linkInfo.oriFrameNum, linkInfo);
		}
	}

	public LinkInfoMap(String filename) {
		json2LinkInfo(filename);
	}

	public LinkInfoMap() {
	}

	public void addLink(LinkInfo link) {
		hmap.put(link.oriFrameNum, link);
	}
}
