package com.manish.view.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.manish.Location.LocationHelperExample;
import com.manish.horizontallistview.HorizontalListFragment;
import com.manish.navigationdrawer.adapters.NavDrawerAdapter;
import com.manish.navigationdrawer.configuration.NavDrawerActivityConfiguration;
import com.manish.navigationdrawer.items.NavDrawerItem;
import com.manish.navigationdrawer.items.NavMenuItem;
import com.manish.navigationdrawer.view.fragments.FragmentTwo;
import com.manish.universalimageloader.HorizontalListWithLoaderFragment;
import com.example.slidemenuframework.R;

/**
 * The Class AbstractNavDrawerActivity +-. This will serve as a base class for
 * all the derived activities. If no Implementation is provided the default
 * implementation will be provided for Navigation Drawer.
 * 
 */
public abstract class AbstractNavDrawerActivity extends FragmentActivity {

	/** The m drawer layout. */
	private DrawerLayout mDrawerLayout;

	/** The m drawer toggle. */
	private ActionBarDrawerToggle mDrawerToggle;

	/** The m drawer list. */
    private ListView mDrawerList;

	/** The m drawer title. */
	private CharSequence mDrawerTitle;

	/** The m title. */
	private CharSequence mTitle;

	/** The nav conf. */
	private NavDrawerActivityConfiguration navConf;

	/**
	 * Gets the nav drawer configuration.
	 * 
	 * @return the nav drawer configuration
	 */
	protected abstract NavDrawerActivityConfiguration getNavDrawerConfiguration();

	/**
	 * On nav item selected.
	 * 
	 * @param id
	 *            the id
	 * @return true, if successful
	 */
	protected abstract boolean onNavItemSelected(int id);

	protected abstract boolean onActionItemSelected(int id);

    private ViewGroup containerLayout;

    protected abstract void setNavHeaderValues(View view);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		navConf = getDefaultNavDrawerConfiguration();
		// set the parent container as the content view
		setContentView(navConf.getMainLayout());

		mTitle = mDrawerTitle = getTitle();

		mDrawerLayout = (DrawerLayout) findViewById(navConf.getDrawerLayoutId());
		mDrawerList = (ListView) findViewById(navConf.getLeftDrawerId());
		mDrawerList.setAdapter(navConf.getBaseAdapter());
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        if(navConf.isContainerTranslation())
        {
            containerLayout=(ViewGroup)findViewById(navConf.getMainContainerLayoutId());
        }
        if(navConf.getHeaderViewId()>0)
        {
            LayoutInflater inflater = getLayoutInflater();
            View header = (View) inflater.inflate(navConf.getHeaderViewId(),
                    mDrawerList, false);
            mDrawerList.addHeaderView(header, null, false);
            setNavHeaderValues(header);
        }

        if(navConf.isHavingDrawerShow())
		this.initDrawerShadow();

		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
        //getActionBar().setDisplayShowHomeEnabled(false);

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				getDrawerIcon(), navConf.getDrawerOpenDesc(),
				navConf.getDrawerCloseDesc()) {
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
				invalidateOptionsMenu();
			}

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                if(navConf.isContainerTranslation()) {
                    float moveFactor = (mDrawerList.getWidth() * slideOffset);
                    containerLayout.setTranslationX(moveFactor);
                }
            }

            public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(mDrawerTitle);
                mDrawerList.bringToFront();
                mDrawerLayout.requestLayout();
				invalidateOptionsMenu();
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);
	}

    public void updateNavigationDrawer()
    {
        navConf.getBaseAdapter().notifyDataSetChanged();
    }

	/**
	 * Gets the default nav drawer configuration.
	 * 
	 * @return the default nav drawer configuration
	 */
	private NavDrawerActivityConfiguration getDefaultNavDrawerConfiguration() {

        NavDrawerItem[] menu = new NavDrawerItem[] {
                NavMenuItem.create(101, "Drawer with new Activity", "navdrawer_friends",
                        false, this),
                NavMenuItem.create(102, "Custom List Drawer", "navdrawer_airport",
                        true, this),
                NavMenuItem.create(103, "Find Location", "location",
                        true, this),
                NavMenuItem.create(104, "Horizontal Custom List", "location",
                        true, this),
                NavMenuItem.create(105, "ListView Image loader", "location",
                        true, this),
                NavMenuItem.create(106, "Drawer with Fragments", "location",
                true, this)};

        NavDrawerActivityConfiguration navDrawerActivityConfiguration = new NavDrawerActivityConfiguration();
        navDrawerActivityConfiguration.setMainLayout(R.layout.activity_main);
        navDrawerActivityConfiguration.setDrawerLayoutId(R.id.drawer_layout);
        navDrawerActivityConfiguration.setLeftDrawerId(R.id.left_drawer);
        navDrawerActivityConfiguration.setNavItems(menu);
        navDrawerActivityConfiguration.setDrawerShadow(R.drawable.ic_drawer);
        navDrawerActivityConfiguration.setDrawerOpenDesc(R.string.drawer_open);
        navDrawerActivityConfiguration
                .setDrawerCloseDesc(R.string.drawer_close);
        navDrawerActivityConfiguration.setBaseAdapter(new NavDrawerAdapter(
                this, R.layout.navdrawer_item, menu,navDrawerActivityConfiguration));

        if (getNavDrawerConfiguration() == null) {
            return navDrawerActivityConfiguration;
        } else {
            return getNavDrawerConfiguration();
        }
    }

	/**
	 * Inits the drawer shadow.
	 */
	protected void initDrawerShadow() {
		mDrawerLayout.setDrawerShadow(navConf.getDrawerShadow(),
				GravityCompat.START);
	}

	/**
	 * Gets the drawer icon.
	 * 
	 * @return the drawer icon
	 */
	protected int getDrawerIcon() {
		return R.drawable.ic_drawer;
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

    //	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
        if(navConf.getActionBarMenuId()>0)
		inflater.inflate(navConf.getActionBarMenuId(), menu);
		return true;
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		if (navConf.getActionMenuItemsToHideWhenDrawerOpen() != null) {
            boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
            for (int iItem : navConf.getActionMenuItemsToHideWhenDrawerOpen()) {
                menu.findItem(iItem).setVisible(!drawerOpen);
            }
        }
	    return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		} else {
            if(onActionItemSelected(item.getItemId()))
            {
                return true;
            }
            else
            return super.onOptionsItemSelected(item);
		}
	}
