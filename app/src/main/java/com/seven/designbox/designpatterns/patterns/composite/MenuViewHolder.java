package com.seven.designbox.designpatterns.patterns.composite;

import com.seven.treelist.model.TreeNode;
import com.seven.designbox.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class MenuViewHolder extends TreeNode.BaseNodeViewHolder<MenuViewHolder.MenuItem> {

    private ImageView iconView;

    public MenuViewHolder(Context context) {
        super(context);
    }

    @Override
    public View createNodeView(TreeNode node, MenuItem menuItem) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(R.layout.layout_menu_node, null, false);

        iconView = (ImageView) view.findViewById(R.id.item_lable);
        iconView.setImageResource(menuItem.icon);

        MenuComponent item = menuItem.item;

        TextView itemName = (TextView) view.findViewById(R.id.item_name);
        itemName.setText(item.getName());

        TextView itemDescription = (TextView) view.findViewById(R.id.item_description);
        itemDescription.setText(item.getDescription());


        TextView itemPrice = (TextView) view.findViewById(R.id.item_price);
        String price = String.format(context.getString(R.string.item_price), item.getPrice());
        itemPrice.setText(price);

        return view;
    }

    @Override
    public void toggle(boolean active) {
    }


    public static class MenuItem {
        public int icon;
        public MenuComponent item;

        public MenuItem(int icon, MenuComponent item) {
            this.icon = icon;
            this.item = item;
        }
    }

}
