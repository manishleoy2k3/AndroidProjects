package com.manish.navigationdrawer.configuration;

import android.widget.BaseAdapter;

import com.manish.navigationdrawer.items.NavDrawerItem;
import com.example.slidemenuframework.R;

/**
 * The Class NavDrawerActivityConfiguration.
 */
public class NavDrawerActivityConfiguration {

    /** The main layout. */
    private int mainLayout;
    
    /** The drawer shadow. */
    private int drawerShadow;
    
    /** The drawer layout id. */
    private int drawerLayoutId;
    
    /** The left drawer id. */
    private int leftDrawerId;
    
    /** The action menu items to hide when drawer open. */
    private int[] actionMenuItemsToHideWhenDrawerOpen;
    
    /** The nav items. */
    private NavDrawerItem[] navItems;
    
    /** The drawer open desc. */
    private int drawerOpenDesc;
    
    /** The drawer close desc. */
    private int drawerCloseDesc;
    
    /** The base adapter. */
    private BaseAdapter baseAdapter;

    private int mainContainerLayoutId;

    private boolean containerTranslation,havingDrawerShow;

    private int actionBarMenuId;

    private int labelTextStyle= R.style.navigation_label_style;

    private int countTextStyle=R.style.navigation_count_style;

    private int sectionHeaderStyle=R.style.list_seperate_style;

    public int getSectionHeaderStyle() {
        return sectionHeaderStyle;
    }

    public void setSectionHeaderStyle(int sectionHeaderStyle) {
        this.sectionHeaderStyle = sectionHeaderStyle;
    }

    public int getLabelTextStyle() {
        return labelTextStyle;
    }

    public void setLabelTextStyle(int labelTextStyle) {
        this.labelTextStyle = labelTextStyle;
    }

    public int getCountTextStyle() {
        return countTextStyle;
    }

    public void setCountTextStyle(int countTextStyle) {
        this.countTextStyle = countTextStyle;
    }



    public int getActionBarMenuId() {
        return actionBarMenuId;
    }

    public void setActionBarMenuId(int actionBarMenuId) {
        this.actionBarMenuId = actionBarMenuId;
    }

    public int getHeaderViewId() {
        return headerViewId;

    }

    public void setHeaderViewId(int headerViewId) {
        this.headerViewId = headerViewId;
    }

    private int headerViewId;



    public boolean isContainerTranslation() {
        return containerTranslation;
    }

    public void setContainerTranslation(boolean containerTranslation,int mainContainerId) {
        this.containerTranslation = containerTranslation;
        this.mainContainerLayoutId=mainContainerId;
    }

    public int getMainContainerLayoutId()
    {
        return mainContainerLayoutId;
    }

    /**
     * Gets the main layout.
     *
     * @return the main layout
     */
    public int getMainLayout() {
        return mainLayout;
    }

    /**
     * Sets the main layout.
     *
     * @param mainLayout the new main layout
     */
    public void setMainLayout(int mainLayout) {
        this.mainLayout = mainLayout;
    }

    /**
     * Gets the drawer shadow.
     *
     * @return the drawer shadow
     */
    public int getDrawerShadow() {
        return drawerShadow;
    }

    /**
     * Sets the drawer shadow.
     *
     * @param drawerShadow the new drawer shadow
     */
    public void setDrawerShadow(int drawerShadow) {
        this.drawerShadow = drawerShadow;
        setHavingDrawerShow(true);
    }

    public boolean isHavingDrawerShow() {
        return havingDrawerShow;
    }

    public void setHavingDrawerShow(boolean havingDrawerShow) {
        this.havingDrawerShow = havingDrawerShow;
    }

    /**
     * Gets the drawer layout id.
     *
     * @return the drawer layout id
     */
    public int getDrawerLayoutId() {
        return drawerLayoutId;
    }

    /**
     * Sets the drawer layout id.
     *
     * @param drawerLayoutId the new drawer layout id
     */
    public void setDrawerLayoutId(int drawerLayoutId) {
        this.drawerLayoutId = drawerLayoutId;
    }

    /**
     * Gets the left drawer id.
     *
     * @return the left drawer id
     */
    public int getLeftDrawerId() {
        return leftDrawerId;
    }

    /**
     * Sets the left drawer id.
     *
     * @param leftDrawerId the new left drawer id
     */
    public void setLeftDrawerId(int leftDrawerId) {
        this.leftDrawerId = leftDrawerId;
    }

    /**
     * Gets the action menu items to hide when drawer open.
     *
     * @return the action menu items to hide when drawer open
     */
    public int[] getActionMenuItemsToHideWhenDrawerOpen() {
        return actionMenuItemsToHideWhenDrawerOpen;
    }

    /**
     * Sets the action menu items to hide when drawer open.
     *
     * @param actionMenuItemsToHideWhenDrawerOpen the new action menu items to hide when drawer open
     */
    public void setActionMenuItemsToHideWhenDrawerOpen(
            int[] actionMenuItemsToHideWhenDrawerOpen) {
        this.actionMenuItemsToHideWhenDrawerOpen = actionMenuItemsToHideWhenDrawerOpen;
    }

    /**
     * Gets the nav items.
     *
     * @return the nav items
     */
    public NavDrawerItem[] getNavItems() {
        return navItems;
    }

    /**
     * Sets the nav items.
     *
     * @param navItems the new nav items
     */
    public void setNavItems(NavDrawerItem[] navItems) {
        this.navItems = navItems;
    }

    /**
     * Gets the drawer open desc.
     *
     * @return the drawer open desc
     */
    public int getDrawerOpenDesc() {
        return drawerOpenDesc;
    }

    /**
     * Sets the drawer open desc.
     *
     * @param drawerOpenDesc the new drawer open desc
     */
    public void setDrawerOpenDesc(int drawerOpenDesc) {
        this.drawerOpenDesc = drawerOpenDesc;
    }

    /**
     * Gets the drawer close desc.
     *
     * @return the drawer close desc
     */
    public int getDrawerCloseDesc() {
        return drawerCloseDesc;
    }

    /**
     * Sets the drawer close desc.
     *
     * @param drawerCloseDesc the new drawer close desc
     */
    public void setDrawerCloseDesc(int drawerCloseDesc) {
        this.drawerCloseDesc = drawerCloseDesc;
    }

    /**
     * Gets the base adapter.
     *
     * @return the base adapter
     */
    public BaseAdapter getBaseAdapter() {
        return baseAdapter;
    }

    /**
     * Sets the base adapter.
     *
     * @param baseAdapter the new base adapter
     */
    public void setBaseAdapter(BaseAdapter baseAdapter) {
        this.baseAdapter = baseAdapter;
    }
}