package com.et4.gametrackerproject.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

public interface CloudinaryService{

    String savePhoto(InputStream photo, String title) throws IOException;


}
