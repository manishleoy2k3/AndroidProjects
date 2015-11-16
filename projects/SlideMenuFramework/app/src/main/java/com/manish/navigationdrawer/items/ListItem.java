package com.manish.navigationdrawer.items;

/**
 * Created by Manish on 12/30/2014.
 */
public class ListItem implements ListItemInterface {

    /** The id. */
    private int id;

    /** The heading. */
    private String heading;
    private String smallText;
    boolean checked;

    private ListItem()
    {

    }

    public static ListItem createItem(int id,String heading,String smallText)
    {
        ListItem item=new ListItem();
        item.setId(id);
        item.setHeading(heading);
        item.setSmallText(smallText);
        return item;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getHeadingText() {
        return heading;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public void setSmallText(String text)
    {
        this.smallText=text;
    }

    public String getSmallText()
    {
        return this.smallText;
    }
}
