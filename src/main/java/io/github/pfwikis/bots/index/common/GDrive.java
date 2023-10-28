package io.github.pfwikis.bots.index.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.ByteArrayContent;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;

@SuppressWarnings("deprecation")
public enum GDrive {

	INSTANCE;

	private Drive service;
	
	private GDrive() {
		try {
			var credentials = getCredentials();
			service = new Drive.Builder(
				GoogleNetHttpTransport.newTrustedTransport(),
				GsonFactory.getDefaultInstance(),
				req->{
					credentials.initialize(req);
					req.setReadTimeout((int) TimeUnit.MINUTES.toMillis(5));
				}
			)
			.setApplicationName("Paizo Wikis Bots")
			.build();
		} catch(Exception e) {
			throw new Error("Failed GDrive init", e);
		}
	}
	
	public synchronized List<File> listFiles(String query) throws IOException {
		List<File> files = new ArrayList<File>();
		String pageToken = null;
		do {
			FileList result = service.files().list()
					.setQ(query+" and not trashed")
					.setSpaces("drive")
					.setFields("nextPageToken,files(id, name, modifiedTime)")
					.setPageToken(pageToken)
					.execute();

			files.addAll(result.getFiles());
			pageToken = result.getNextPageToken();
		} while (pageToken != null);

		return files;
	}
	
	public synchronized byte[] downloadFile(String id) throws IOException {
		try (var baos = new ByteArrayOutputStream()) {
			service.files()
					.get(id)
					.executeMediaAndDownloadTo(baos);
			return baos.toByteArray();
		}
	}
	
	public synchronized void updateFile(String id, String fileName, String mimeType, byte[] bytes) throws IOException {
		File f = new File();
		f.setName(fileName);
		service.files().update(id, f, new ByteArrayContent(mimeType, bytes))
			.execute();
	}
	
	public synchronized void createFile(String fileName, String mimeType, byte[] bytes) throws IOException {
		File f = new File();
		f.setName(fileName);
		f.setParents(List.of("1Xr46iDgzbquIeQxVVV3OsnSPBHywxAWC"));
		service.files().create(f, new ByteArrayContent(mimeType, bytes))
			.execute();
	}
	
	public synchronized void deleteFile(String id) throws IOException {
		File f = new File();
		f.setTrashed(true);
		service.files().update(id, f)
			.execute();
	}

	private Credential getCredentials() throws IOException {
	    var secret = Files.readAllBytes(Path.of("service-account.json"));
	    var credential = GoogleCredential.fromStream(new ByteArrayInputStream(secret))
	    		.createScoped(List.of(
    				DriveScopes.DRIVE
				));
	    return credential;

	}
}
