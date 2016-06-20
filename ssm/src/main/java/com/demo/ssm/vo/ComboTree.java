package com.demo.ssm.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/2/16.
 */
public class ComboTree {
    private String id;
    private String pid;
    private String text;
    private List<ComboTree> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<ComboTree> getChildren() {
        return children;
    }

    public void setChildren(List<ComboTree> children) {
        this.children = children;
    }

    public static List<ComboTree> formatTree(List<ComboTree> nodes, String id) {
        List<ComboTree> treeNodes = new ArrayList<ComboTree>();
        for (ComboTree node : nodes) {
            ComboTree comboTree = new ComboTree();
            comboTree.setId(node.getId());
            comboTree.setText(node.getText());
            if (id.equals(node.getPid())) {
                comboTree.setChildren(formatTree(nodes, node.getId()));
                treeNodes.add(comboTree);
            }
        }
        return treeNodes;
    }

    @Override
    public String toString() {
        return "ComboTree{" +
                "id='" + id + '\'' +
                ", text='" + text + '\'' +
                ", children=" + children +
                '}';
    }
}
