package com.manish.navigationdrawer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.manish.navigationdrawer.configuration.NavDrawerActivityConfiguration;
import com.manish.navigationdrawer.items.MenuItemType;
import com.manish.navigationdrawer.items.NavDrawerItem;
import com.manish.navigationdrawer.items.NavMenuItem;
import com.example.slidemenuframework.R;

/**
 * The Class NavDrawerAdapter.
 */
public class NavDrawerAdapter extends ArrayAdapter<NavDrawerItem> {

	/** The inflater. */
	private LayoutInflater inflater;

    private NavDrawerActivityConfiguration conFig;
	/**
	 * Instantiates a new nav drawer adapter.
	 * 
	 * @param context
	 *            the context
	 * @param textViewResourceId
	 *            the text view resource id
	 * @param objects
	 *            the objects
	 */
	public NavDrawerAdapter(Context context, int textViewResourceId,
			NavDrawerItem[] objects,NavDrawerActivityConfiguration conFig) {
		super(context, textViewResourceId, objects);
		this.inflater = LayoutInflater.from(context);
        this.conFig=conFig;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = null;
		NavDrawerItem menuItem = this.getItem(position);
		if (menuItem.getType() == MenuItemType.TYPE_ITEM) {
			view = getItemView(convertView, parent, menuItem);
		} else {
			view = getSectionView(convertView, parent, menuItem);
		}
		return view;
	}

	/**
	 * Gets the item view.
	 * 
	 * @param convertView
	 *            the convert view
	 * @param parentView
	 *            the parent view
	 * @param navDrawerItem
	 *            the nav drawer item
	 * @return the item view
	 */
	public View getItemView(View convertView, ViewGroup parentView,
			NavDrawerItem navDrawerItem) {

		NavMenuItem menuItem = (NavMenuItem) navDrawerItem;
		NavMenuItemHolder navMenuItemHolder = null;

		if (convertView == null) {
			convertView = inflater.inflate(R.layout.navdrawer_item, parentView,
					false);
			TextView labelView = (TextView) convertView
					.findViewById(R.id.navmenuitem_label);
            TextView countView = (TextView) convertView
                    .findViewById(R.id.counter);
            labelView.setTextAppearance(getContext(),conFig.getLabelTextStyle());
            countView.setTextAppearance(getContext(), conFig.getCountTextStyle());
			ImageView iconView = (ImageView) convertView
					.findViewById(R.id.navmenuitem_icon);

			navMenuItemHolder = new NavMenuItemHolder();
			navMenuItemHolder.labelView = labelView;
			navMenuItemHolder.iconView = iconView;
            navMenuItemHolder.countView = countView;
			convertView.setTag(navMenuItemHolder);
		}

		if (navMenuItemHolder == null) {
			navMenuItemHolder = (NavMenuItemHolder) convertView.getTag();
		}

        if(menuItem.isCounterVisible())
        navMenuItemHolder.countView.setText(menuItem.getCountValue());
        else
        navMenuItemHolder.countView.setVisibility(View.INVISIBLE);

		navMenuItemHolder.labelView.setText(menuItem.getLabel());
		navMenuItemHolder.iconView.setImageResource(menuItem.getIcon());

		return convertView;
	}

	/**
	 * Gets the section view.
	 * 
	 * @param convertView
	 *            the convert view
	 * @param parentView
	 *            the parent view
	 * @param navDrawerItem
	 *            the nav drawer item
	 * @return the section view
	 */
	public View getSectionView(View convertView, ViewGroup parentView,
			NavDrawerItem navDrawerItem) {

        NavMenuItem menuSection = (NavMenuItem) navDrawerItem;
		NavMenuSectionHolder navMenuItemHolder = null;

		if (convertView == null) {
			convertView = inflater.inflate(R.layout.section, parentView, false);
			TextView labelView = (TextView) convertView
					.findViewById(R.id.navmenusection_label);

            labelView.setTextAppearance(getContext(),conFig.getSectionHeaderStyle());
			navMenuItemHolder = new NavMenuSectionHolder();
			navMenuItemHolder.labelView = labelView;
			convertView.setTag(navMenuItemHolder);
		}

		if (navMenuItemHolder == null) {
			navMenuItemHolder = (NavMenuSectionHolder) convertView.getTag();
		}

		navMenuItemHolder.labelView.setText(menuSection.getLabel());
		return convertView;
	}

//	@Override
//	public int getViewTypeCount() {
//		return 2;
//	}

	@Override
	public boolean isEnabled(int position) {
		return getItem(position).isEnabled();
	}

	/**
	 * The Class NavMenuItemHolder.
	 */
	private static class NavMenuItemHolder {

		/** The label view. */
		private TextView labelView;

		/** The icon view. */
		private ImageView iconView;

        private TextView countView;
	}

	/**
	 * The Class NavMenuSectionHolder.
	 */
	private class NavMenuSectionHolder {

		/** The label view. */
		private TextView labelView;
	}
}