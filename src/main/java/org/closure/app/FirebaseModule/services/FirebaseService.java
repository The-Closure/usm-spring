package org.closure.app.FirebaseModule.services;

import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

import org.closure.app.FirebaseModule.models.FirebaseRequest;
import org.closure.app.FirebaseModule.repositories.FirebaseRepo;
import org.closure.app.UserModule.exceptions.UserErrorException;
import org.closure.app.UserModule.repositories.UserRepo;
import org.closure.app.entities.FirebaseEntity;
import org.closure.app.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

@Service
public class FirebaseService {

    @Autowired
    FirebaseRepo firebaseRepo;

    @Autowired
    ResourceLoader resourceLoader;

    @Autowired
    private FirebaseMessaging firebaseMessaging;

    @Autowired
    UserRepo userRepo;

    @Bean
    public FirebaseMessaging init() throws IOException {
        InputStream serviceAccount = resourceLoader.getResource("classpath:googlecred.json").getInputStream();

        FirebaseOptions.Builder builder = FirebaseOptions.builder();
        GoogleCredentials googleCredentials = GoogleCredentials.fromStream(serviceAccount);
        FirebaseOptions options = builder.setCredentials(googleCredentials).build();
        FirebaseApp app = FirebaseApp.initializeApp(options);
        return FirebaseMessaging.getInstance(app);
    }

    public List<String> postNotification(UserEntity publisher, Optional<String> img,Long postID) throws FirebaseMessagingException {
        List<String> tokens = publisher.getCommuninty().getUsers().stream().filter((u) -> u.getToken() != null)
                .map((u) -> u.getToken().getFirebaseToken()).toList();
        tokens = new ArrayList<String>(tokens);
        if (publisher.getToken() != null)
            tokens.remove(publisher.getToken().getFirebaseToken());
        List<String> recievers = new ArrayList<String>();
        Notification notification = Notification.builder().build();
        for (String token : tokens) {
            if (img.isPresent())
                notification = Notification.builder().setTitle("new post")
                        .setBody(publisher.getName() + " added a post to your community").setImage(img.get()).build();
            else
                notification = Notification.builder().setTitle("new post")
                        .setBody(publisher + " added a post to your community").build();
            Map<String,String> postDetails = new HashMap<String,String>();
            postDetails.put("postID", postID.toString());
            Message packet = Message.builder().setToken(token).setNotification(notification).putAllData(postDetails).build();
            
            recievers.add(firebaseMessaging.send(packet));
        }
        return recievers;
    }

    public String addTokenToUser(FirebaseRequest firebaseRequest) {
        UserEntity userEntity = new UserEntity();
        if (firebaseRepo.findByOwner(userEntity = userRepo.findById(firebaseRequest.getUserID())
                .orElseThrow(() -> new UserErrorException("no user with this id"))).isEmpty())
            firebaseRepo.save(new FirebaseEntity().withCreated_at(Instant.now())
                    .withFirebaseToken(firebaseRequest.getToken()).withOwner(userEntity));
        else
            firebaseRepo.save(firebaseRepo
                    .findByOwner(userEntity = userRepo.findById(firebaseRequest.getUserID())
                            .orElseThrow(() -> new UserErrorException("no user with this id")))
                    .get().withFirebaseToken(firebaseRequest.getToken()));
        return firebaseRequest.getToken();
    }
}
