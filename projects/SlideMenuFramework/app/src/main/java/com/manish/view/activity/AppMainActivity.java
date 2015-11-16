package com.manish.view.activity;

import android.os.Bundle;
import android.view.View;

import com.manish.navigationdrawer.configuration.NavDrawerActivityConfiguration;
import com.manish.navigationdrawer.view.fragments.FragmentOne;
import com.example.slidemenuframework.R;

/**
 * The Class YourAppMainActivity.
 */
public class AppMainActivity extends AbstractNavDrawerActivity {

	@Override
     public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_frame, new FragmentOne()).commit();
        }

    }

    protected void setNavHeaderValues(View view)
    {

    }

	@Override
	protected NavDrawerActivityConfiguration getNavDrawerConfiguration() {

		// NavDrawerItem[] menu = new NavDrawerItem[] {
		// NavMenuItem.create(105, "TEST NEw Item 1", "navdrawer_friends",
		// false, this),
		// NavMenuItem.create(106, "TEST New Item 2", "navdrawer_airport",
		// true, this), };
		//
		// NavDrawerActivityConfiguration navDrawerActivityConfiguration = new
		// NavDrawerActivityConfiguration();
		// navDrawerActivityConfiguration.setMainLayout(R.layout.activity_main);
		// navDrawerActivityConfiguration.setDrawerLayoutId(R.id.drawer_layout);
		// navDrawerActivityConfiguration.setLeftDrawerId(R.id.left_drawer);
		// navDrawerActivityConfiguration.setNavItems(menu);
		// navDrawerActivityConfiguration.setDrawerShadow(R.drawable.ic_drawer);
		// navDrawerActivityConfiguration.setDrawerOpenDesc(R.string.drawer_open);
		// navDrawerActivityConfiguration
		// .setDrawerCloseDesc(R.string.drawer_close);
		// navDrawerActivityConfiguration.setBaseAdapter(new NavDrawerAdapter(
		// this, R.layout.navdrawer_item, menu));
		return null;
	}

	@Override
	protected boolean onNavItemSelected(int id) {
		// switch ((int) id) {
		// case 105:
		// Intent intent = new Intent(YourAppMainActivity.this,
		// DerivedActivity.class);
		// startActivity(intent);
		// //
		// getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,
		// // new FragmentTwo()).commit();
		// break;
		// case 106:
		// getSupportFragmentManager().beginTransaction()
		// .replace(R.id.content_frame, new FragmentThree()).commit();
		// break;
		// }
		return false;
	}

	@Override
	protected boolean onActionItemSelected(int id) {
		return false;
	}
}