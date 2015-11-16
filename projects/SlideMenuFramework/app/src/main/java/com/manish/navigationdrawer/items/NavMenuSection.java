package com.manish.navigationdrawer.items;

/**
 * The Class NavMenuSection.
 */
public class NavMenuSection implements NavDrawerItem {

	/** The Constant SECTION_TYPE. */
	public static final int SECTION_TYPE = 0;

	/** The id. */
	private int id;

	/** The label. */
	private String label;

    private MenuItemType type;
	/**
	 * Instantiates a new nav menu section.
	 */
	private NavMenuSection() {
	}

	/**
	 * Creates the.
	 * 
	 * @param id
	 *            the id
	 * @param label
	 *            the label
	 * @return the nav menu section
	 */
	public static NavMenuSection create(int id, String label) {
		NavMenuSection section = new NavMenuSection();
        section.setId(id);
		section.setLabel(label);
		return section;
	}

	@Override
	public MenuItemType getType() {
		return this.type;
	}

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

	@Override
	public boolean isEnabled() {
		return false;
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

	@Override
	public boolean updateActionBarTitle() {
		return false;
	}

    @Override
    public boolean isCounterVisible() {
        return false;
    }

    @Override
    public String getCountValue() {
        return null;
    }
}