//
//	@Override
//	public boolean onKeyDown(int keyCode, KeyEvent event) {
//		if (keyCode == KeyEvent.KEYCODE_MENU) {
//			if (this.mDrawerLayout.isDrawerOpen(this.mDrawerList)) {
//				this.mDrawerLayout.closeDrawer(this.mDrawerList);
//			} else {
//				this.mDrawerLayout.openDrawer(this.mDrawerList);
//			}
//			return true;
//		}
//		return super.onKeyDown(keyCode, event);
//	}

	/**
	 * Gets the drawer layout.
	 * 
	 * @return the drawer layout
	 */
	protected DrawerLayout getDrawerLayout() {
		return mDrawerLayout;
	}

	/**
	 * Gets the drawer toggle.
	 * 
	 * @return the drawer toggle
	 */
	protected ActionBarDrawerToggle getDrawerToggle() {
		return mDrawerToggle;
	}

	/**
	 * The listener interface for receiving drawerItemClick events. The class
	 * that is interested in processing a drawerItemClick event implements this
	 * interface, and the object created with that class is registered with a
	 * component using the component's
	 * <code>addDrawerItemClickListener<code> method. When
	 * the drawerItemClick event occurs, that object's appropriate
	 * method is invoked.
	 * 
	 * @see //DrawerItemClickEvent
	 */
	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
                     long id) {
            selectItem(position);
        }
	}

	/**
	 * Select item in drawer,setting activity title when item clicked in drawer and also handling further work for click handling
	 * 
	 * @param position
	 *            the position
	 */
	public void selectItem(int position) {
        int newPosition;
        if(navConf.getHeaderViewId()>0)
        newPosition=position-1;
        else
        newPosition=position;
		NavDrawerItem selectedItem = navConf.getNavItems()[newPosition];
		// if(this.onNavItemSelected(selectedItem.getId());)
		itemSelectedFromNavigation(selectedItem.getId());
		// this.onNavItemSelected(selectedItem.getId());
		mDrawerList.setItemChecked(position, true);

		if (selectedItem.updateActionBarTitle()) {
			setTitle(selectedItem.getLabel());
		}

		if (this.mDrawerLayout.isDrawerOpen(this.mDrawerList)) {
			mDrawerLayout.closeDrawer(mDrawerList);
		}
	}

	/**
	 * Method for checking that Item click is handled in derived class or not. If not default work is done.
	 * 
	 * @param id
	 *            the id
	 */
	private void itemSelectedFromNavigation(int id) {

        //Here checking whether the click is handled in derived class or not//
		if (!onNavItemSelected(id)) {
			switch ((int) id) {
			case 101:

                Intent intent = new Intent(AbstractNavDrawerActivity.this,
                        DerivedActivityWithoutFragments.class);
                startActivity(intent);
				// getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,
				// new FragmentTwo()).commit();
				break;
			case 102:
//				getSupportFragmentManager().beginTransaction()
//						.replace(R.id.content_frame, new FragmentThree())
//						.commit();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_frame, new FragmentTwo())
                        .commit();
				break;
            case 103:
                Intent intentLocation = new Intent(AbstractNavDrawerActivity.this,
                        LocationHelperExample.class);
                startActivity(intentLocation);
                break;
                case 104:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_frame, new HorizontalListFragment())
                            .commit();
                break;
                case 105:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_frame, new HorizontalListWithLoaderFragment())
                            .commit();
                    break;
                case 106:
                    Intent intent1 = new Intent(AbstractNavDrawerActivity.this,
                            DerivedActivity.class);
                    startActivity(intent1);
                    break;
			}
		}

	}

    @Override
    public void onBackPressed() {
        if (this.mDrawerLayout.isDrawerOpen(this.mDrawerList)) {
				this.mDrawerLayout.closeDrawer(this.mDrawerList);
			} else {
                super.onBackPressed();
			}
    }

    @Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}


}