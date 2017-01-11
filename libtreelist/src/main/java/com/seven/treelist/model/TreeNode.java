package com.seven.treelist.model;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.seven.treelist.R;
import com.seven.treelist.view.AndroidTreeView;
import com.seven.treelist.view.TreeNodeWrapperView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TreeNode {
    public static final String NODES_ID_SEPARATOR = ":";

    private int mId;
    private int mLastId;
    private TreeNode mParent;
    private final List<TreeNode> children;
    private BaseNodeViewHolder mViewHolder;
    private TreeNodeClickListener mClickListener;
    private TreeNodeLongClickListener mLongClickListener;
    private Object mValue;
    private boolean mExpanded;

    public static TreeNode root() {
        TreeNode root = new TreeNode(null);
        return root;
    }

    private int generateId() {
        return ++mLastId;
    }

    public TreeNode(Object value) {
        children = new ArrayList<>();
        mValue = value;
    }

    public TreeNode addChild(TreeNode childNode) {
        childNode.mParent = this;
        childNode.mId = generateId();
        children.add(childNode);
        return this;
    }

    public List<TreeNode> getChildren() {
        return Collections.unmodifiableList(children);
    }

    public int size() {
        return children.size();
    }

    public TreeNode getParent() {
        return mParent;
    }

    public int getId() {
        return mId;
    }

    public boolean isLeaf() {
        return size() == 0;
    }

    public Object getValue() {
        return mValue;
    }

    public boolean isExpanded() {
        return mExpanded;
    }

    public TreeNode setExpanded(boolean expanded) {
        mExpanded = expanded;
        return this;
    }

    public String getPath() {
        final StringBuilder path = new StringBuilder();
        TreeNode node = this;
        while (node.mParent != null) {
            path.append(node.getId());
            node = node.mParent;
            if (node.mParent != null) {
                path.append(NODES_ID_SEPARATOR);
            }
        }
        return path.toString();
    }


    public TreeNode setViewHolder(BaseNodeViewHolder viewHolder) {
        mViewHolder = viewHolder;
        if (viewHolder != null) {
            viewHolder.mNode = this;
        }
        return this;
    }

    public TreeNode setClickListener(TreeNodeClickListener listener) {
        mClickListener = listener;
        return this;
    }

    public TreeNode setLongClickListener(TreeNodeLongClickListener listener) {
        mLongClickListener = listener;
        return this;
    }

    public TreeNodeClickListener getClickListener() {
        return this.mClickListener;
    }

    public TreeNodeLongClickListener getLongClickListener() {
        return mLongClickListener;
    }

    public BaseNodeViewHolder getViewHolder() {
        return mViewHolder;
    }

    public interface TreeNodeClickListener {
        void onClick(TreeNode node, Object value);
    }

    public interface TreeNodeLongClickListener {
        boolean onLongClick(TreeNode node, Object value);
    }

    public static abstract class BaseNodeViewHolder<E> {
        protected AndroidTreeView tView;
        protected TreeNode mNode;
        private View mView;
        protected int containerStyle;
        protected Context context;

        public BaseNodeViewHolder(Context context) {
            this.context = context;
        }

        public View getView() {
            if (mView != null) {
                return mView;
            }
            final View nodeView = getNodeView();
            final TreeNodeWrapperView nodeWrapperView = new TreeNodeWrapperView(nodeView.getContext(), getContainerStyle());
            nodeWrapperView.insertNodeView(nodeView);
            mView = nodeWrapperView;

            return mView;
        }

        public void setTreeViev(AndroidTreeView treeViev) {
            this.tView = treeViev;
        }

        public AndroidTreeView getTreeView() {
            return tView;
        }

        public void setContainerStyle(int style) {
            containerStyle = style;
        }

        public View getNodeView() {
            return createNodeView(mNode, (E) mNode.getValue());
        }

        public ViewGroup getNodeItemsView() {
            return (ViewGroup) getView().findViewById(R.id.node_items);
        }

        public int getContainerStyle() {
            return containerStyle;
        }


        public abstract View createNodeView(TreeNode node, E value);

        public void toggle(boolean active) {
            // empty
        }

    }
}
