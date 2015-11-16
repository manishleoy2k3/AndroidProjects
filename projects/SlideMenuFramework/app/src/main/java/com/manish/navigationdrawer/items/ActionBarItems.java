package com.manish.navigationdrawer.items;

import android.content.Context;

/**
 * The Class NavMenuItem.
 */
public class ActionBarItems implements ActionListItem {

	/** The Constant ITEM_TYPE. */
	public static final int ITEM_TYPE = 1;

	/** The id. */
	private int id;

	/** The label. */
	private String label;

	/** The icon. */
	private int icon;

	/**
	 * Instantiates a new nav menu item.
	 */
	private ActionBarItems() {
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
	public static ActionBarItems create(int id, String label, String icon,
			boolean updateActionBarTitle, Context context) {
		ActionBarItems item = new ActionBarItems();
		item.setId(id);
		item.setLabel(label);
		

		return item;
	}

	@Override
	public int getType() {
		return ITEM_TYPE;
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
		return true;
	}

}