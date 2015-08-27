package com.emdsys.android.jsonapplication.JsonExpandableListViewVolley;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.emdsys.android.jsonapplication.R;

import java.util.ArrayList;

/**
 * Created by EMD029 on 8/26/2015.
 */
public class ExpandableListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private ArrayList<Group> groupLists;

    ImageLoader imageLoader=MyApplication.getInstance().getImageLoader();

    public ExpandableListAdapter(Context context,ArrayList<Group> groupLists){
        this.context=context;
        this.groupLists=groupLists;
    }

    @Override
    public int getGroupCount() {
        return groupLists.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        ArrayList<Child> childLists=groupLists.get(groupPosition).getItems();
        return childLists.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupLists.get(groupPosition);
    }
//Declaring array list for child
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        ArrayList<Child> childlists=groupLists.get(groupPosition).getItems();
        return childlists.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }
//Getting Child Id
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        Group group= (Group) getGroup(groupPosition);
        if (convertView==null){
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.group_item_expandable,null);
        }
        TextView textView= (TextView) convertView.findViewById(R.id.group_name);
        textView.setText(group.getName());
        return convertView;
    }
//set action for child itemsf
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        Child child= (Child) getChild(groupPosition,childPosition);
        if (convertView==null){
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.child_item_expandable,null);
        }
        if (imageLoader==null){
            imageLoader=MyApplication.getInstance().getImageLoader();
        }
        TextView movieName= (TextView) convertView.findViewById(R.id.Expandable_movieName);
        NetworkImageView movieImage= (NetworkImageView) convertView.findViewById(R.id.Expandable_movieImage);

        movieName.setText(child.getName().toString());
        movieImage.setImageUrl(child.getThumbnailUrl(),imageLoader);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
