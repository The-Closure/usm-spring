package org.closure.app.UserModule.services;

import org.springframework.stereotype.Service;

@Service
public class ImgService {



    private static String img_api = "https://eu.ui-avatars.com/api/?name=";

    public static String generateImg(String name)
    {
        return img_api+name;
    }
}
