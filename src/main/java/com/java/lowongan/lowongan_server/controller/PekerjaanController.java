package com.java.lowongan.lowongan_server.controller;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.java.lowongan.lowongan_server.model.Pekerjaan;
import com.java.lowongan.lowongan_server.model.Pelamar;
import com.java.lowongan.lowongan_server.repository.PekerjaanRepository;
import com.java.lowongan.lowongan_server.service.PekerjaanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;


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
        @GetMapping("/pekerjaan/pelamar/{userId}")
        public ResponseEntity<List<Pekerjaan>> findByUserID(@PathVariable Long userId) {
            List<Pekerjaan> pekerjaanList = pekerjaanService.findByUserId(userId);

            if (pekerjaanList.isEmpty()) {

                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {

                return new ResponseEntity<>(pekerjaanList, HttpStatus.OK);
            }
        }



    @GetMapping("/pekerjaan/user/{userId}/id/{id}")
    public ResponseEntity<Pekerjaan> findByUserIdAndId(
            @PathVariable Long userId,
            @PathVariable Long id
    ) {
        Optional<Pekerjaan> pekerjaan = pekerjaanService.findByUserIdAndId(userId, id);

        return pekerjaan.map(value ->
                new ResponseEntity<>(value, HttpStatus.OK)
        ).orElseGet(() ->
                new ResponseEntity<>(HttpStatus.NOT_FOUND)
        );
    }


    @GetMapping("/pekerjaan/all")
    public ResponseEntity<List<Pekerjaan>> findAll() {
        List<Pekerjaan> pekerjaan = pekerjaanService.findAll();
        return new ResponseEntity<>(pekerjaan, HttpStatus.OK);
    }
    @GetMapping("/pekerjaan/getBy/{id}")
    public ResponseEntity<Pekerjaan> findById(@PathVariable Long id) {
        Pekerjaan pekerjaan = pekerjaanService.findById(id);
        return new ResponseEntity<>(pekerjaan, HttpStatus.OK);
    }



    @PutMapping("/pekerjaan/{id}")
    public ResponseEntity<Pekerjaan> editPekerjaan(@PathVariable Long id, @RequestBody Pekerjaan pekerjaan) {
        pekerjaanService.editPekerjaan(id, pekerjaan);

        return ResponseEntity.ok(pekerjaan);
    }
    @DeleteMapping("/pekerjaan/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        pekerjaanService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/pekerjaan/add" )
    public ResponseEntity<?> createPekerjaan(@RequestBody Pekerjaan pekerjaan) throws IOException {
        try {
            pekerjaan.setTanggalPost(new Date());
            Pekerjaan pekerjaanBaru = pekerjaanService.save(pekerjaan);

            Long newId = pekerjaanBaru.getId();

            return new ResponseEntity<>("redirect:/pekerjaan/" + newId + "/uploadImage", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error processing the request", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/pekerjaan/{id}/uploadImage")
    public ResponseEntity<String> uploadImage(@PathVariable Long id, @RequestParam("image") MultipartFile image) {
        try {
            Pekerjaan pekerjaan = pekerjaanRepository.getById(id);
            if (pekerjaan == null) {
                return ResponseEntity.notFound().build();
            }

            String downloadUrl = uploadImage(pekerjaan, image);
            pekerjaan.setFotoPekerjaan(downloadUrl);
            pekerjaanRepository.save(pekerjaan);
            return ResponseEntity.ok(downloadUrl);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error uploading image");
        }
    }

    private String uploadImage(Pekerjaan pekerjaan, MultipartFile image) throws IOException {
        // Create a BlobId object with the image data and metadata
        BlobId blobId = BlobId.of("lowongan-a0c4a.appspot.com", image.getOriginalFilename());
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                .setContentType("media")
                .build();
        Credentials credentials = GoogleCredentials.fromStream(new FileInputStream("./src/main/resources/ServiceAccount.json"));
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        storage.create(blobInfo, image.getBytes());
        return String.format(DOWNLOAD_URL, URLEncoder.encode(image.getOriginalFilename(), StandardCharsets.UTF_8));
    }

    public String uploadFile(File fileId, String fileName) throws IOException {
        // Create a temporary file with the specified filename
        File file = File.createTempFile("pekerjaan-image-", fileName);
        byte[] fileContents = "your_file_contents".getBytes();
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(fileContents);
        }
        String downloadURL = uploadFileToFirebaseStorage((MultipartFile) file, fileName);
        file.delete();

        return downloadURL;
    }

    private String uploadFileToFirebaseStorage(MultipartFile multipartFile, String fileName) throws IOException {
        // Create a BlobId object with the image data and metadata
        BlobId blobId = BlobId.of("lowongan-a0c4a.appspot.com", fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                .setContentType("media")
                .build();
        Credentials credentials = GoogleCredentials.fromStream(new FileInputStream("./src/main/resources/ServiceAccount.json"));
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        storage.create(blobInfo, multipartFile.getBytes());
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



}