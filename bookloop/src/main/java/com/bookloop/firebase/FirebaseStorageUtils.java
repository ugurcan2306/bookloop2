package com.bookloop.firebase;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

import java.io.FileInputStream;
import java.io.IOException;

public class FirebaseStorageUtils {

    private static final String BUCKET_NAME = "bookloop-5d51a.appspot.com"; // Replace with your Firebase Storage bucket name

    public static String uploadImageToFirebaseStorage(String filePath, String uploadFileName) throws IOException {
        // Initialize Firebase Storage
        Storage storage = StorageOptions.newBuilder()
                .setCredentials(GoogleCredentials.fromStream(new FileInputStream("bookloop/src/main/resources/bookloop-5d51a-firebase-adminsdk-ptztt-59a495ea0b.json")))
                .build()
                .getService();

        // Get the bucket
        Bucket bucket = storage.get(BUCKET_NAME);

        // Upload the file
        Blob blob = bucket.create(uploadFileName, new FileInputStream(filePath), "image/jpeg");
        return blob.getMediaLink(); // Return the URL of the uploaded image
    }
}
