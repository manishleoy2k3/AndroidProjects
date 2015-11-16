package com.manish.view.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.manish.Location.LocationHelperExample;
import com.manish.helper.HelperJava;
import com.manish.navigationdrawer.adapters.NavDrawerAdapter;
import com.manish.navigationdrawer.configuration.NavDrawerActivityConfiguration;
import com.manish.navigationdrawer.items.NavMenuItem;
import com.example.slidemenuframework.R;

/**
 * Created by Manish on 1/21/2015.
 */
public class DerivedActivityWithoutFragments extends AbstractNavDrawerActivity {

    TextView test;
    NavMenuItem[] menu;
    Button btnUpdCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        test=(TextView)findViewById(R.id.testview);
        test.setText("Test Derived Activity Without Fragments.");
        btnUpdCount=(Button)findViewById(R.id.updateCntBtn);
        btnUpdCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateCountValue();
            }
        });
    }

      @Override
      protected NavDrawerActivityConfiguration getNavDrawerConfiguration() {
        menu = new NavMenuItem[] {
                NavMenuItem.createSectionHeader(100,"Label 1"),
                NavMenuItem.create(101, "TEST Derived 1", "ic_launcher",
                        false, this),
                NavMenuItem.create(102, "TEST Derived 1", "ic_launcher",
                        false, this,true,"10+")};

        NavDrawerActivityConfiguration navDrawerActivityConfiguration = new NavDrawerActivityConfiguration();
        navDrawerActivityConfiguration.setMainLayout(R.layout.activity_without_fragments);
        navDrawerActivityConfiguration.setDrawerLayoutId(R.id.drawer_layout);
        navDrawerActivityConfiguration.setLeftDrawerId(R.id.left_drawer);
        navDrawerActivityConfiguration.setNavItems(menu);
        navDrawerActivityConfiguration.setDrawerShadow(R.drawable.ic_drawer);
        navDrawerActivityConfiguration.setDrawerOpenDesc(R.string.drawer_open_derived);
        navDrawerActivityConfiguration.setContainerTranslation(true,R.id.containerActivity);
        navDrawerActivityConfiguration.setHeaderViewId(R.layout.header_navigation_drawer);
        navDrawerActivityConfiguration.setLabelTextStyle(R.style.navigation_label_style);
        navDrawerActivityConfiguration.setCountTextStyle(R.style.navigation_count_style);
        navDrawerActivityConfiguration.setSectionHeaderStyle(R.style.list_seperate_style);
        //  navDrawerActivityConfiguration.setActionBarMenuId(R.menu.list_menu);
        navDrawerActivityConfiguration
                .setDrawerCloseDesc(R.string.drawer_close_derived);
        navDrawerActivityConfiguration.setBaseAdapter(new NavDrawerAdapter(
                this, R.layout.navdrawer_item, menu,navDrawerActivityConfiguration));
        return navDrawerActivityConfiguration;
    }

    public void updateCountValue()
    {
        menu[2].setCountValue("11+");
        updateNavigationDrawer();
    }

    protected void setNavHeaderValues(View view)
    {
        TextView txtView=(TextView)view.findViewById(R.id.viewNavigationHeader);
        ImageView imgView=(ImageView)view.findViewById(R.id.imageNavigationHeader);
        txtView.setText("Manish Kumar");
        Bitmap bim= BitmapFactory.decodeResource(getResources(),R.drawable.profilepic);
        imgView.setImageBitmap(HelperJava.getRoundedShape(bim, this, 140, 140));
        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(DerivedActivityWithoutFragments.this,"Profile pic clicked",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected boolean onNavItemSelected(int id) {
        switch ((int) id) {
            case 101:
                Intent intent = new Intent(DerivedActivityWithoutFragments.this,
                        LocationHelperExample.class);
                startActivity(intent);
                break;
        }
        return true;
    }

    @Override
    protected boolean onActionItemSelected(int id) {
        Toast.makeText(this,"The menu item selected",Toast.LENGTH_SHORT).show();
        return true;
    }
}