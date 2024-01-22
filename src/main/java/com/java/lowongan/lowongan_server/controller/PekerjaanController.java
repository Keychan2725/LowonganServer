package com.java.lowongan.lowongan_server.controller;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.java.lowongan.lowongan_server.exception.NotFoundException;
import com.java.lowongan.lowongan_server.model.Pekerjaan;
import com.java.lowongan.lowongan_server.model.User;
import com.java.lowongan.lowongan_server.repository.PekerjaanRepository;
import com.java.lowongan.lowongan_server.service.PekerjaanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class PekerjaanController {

    private static final String DOWNLOAD_URL = "https://firebasestorage.googleapis.com/v0/b/lowongan-a0c4a.appspot.com/o/%s?alt=media";


    @Autowired
    PekerjaanRepository pekerjaanRepository;

    private PekerjaanService pekerjaanService;

    public PekerjaanController(PekerjaanService pekerjaanService) {
        this.pekerjaanService = pekerjaanService;
    }

    @GetMapping("/pekerjaan/user/{userId}")
    public ResponseEntity<List<Pekerjaan>> findAllByUserId(@PathVariable Long userId) {
        List<Pekerjaan> pekerjaanList = pekerjaanService.findAllByUserId(userId);

        if (pekerjaanList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(pekerjaanList, HttpStatus.OK);
    }
    @GetMapping("/pekerjaan/all")
    public ResponseEntity<List<Pekerjaan>> findAll() {
        List<Pekerjaan> pekerjaan = pekerjaanService.findAll();
        return new ResponseEntity<>(pekerjaan, HttpStatus.OK);
    }
    @GetMapping("/pekerjaan/{id}")
    public ResponseEntity<Pekerjaan> findById(@PathVariable Long id) {
        Pekerjaan pekerjaan = pekerjaanService.findById(id);
        return new ResponseEntity<>(pekerjaan, HttpStatus.OK);
    }

    @PostMapping("/pekerjaan/{id}/lamar")
    public ResponseEntity<String> melamarPekerjaan(@PathVariable Long id) {
        try {
            pekerjaanService.melamarPekerjaan(id);
            return ResponseEntity.ok("Berhasil melamar.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Terjadi kesalahan: " + e.getMessage());
        }
    }
    @PutMapping("/pekerjaan/{id}")
    public ResponseEntity<Pekerjaan> editPekerjaan(@PathVariable Long id, @RequestBody Pekerjaan pekerjaan) {
        pekerjaanService.editPekerjaan(id, pekerjaan);

        return ResponseEntity.ok(pekerjaan);
    }



    @PostMapping("/pekerjaan/add")
    public ResponseEntity<Pekerjaan> save(@RequestBody Pekerjaan pekerjaan) {
        Pekerjaan pekerjaanBaru = pekerjaanService.save(pekerjaan);
        return new ResponseEntity<>(pekerjaanBaru, HttpStatus.CREATED);
    }
    @DeleteMapping("/pekerjaan/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        pekerjaanService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/pekerjaan/upload-image/{id}")
    public ResponseEntity<?> uploadImage(@PathVariable("id") Long id, @RequestParam("image") MultipartFile image) throws IOException {
        Pekerjaan user = pekerjaanRepository.findById(id).orElseThrow(() -> new NotFoundException("Pekerjaan tidak ditemukan"));

        // Upload file ke Firebase Storage dan dapatkan URL download
        String downloadURL = uploadFileToFirebaseStorage(image, image.getOriginalFilename());

        // Update informasi gambar user
        user.setFotoPekerjaan(downloadURL);
        pekerjaanRepository.save(user);

        return ResponseEntity.ok(downloadURL);
    }

    public String uploadFile(File fileId, String fileName) throws IOException {
        // Create a temporary file with the specified filename
        File file = File.createTempFile("pekerjaan-image-", fileName);

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
    public String uploadImage(Pekerjaan user, @RequestParam("image") MultipartFile image) throws IOException {
        String fileName = getExtentions(image.getOriginalFilename());

        // Validasi format file
        if (!Arrays.asList("jpg", "jpeg", "png", "gif", "webp").contains(fileName)) {
            throw new RuntimeException("Format file gambar tidak didukung");
        }

        // Validasi ukuran file
        if (image.getSize() > 50_000_000) {
            throw new RuntimeException("Ukuran file gambar melebihi batas 50 MB");
        }

        // Upload file ke Firebase Storage dan dapatkan URL download
        String downloadURL = this.uploadFileToFirebaseStorage(image, fileName);

        // Update informasi gambar user
        user.setFotoPekerjaan(downloadURL);
        pekerjaanRepository.save(user);

        return downloadURL;
    }
    public File convertToFile(MultipartFile multipartFile, String fileName) throws IOException {
        File file = new File(fileName);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(multipartFile.getBytes());
            fos.close();
        }
        return file;
    }



}