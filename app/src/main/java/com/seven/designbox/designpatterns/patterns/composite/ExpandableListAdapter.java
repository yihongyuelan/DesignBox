package com.seven.designbox.designpatterns.patterns.composite;

import com.seven.designbox.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ExpandableListAdapter extends BaseExpandableListAdapter {
    private LayoutInflater mLayoutInflater;
    private List<MenuComponent> mGroup;

    public ExpandableListAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getGroupCount() {
        return 2;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 5;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return "Group";
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return "Child content";
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewHolderGroup groupView;
        if (convertView == null) {
            groupView = new ViewHolderGroup();
            convertView = mLayoutInflater.inflate(R.layout.patterns_composite_group, null);
            groupView.titleTv = (TextView) convertView.findViewById(R.id.title);
            groupView.arrow = (ImageView) convertView.findViewById(R.id.arrow);
            convertView.setTag(groupView);
        } else {
            groupView = (ViewHolderGroup) convertView.getTag();
        }
        groupView.titleTv.setText(getGroup(groupPosition).toString());

        if (isExpanded) {
            // Down arrow
            groupView.arrow.setImageResource(R.mipmap.down_arrow);
        } else {
            // Right arrow
            groupView.arrow.setImageResource(R.mipmap.right_arrow);
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolderChild childView;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.patterns_composite_child, null);
            childView = new ViewHolderChild();
            childView.contentTv = (TextView) convertView.findViewById(R.id.content);
            convertView.setTag(childView);
        } else {
            childView = (ViewHolderChild) convertView.getTag();
        }
        childView.contentTv.setText(getChild(groupPosition, childPosition).toString());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    class ViewHolderGroup {
        TextView titleTv;
        ImageView arrow;
    }

    class ViewHolderChild {
        TextView contentTv;
    }
}
