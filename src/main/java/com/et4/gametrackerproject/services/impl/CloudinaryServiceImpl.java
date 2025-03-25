package com.et4.gametrackerproject.services.impl;

import com.cloudinary.Cloudinary;
import com.et4.gametrackerproject.services.CloudinaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class CloudinaryServiceImpl implements CloudinaryService {

    private final Cloudinary cloudinary;

    @Autowired
    public CloudinaryServiceImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @Override
    public String savePhoto(InputStream photo, String title) throws IOException {
        // Création des métadonnées
        Map<String, Object> uploadMetadata = new HashMap<>();
        uploadMetadata.put("public_id", title);
        uploadMetadata.put("overwrite", true);

        // Upload de la photo - respecte la signature de l'API Cloudinary
        Map uploadResult = cloudinary.uploader().upload(photo, uploadMetadata);

        // Récupération de l'identifiant public
        String photoId = (String) uploadResult.get("public_id");

        // Génération de l'URL avec des transformations si nécessaire
        return cloudinary.url()
                .secure(true)
                .publicId(photoId)
                .format("auto")
                .generate();
    }





}
