package com.manish.universalimageloader;

/**
 * Created by Manish on 1/15/2015.
 */
public class ImageListItem implements ImageListItemInterface {

    private String imageUrl;

    @Override
    public String getUrl() {
        return imageUrl;
    }


    public static ImageListItem createItem(String url)
    {
       ImageListItem item=new ImageListItem();
       item.setImageUrl(url);
       return item;
    }

    private void setImageUrl(String url)
    {
        this.imageUrl=url;
    }
}
