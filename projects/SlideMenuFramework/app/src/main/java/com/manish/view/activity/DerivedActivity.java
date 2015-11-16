package com.manish.view.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.manish.helper.HelperJava;
import com.manish.navigationdrawer.adapters.NavDrawerAdapter;
import com.manish.navigationdrawer.configuration.NavDrawerActivityConfiguration;
import com.manish.navigationdrawer.items.NavMenuItem;
import com.manish.navigationdrawer.view.fragments.FragmentOne;
import com.manish.navigationdrawer.view.fragments.FragmentThree;
import com.example.slidemenuframework.R;

/**
 * The Class DerivedActivity.
 */
public class DerivedActivity extends AbstractNavDrawerActivity {


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_frame, new FragmentThree()).commit();
		}
	}

	@Override
	protected NavDrawerActivityConfiguration getNavDrawerConfiguration() {

		        NavMenuItem[] menu = new NavMenuItem[] {
                NavMenuItem.createSectionHeader(101, "Using Fragments"),
                NavMenuItem.create(102, "New Fragment Activity", "ic_launcher",
                        false, this),
                NavMenuItem.create(103, "Same Activity New Fragment", "ic_launcher",
                        false, this,true,"10+"),
                NavMenuItem.createSectionHeader(104, "Using Activity"),
                NavMenuItem.create(105, "New Activity", "ic_launcher",
                        false, this)
               };

		NavDrawerActivityConfiguration navDrawerActivityConfiguration = new NavDrawerActivityConfiguration();
		navDrawerActivityConfiguration.setMainLayout(R.layout.activity_derive_layout);
		navDrawerActivityConfiguration.setDrawerLayoutId(R.id.drawer_layout);
		navDrawerActivityConfiguration.setLeftDrawerId(R.id.left_drawer);
		navDrawerActivityConfiguration.setNavItems(menu);
		navDrawerActivityConfiguration.setDrawerShadow(R.drawable.ic_drawer);
		navDrawerActivityConfiguration.setDrawerOpenDesc(R.string.drawer_open);
        navDrawerActivityConfiguration.setHeaderViewId(R.layout.header_navigation_drawer);
        navDrawerActivityConfiguration.setContainerTranslation(true,R.id.content_frame);
        navDrawerActivityConfiguration.setActionBarMenuId(R.menu.list_menu);
		navDrawerActivityConfiguration
				.setDrawerCloseDesc(R.string.drawer_close);
		navDrawerActivityConfiguration.setBaseAdapter(new NavDrawerAdapter(
				this, R.layout.navdrawer_item, menu,navDrawerActivityConfiguration));
		return navDrawerActivityConfiguration;
	}

    protected void setNavHeaderValues(View view)
    {
        TextView txtView=(TextView)view.findViewById(R.id.viewNavigationHeader);
        ImageView imgView=(ImageView)view.findViewById(R.id.imageNavigationHeader);
        txtView.setText("Manish Kumar");
        Bitmap bim= BitmapFactory.decodeResource(getResources(), R.drawable.profilepic);
        imgView.setImageBitmap(HelperJava.getRoundedShape(bim, this, 140, 140));
        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DerivedActivity.this, "Profile pic clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

	@Override
	protected boolean onNavItemSelected(int id) {
      switch ((int) id) {
		case 102:
            Intent intent = new Intent(DerivedActivity.this,
					AppMainActivity.class);
			startActivity(intent);

			break;

		case 103:
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.content_frame, new FragmentOne()).commit();
			break;
         case 105:
             Intent intent1 = new Intent(DerivedActivity.this,
                     DerivedActivityWithoutFragments.class);
             startActivity(intent1);
             break;
		}
		return true;
	}

	@Override
	protected boolean onActionItemSelected(int id) {
        Toast.makeText(DerivedActivity.this, "Menu Item clicked", Toast.LENGTH_SHORT).show();
		return true;
	}
}
