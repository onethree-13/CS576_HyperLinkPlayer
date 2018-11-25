package CS576.utils;

import org.json.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class LinkInfoMap {

	private HashMap<Integer, List<LinkInfo>> hmap = new HashMap<Integer, List<LinkInfo>>();

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
		JSONObject links = new JSONObject(hmap);
		System.out.println(links);
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
		JSONObject obj = parseJSONFile(filename);
		if (obj == null)
			return;
		hmap.clear();
		Iterator<String> keysItr = obj.keys();
		while (keysItr.hasNext()) {
			String key = keysItr.next();
			JSONArray links = obj.getJSONArray(key);
			for (int i = 0; i < links.length(); i++) {
				LinkInfo linkInfo = new LinkInfo();
				JSONObject link = links.getJSONObject(i);
				linkInfo.setObject(link.getString("object"));
				linkInfo.setOriPathName(link.getString("oriPathName"));
				linkInfo.setOriFrameNum(link.getInt("oriFrameNum"));
				linkInfo.setDestPathName(link.getString("destPathName"));
				linkInfo.setDestFrameNum(link.getInt("destFrameNum"));
				linkInfo.setBoundaryX(link.getInt("boundaryX"));
				linkInfo.setBoundaryY(link.getInt("boundaryY"));
				linkInfo.setBoundaryWidth(link.getInt("boundaryWidth"));
				linkInfo.setBoundaryHeight(link.getInt("boundaryHeight"));
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
	}

	public LinkInfoMap(String filename) {
		Json2LinkInfoMap(filename);
	}

	public LinkInfoMap() {
	}

	public void addLink(LinkInfo linkInfo) {
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
