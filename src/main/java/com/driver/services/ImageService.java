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
        List<Image> imageLists = blog.getImageList();
        Image image = new Image();
        image.setDescription(description);
        image.setDimensions(dimensions);

        imageLists.add(image);
        blog.setImageList(imageLists);
        blogRepository2.save(blog);

        return image;
    }

    public void deleteImage(Integer id){
        imageRepository2.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        Blog blog = blogRepository2.findById(id).get();
        List<Image> imageList = blog.getImageList();
        int count = 0;
        for(Image image : imageList){
            if (image.getDimensions() == screenDimensions){
                count++;
            }
        }
        return count;
    }
}
