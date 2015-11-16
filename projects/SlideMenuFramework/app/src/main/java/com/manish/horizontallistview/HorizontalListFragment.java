package com.manish.horizontallistview;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.manish.helper.HelperJava;
import com.example.slidemenuframework.R;

/**
 * Created by manish on 1/15/2015.
 */
public class HorizontalListFragment extends Fragment {

    HorizontalListView listView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.horizontallistactivity, container, false);
        listView = (HorizontalListView) rootView.findViewById(R.id.listviewlocal);
        listView.setAdapter(new ImageAdapter());
        return  rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private static int[] dataObjects = new int[] {
            // Heavy images
           R.drawable.ic_stub,R.drawable.ic_stub,R.drawable.ic_stub,R.drawable.ic_stub,R.drawable.ic_stub,R.drawable.ic_stub
            ,R.drawable.ic_stub
            ,R.drawable.ic_stub
            ,R.drawable.ic_stub
    };

    private class ImageAdapter extends BaseAdapter {

        private LayoutInflater inflater;

        ImageAdapter() {
            inflater = LayoutInflater.from(getActivity());
        }

//        private OnClickListener mOnButtonClicked = new OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//                builder.setMessage("hello from " + v);
//                builder.setPositiveButton("Cool", null);
//                builder.show();
//
//            }
//        };

        @Override
        public int getCount() {
            return dataObjects.length;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            final ViewHolder holder;
            View view = convertView;
            if (view == null) {
                view = inflater.inflate(R.layout.viewitem, parent, false);
                holder = new ViewHolder();
                holder.imageView = (ImageView) view.findViewById(R.id.image);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }

            Bitmap bim=((BitmapDrawable)getResources().getDrawable(dataObjects[position])).getBitmap();

            holder.imageView.setImageBitmap(HelperJava.getRoundedShape(bim,getActivity(),90,100));
            return view;
        }
    }

    static class ViewHolder {
        ImageView imageView;
    }

}
