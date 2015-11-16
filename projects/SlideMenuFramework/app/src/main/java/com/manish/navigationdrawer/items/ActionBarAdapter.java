package com.manish.navigationdrawer.items;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.slidemenuframework.R;

/**
 * The Class NavDrawerAdapter.
 */
public class ActionBarAdapter extends ArrayAdapter<ActionBarItems> {

	/** The inflater. */
	private LayoutInflater inflater;

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
	public ActionBarAdapter(Context context, int textViewResourceId,
			ActionBarItems[] objects) {
		super(context, textViewResourceId, objects);
		this.inflater = LayoutInflater.from(context);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = null;
		ActionBarItems menuItem = this.getItem(position);
		if (menuItem.getType() == ActionBarItems.ITEM_TYPE) {
			view = getItemView(convertView, parent, menuItem);
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
	 * @param actionListItems
	 *            the nav drawer item
	 * @return the item view
	 */
	public View getItemView(View convertView, ViewGroup parentView,
			ActionBarItems actionListItems) {

		ActionBarItems menuItem = (ActionBarItems) actionListItems;
		ActionItemHolder navMenuItemHolder = null;

		if (convertView == null) {
			convertView = inflater.inflate(R.layout.action_items, parentView,
					false);
			TextView labelView = (TextView) convertView
					.findViewById(R.id.actionitem_label);

			navMenuItemHolder = new ActionItemHolder();
			navMenuItemHolder.labelView = labelView;

			convertView.setTag(navMenuItemHolder);
		}

		if (navMenuItemHolder == null) {
			navMenuItemHolder = (ActionItemHolder) convertView.getTag();
		}

		navMenuItemHolder.labelView.setText(menuItem.getLabel());

		return convertView;
	}

	@Override
	public int getViewTypeCount() {
		return 2;
	}

	@Override
	public int getItemViewType(int position) {
		return this.getItem(position).getType();
	}

	@Override
	public boolean isEnabled(int position) {
		return getItem(position).isEnabled();
	}

	/**
	 * The Class NavMenuItemHolder.
	 */
	private static class ActionItemHolder {

		/** The label view. */
		private TextView labelView;

	}

	/**
	 * The Class NavMenuSectionHolder.
	 */

}