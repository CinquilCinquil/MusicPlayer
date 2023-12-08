package control;

import service.ContentService;

public class ContentController {
	
	private ContentService service;
	
	public ContentController() {
		service = new ContentService();
	}
	
	public void addPlaylist(int userId) {
		service.addPlaylist(userId);
	}

}
