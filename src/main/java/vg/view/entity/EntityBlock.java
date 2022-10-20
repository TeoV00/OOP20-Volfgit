package vg.view.entity;

import javafx.collections.ObservableList;
import javafx.geometry.Dimension2D;
import javafx.scene.Node;
import vg.utils.V2D;

public interface EntityBlock {


    public V2D getPosition();

    public void setPosition(V2D position);
    public void setImage(String pathImage);
    public void setInParentNode(ObservableList<Node> gameAreaNode);
    public void setDimensionImageOverlay(Dimension2D dimension);
    public void setImageOverlay(String pathImage);

    public void showImageOverlay();
    public void hideImageOverlay();

}
