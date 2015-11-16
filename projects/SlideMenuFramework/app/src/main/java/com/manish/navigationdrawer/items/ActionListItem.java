package com.manish.navigationdrawer.items;

/**
 * The Interface NavDrawerItem.
 */
public interface ActionListItem {

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
	public int getType();

	/**
	 * Checks if is enabled.
	 * 
	 * @return true, if is enabled
	 */
	public boolean isEnabled();

}
