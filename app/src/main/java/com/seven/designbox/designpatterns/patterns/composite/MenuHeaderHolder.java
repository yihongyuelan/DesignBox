package com.seven.designbox.designpatterns.patterns.composite;

import com.seven.designbox.R;
import com.seven.treelist.model.TreeNode;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MenuHeaderHolder extends TreeNode.BaseNodeViewHolder<MenuTreeItemHolder.MenuTreeItem> {
    private TextView tvValue;
    private ImageView arrowView;

    public MenuHeaderHolder(Context context) {
        super(context);
    }

    @Override
    public View createNodeView(final TreeNode node, MenuTreeItemHolder.MenuTreeItem value) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(R.layout.layout_menu_header, null, false);

        tvValue = (TextView) view.findViewById(R.id.node_value);
        tvValue.setText(value.text);

        final ImageView iconView = (ImageView) view.findViewById(R.id.icon);
        iconView.setImageResource(value.icon);

        arrowView = (ImageView) view.findViewById(R.id.arrow_icon);
        if (node.isLeaf()) {
            arrowView.setVisibility(View.GONE);
        }

        return view;
    }

    @Override
    public void toggle(boolean active) {
        arrowView.setImageResource(active ? R.drawable.ic_arrow_down_black : R.drawable.ic_arrow_right_black);
    }

}
