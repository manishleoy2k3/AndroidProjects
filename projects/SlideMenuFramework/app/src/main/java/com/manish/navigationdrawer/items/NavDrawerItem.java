package com.manish.navigationdrawer.items;


/**
 * The Interface NavDrawerItem.
 */
public interface NavDrawerItem {
    
    /**
     * Gets the id.
     *
     * @return the id
     */
    public int getId();
    
    /**
     * Gets the label.
     *
     * @return the label
     */
    public String getLabel();
    
    /**
     * Gets the type.
     *
     * @return the type
     */
    public MenuItemType getType();
    
    /**
     * Checks if is enabled.
     *
     * @return true, if is enabled
     */
    public boolean isEnabled();
    
    /**
     * Update action bar title.
     *
     * @return true, if successful
     */
    public boolean updateActionBarTitle();

    /**
     * Getting counter visibility
     *
     * @return true, if successful
     */
    public boolean isCounterVisible();

    /**
     * Getting counter value
     *
     * @return value of counter
     */
    public String getCountValue();
}
