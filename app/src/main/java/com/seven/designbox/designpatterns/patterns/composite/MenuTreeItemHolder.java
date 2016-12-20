package com.seven.designbox.designpatterns.patterns.composite;

import com.seven.treelist.model.TreeNode;
import com.seven.designbox.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MenuTreeItemHolder extends TreeNode.BaseNodeViewHolder<MenuTreeItemHolder.MenuTreeItem> {
    private TextView tvValue;
    private ImageView arrowView;

    public MenuTreeItemHolder(Context context) {
        super(context);
    }

    @Override
    public View createNodeView(final TreeNode node, MenuTreeItem value) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(R.layout.layout_menu_header, null, false);
        tvValue = (TextView) view.findViewById(R.id.node_value);
        tvValue.setText(value.text);
        final ImageView iconView = (ImageView) view.findViewById(R.id.icon);
        iconView.setImageResource(value.icon);
        arrowView = (ImageView) view.findViewById(R.id.arrow_icon);
        return view;
    }

    @Override
    public void toggle(boolean active) {
        arrowView.setImageResource(active ? R.drawable.ic_arrow_down_black : R.drawable.ic_arrow_right_black);
    }

    public static class MenuTreeItem {
        public int icon;
        public String text;

        public MenuTreeItem(int icon, String text) {
            this.icon = icon;
            this.text = text;
        }
    }
}
