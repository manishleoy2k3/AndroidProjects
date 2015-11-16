package com.manish.navigationdrawer.view.fragments;

//import android.app.ListFragment;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import com.manish.helper.ConnectionDetector;
import com.manish.navigationdrawer.adapters.ListItemAdapter;
import com.manish.navigationdrawer.items.ListItem;
import com.manish.view.activity.AsyncTaskFetchDataFromServer;
import com.manish.view.activity.onDataFromServerListener;
import com.example.slidemenuframework.R;
import java.util.ArrayList;

/**
 * Created by Manish on 1/5/2015.
 */
public abstract class CustomListFragment extends ListFragment implements onDataFromServerListener {

    /**Method for deleting item from the list*/
    protected abstract void deleteItem(int id);

    /**Method called when internet connection failed  while connecting to sever*/
    protected abstract void onInternetConnectionFailed();

    ArrayList<ListItem> itemList;
    private ListItemAdapter adapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        itemList=new ArrayList<ListItem>();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getListView().setChoiceMode(1);//setting choice mode to CHOICE_MODE_SINGLE with constant 1//
        getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                getActivity().startActionMode(mCallBack);
                getListView().setItemChecked(position,true);
                view.setSelected(true);
                adapter.currentSelection = position;
                return true;
            }
        });
    }

    private ActionMode.Callback mCallBack=new ActionMode.Callback(){

        public boolean onPrepareActionMode(ActionMode mode, Menu menu){
            return false;
        }

        public void onDestroyActionMode(ActionMode mode) {
            mode = null;
            getListView().setItemChecked(adapter.currentSelection,false);
        }

        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.setTitle("Options");
            mode.getMenuInflater().inflate(R.menu.list_menu, menu);
            return true;
        }

        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            int id=item.getItemId();
            switch(id)
            {
                case R.id.action_delete:
                    adapter.remove(adapter.getItem(adapter.currentSelection));
                    mode.finish();
                    return true;
                default:
                    return false;
            }
        }
    };

    /** Calling async task to download data from the server */
    public void fetchDataFromServerAsync(String url,String urlencodedParameters)
    {
        ConnectionDetector cd=new ConnectionDetector(getActivity());
        if(cd.isConnectingToInternet())
        {
            setListShown(false);    //setting list shown false because without this progress bar will not be shown//
            AsyncTaskFetchDataFromServer task=new AsyncTaskFetchDataFromServer(getActivity(),false,url,urlencodedParameters);
            task.delegate=this;
            task.execute();
        }
        else
        {
            setEmptyText(getResources().getString(R.string.internetnotprsent));
            onInternetConnectionFailed();
        }
    }

    /**setting data to the custom adapter of listview*/
    public void setData()
    {
        if(itemList!=null)
        {
            adapter=new ListItemAdapter(getActivity(), R.layout.navdrawer_item,itemList);
            setListAdapter(adapter);
        }
        else
        {
            setEmptyText("No Data");
        }
    }


    @Override
    public void setEmptyText(CharSequence text) {
        setListShown(true);  //setting list shown true because without this text will not be shown//
        super.setEmptyText(text);
    }
}
