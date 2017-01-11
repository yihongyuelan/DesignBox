package com.seven.designbox.designpatterns.patterns.composite;


import com.seven.designbox.R;
import com.seven.treelist.model.TreeNode;
import com.seven.treelist.view.AndroidTreeView;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CompositeDemoFragment extends Fragment {
    private AndroidTreeView tView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.patterns_composite_container, null, false);
        final ViewGroup containerView = (ViewGroup) rootView.findViewById(R.id.container);

        final TreeNode root = TreeNode.root();

        Waitress mWaitress = new Waitress();
        MenuComponent mAllMenus = mWaitress.getAllMenus();

        MenuTreeItemHolder.MenuTreeItem item = new MenuTreeItemHolder.MenuTreeItem(R.drawable.menu_black, mAllMenus.getName());
        TreeNode data = new TreeNode(item);
        MenuHeaderHolder holder = new MenuHeaderHolder(getActivity());
        data.setViewHolder(holder);
        fillFolder(data, mAllMenus);
        root.addChild(data);


        tView = new AndroidTreeView(getActivity(), root);
        //tView.setDefaultAnimation(true);
        tView.setDefaultContainerStyle();
        containerView.addView(tView.getView());

        if (savedInstanceState != null) {
            String state = savedInstanceState.getString("tState");
            if (!TextUtils.isEmpty(state)) {
                tView.restoreState(state);
            }
        }

        return rootView;
    }

    private void fillFolder(TreeNode folder, MenuComponent menus) {
        TreeNode currentNode = folder;
        for (int i = 0; i < menus.getCounts(); i++) {
            MenuComponent menu = menus.getChild(i);
            int counts = menu.getCounts();
            int icon = counts > 0 ? R.drawable.menu_black : R.drawable.menu_item_black;
            TreeNode file = counts > 0 ?
                    new TreeNode(new MenuTreeItemHolder.MenuTreeItem(icon, menu.getName())).setViewHolder(new MenuHeaderHolder(getActivity()))
                    :
                    new TreeNode(new MenuViewHolder.MenuItem(icon, menu)).setViewHolder(new MenuViewHolder(getActivity()));
            fillFolder(file, menu);
            currentNode.addChild(file);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("tState", tView.getSaveState());
    }
}
