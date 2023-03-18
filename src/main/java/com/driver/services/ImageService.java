package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog
        Blog blog = blogRepository2.findById(blogId).get();

        Image image = new Image();
        image.setDescription(description);
        image.setDimensions(dimensions);
        image.setBlog(blog);
        blog.getImageList().add(image);
        blogRepository2.save(blog);

        return image;
    }

    public void deleteImage(Integer id){
        imageRepository2.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        int indOfX = screenDimensions.indexOf('X');
        int screenH = Integer.parseInt(screenDimensions.substring(0,indOfX));
        int screenW = Integer.parseInt(screenDimensions.substring(indOfX+1));

        Image image = imageRepository2.findById(id).get();

        int indexOfinImage = image.getDimensions().indexOf('X');

        int imageH = Integer.parseInt(image.getDimensions().substring(0, indexOfinImage));
        int imageW = Integer.parseInt(image.getDimensions().substring((indexOfinImage+1)));

        return (screenH/imageH)*(screenW/imageW);
    }
}
