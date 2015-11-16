package com.manish.navigationdrawer.items;

import android.content.Context;

/**
 * The Class NavMenuItem.
 */
public class NavMenuItem implements NavDrawerItem {

    private MenuItemType type;
	/** The id. */
	private int id;

	/** The label. */
	private String label;

	/** The icon. */
	private int icon;

	/** The update action bar title. */
	private boolean updateActionBarTitle;

    /**String to set count value*/
    private String count = "0";

    /** boolean to set visiblity of the counter*/
    private boolean isCounterVisible = false;

    private boolean enabledState;

	/**
	 * Instantiates a new nav menu item.
	 */
	private NavMenuItem() {
	}

	/**
	 * Creates the.
	 * 
	 * @param id
	 *            the id
	 * @param label
	 *            the label
	 * @param icon
	 *            the icon
	 * @param updateActionBarTitle
	 *            the update action bar title
	 * @param context
	 *            the context
	 * @return the nav menu item
	 */
	public static NavMenuItem create(int id, String label, String icon,
			boolean updateActionBarTitle, Context context) {
		NavMenuItem item = new NavMenuItem();
		item.setId(id);
		item.setLabel(label);
		item.setIcon(context.getResources().getIdentifier(icon, "drawable",
				context.getPackageName()));
		item.setUpdateActionBarTitle(updateActionBarTitle);
        item.type=MenuItemType.TYPE_ITEM;
        item.setEnabledState(true);
		return item;
	}

    public static NavMenuItem create(int id, String label, String icon,
                                     boolean updateActionBarTitle, Context context,boolean isCounterVisible,String count) {
        NavMenuItem item = new NavMenuItem();
        item.setId(id);
        item.setLabel(label);
        item.setIcon(context.getResources().getIdentifier(icon, "drawable",
                context.getPackageName()));
        item.setUpdateActionBarTitle(updateActionBarTitle);
        item.setCounterVisible(isCounterVisible);
        item.setCountValue(count);
        item.type=MenuItemType.TYPE_ITEM;
        item.setEnabledState(true);
        return item;
    }

    public static NavMenuItem createSectionHeader(int id,String label)
    {
        NavMenuItem item = new NavMenuItem();
        item.setId(id);
        item.setLabel(label);
        item.type=MenuItemType.TYPE_SECTION;
        return item;
    }

    public void setEnabledState(boolean enableState)
    {
        this.enabledState=enableState;
    }
    public void setCounterVisible(boolean visibility)
    {
        this.isCounterVisible=visibility;
    }

    public void setCountValue(String count)
    {
        this.count=count;
    }

    @Override
    public boolean isCounterVisible() {
        return this.isCounterVisible;
    }

    @Override
    public String getCountValue() {
        return this.count;
    }

    @Override
	public MenuItemType getType() {
		return this.type;
	}

	public int getId() {
		return id;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id
	 *            the new id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.slidemenuframework.NavDrawerItem#getLabel()
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Sets the label.
	 * 
	 * @param label
	 *            the new label
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * Gets the icon.
	 * 
	 * @return the icon
	 */
	public int getIcon() {
		return icon;
	}

	/**
	 * Sets the icon.
	 * 
	 * @param icon
	 *            the new icon
	 */
	public void setIcon(int icon) {
		this.icon = icon;
	}

	@Override
	public boolean isEnabled() {
		return this.enabledState;
	}

	@Override
	public boolean updateActionBarTitle() {
		return this.updateActionBarTitle;
	}

    /**
	 * Sets the update action bar title.
	 * 
	 * @param updateActionBarTitle
	 *            the new update action bar title
	 */
	public void setUpdateActionBarTitle(boolean updateActionBarTitle) {
		this.updateActionBarTitle = updateActionBarTitle;
	}
}