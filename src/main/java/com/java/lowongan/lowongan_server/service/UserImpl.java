package com.java.lowongan.lowongan_server.service;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.java.lowongan.lowongan_server.model.LoginRequest;
import com.java.lowongan.lowongan_server.model.User;
import com.java.lowongan.lowongan_server.repository.UserRepository;
import com.java.lowongan.lowongan_server.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.google.cloud.storage.StorageOptions;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserImpl implements UserService{
    private static final String DOWNLOAD_URL = "https://firebasestorage.googleapis.com/v0/b/lowongan-a0c4a.appspot.com/o/%s?alt=media";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    AuthenticationManager authenticationManager;
    public String uploadFile(File fileId, String fileName) throws IOException {
        // Create a temporary file with the specified filename
        File file = File.createTempFile("user-image-", fileName);

        // Write the file contents to the temporary file
        // **Replace "your_file_contents" with the actual file contents**
        byte[] fileContents = "your_file_contents".getBytes();
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(fileContents);
        }

        // Upload the temporary file to Firebase Storage
        String downloadURL = uploadFileToFirebaseStorage((MultipartFile) file, fileName);

        // Delete the temporary file
        file.delete();

        return downloadURL;
    }
    public String uploadImage(MultipartFile multipartFile) throws IOException {
        try {
            String fileName = getExtentions(multipartFile.getOriginalFilename());
            String downloadURL = uploadFileToFirebaseStorage(multipartFile, fileName);
            return downloadURL;
        } catch (Exception e) {
            e.getStackTrace();
            throw new RuntimeException("error");
        }
    }

    private String uploadFileToFirebaseStorage(MultipartFile multipartFile, String fileName) throws IOException {
        // Create a BlobId object with the image data and metadata
        BlobId blobId = BlobId.of("lowongan-a0c4a.appspot.com", fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                .setContentType("media")
                .build();

        // Get the credentials for accessing Firebase Storage
        Credentials credentials = GoogleCredentials.fromStream(new FileInputStream("./src/main/resources/ServiceAccount.json"));

        // Get the Storage service
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();

        // Upload the image data directly from MultipartFile
        storage.create(blobInfo, multipartFile.getBytes());

        // Generate the download URL for the image
        return String.format(DOWNLOAD_URL, URLEncoder.encode(fileName, StandardCharsets.UTF_8));
    }

    public String getExtentions(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }
    public String imageConverter(MultipartFile multipartFile) {
        try {
            String fileName = getExtentions(multipartFile.getOriginalFilename());
            File file = convertToFile(multipartFile, fileName);
            var RESPONSE_URL = uploadFile(file, fileName);
            file.delete();
            return RESPONSE_URL;
        } catch (Exception e) {
            e.getStackTrace();
            throw new RuntimeException("error  ");
        }
    }

    public File convertToFile(MultipartFile multipartFile, String fileName) throws IOException {
        File file = new File(fileName);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(multipartFile.getBytes());
            fos.close();
        }
        return file;
    }


    @Override
    public Map<Object, Object> login(LoginRequest loginRequest) {

        User user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(() -> new com.java.lowongan.lowongan_server.exception.NotFoundException("Username not found"));
        if (encoder.matches(loginRequest.getPassword(), user.getPassword())) {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateToken(authentication);
            user.setLast_login(new Date());
            userRepository.save(user);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedLastLogin = sdf.format(user.getLast_login());
            Map<Object, Object> response = new HashMap<>();
            response.put("data", user);
            response.put("id", user.getId());
            response.put("token", jwt);
            response.put("last_login", formattedLastLogin);
            response.put("type_token", "User");

            return response;
        }
        throw new  com.java.lowongan.lowongan_server.exception.NotFoundException("Password not found");
    }

    @Override
    public User addUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new com.java.lowongan.lowongan_server.exception.NotFoundException("Email sudah digunakan");
        }

        // Validate age
        int usia = Integer.parseInt(user.getUsia()); // Assuming usia is a string representation of an integer
        if (usia < 18) {
            throw new IllegalStateException("Usia belum mencapai 18 tahun, tidak dapat melakukan registrasi");
        }

        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole("user");
        return userRepository.save(user);
    }

    @Override
    public User addAdmin(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new  com.java.lowongan.lowongan_server.exception.NotFoundException("Email sudah digunakan");
        }

        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole("admin");
        return userRepository.save(user);
    }

    @Override
    public User get(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new   com.java.lowongan.lowongan_server.exception.NotFoundException("Id Not Found"));
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User edit(Long id, User user) {
        User update = userRepository.findById(id).orElseThrow(() -> new  com.java.lowongan.lowongan_server.exception.NotFoundException("Id Not Found"));
        update.setPassword(user.getPassword());
        update.setUsername(user.getUsername());
        return userRepository.save(user);
    }

    @Override
    public Map<String, Boolean> delete(Long id) {
        try {
            userRepository.deleteById(id);
            Map<String, Boolean> res = new HashMap<>();
            res.put("Deleted", Boolean.TRUE);
            return res;
        } catch (Exception e) {
            return null;
        }
    }
}