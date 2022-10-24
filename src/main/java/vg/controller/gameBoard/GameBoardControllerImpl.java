package vg.controller.gameBoard;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Dimension2D;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polyline;
import vg.model.MapImpl;
import vg.model.entity.ShapedEntity;
import vg.model.entity.dynamicEntity.DynamicEntity;
import vg.utils.V2D;
import vg.view.ViewController;
import vg.view.entity.EntityBlock;
import vg.view.entity.EntityBlockImpl;
import vg.view.entity.StaticFactoryEntityBlock;
import vg.view.player.PlayerViewController;
import vg.view.player.PlayerViewControllerImpl;
import vg.view.utils.Colors;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GameBoardControllerImpl extends ViewController implements GameBoardController {

    @FXML
    private BorderPane borderPane;

    @FXML
    public Label numberRound;
    @FXML
    public Label scoreText;
    @FXML
    public Label highScoreText;
    @FXML
    public Label shield;
    @FXML
    public Label percentage;
    @FXML
    public Button life1;
    @FXML
    public Button life2;
    @FXML
    public Button life3;
    @FXML
    public Button life4;
    @FXML
    public Button life5;
    @FXML
    public Button life6;

    private PlayerViewController player;
    private Polyline tailPolyline;
    private Polyline borders;
    private EntityBlock boss;
    private Set<Node> mosquitoesNode;
    private Set<EntityBlock> mosqs;
    private int prevLife;

    @FXML
    private Pane gameArea;

    @Override
    public Dimension2D getGameAreaDimension() {
        return new Dimension2D(gameArea.getWidth(), gameArea.getHeight());
    }

    @Override
    public Pane getGameArea() {
        return this.gameArea;
    }

    @Override
    public void addInGameArea(Node node) {
        this.gameArea.getChildren().add(node);
    }

    @Override
    public ObservableList<Node> getGameAreaNode() {
        return this.gameArea.getChildren();
    }

    @Override
    public void initMapView() {
        //Set player in view map
        this.player = new PlayerViewControllerImpl();
        this.player.setInParentNode(this.getGameAreaNode());

        //TODO create an appropriate controller for boss?
       // this.boss = StaticFactoryEntityBlock.createBoss(new V2D(0, 0), modelRadiusToDimension2D(5));
      //  this.boss.setInParentNode(this.getGameAreaNode());
        this.mosqs = new HashSet<>();
//        this.addInGameArea(this.boss.getNode());

        this.mosquitoesNode = new HashSet<>();
        this.tailPolyline = new Polyline();

    }

    @Override
    public void updateMosquitoesPosition(final Set<DynamicEntity> mosquitoes) {

       // this.gameArea.getChildren().removeAll(mosquitoesNode);
        this.gameArea.getChildren().removeAll(mosqs);
        this.mosqs.clear();

        mosquitoes.forEach(m -> {
            EntityBlock entityBlock = StaticFactoryEntityBlock.createMosquitoes(mapCoordinateToViewSize(m.getPosition()), modelRadiusToDimension2D(m.getRadius()));
            entityBlock.setInParentNode(this.getGameAreaNode());
            this.mosqs.add(entityBlock);
            //this.mosquitoesNode.add(entityBlock.getNode());
            //this.addInGameArea(entityBlock.getNode());
        });
    }

    @Override
    public void updateBossPosition(final V2D bossPos) {
        /*((EntityBlockImpl)this.boss).setDisable(true);
        this.getGameArea().getChildren().removeIf(Node::isDisable);
        this.boss = StaticFactoryEntityBlock.createBoss(bossPos, modelRadiusToDimension2D(5));
        //((EntityBlockImpl)this.boss).setDisable(false);
        this.boss.setInParentNode(this.getGameAreaNode());
    */
    }

    @Override
    public void updateBorders(final List<V2D> vertexBorder) {
        this.gameArea.getChildren().remove(this.borders);
        this.borders = new Polyline();
        this.borders.getPoints()
                .setAll(convertToListOfDouble(vertexBorder.stream()
                                                        .map(this::mapCoordinateToViewSize)
                                                        .collect(Collectors.toList())));
        this.borders.setStrokeWidth(2.5);
        this.borders.setStroke(Colors.BORDER);
        this.addInGameArea(this.borders);
    }

    @Override
    public void updatePlayer(final V2D position, final boolean shieldActive, final List<V2D> tailVec) {
        if (shieldActive) {
            this.player.showShield();
        } else {
            this.player.hideShield();
        }
        this.player.setPosition(mapCoordinateToViewSize(position));

        this.gameArea.getChildren().remove(this.tailPolyline);
        tailVec.add(position);
        this.tailPolyline = new Polyline();
        List<V2D> mappedTail = tailVec.stream().map(this::mapCoordinateToViewSize).collect(Collectors.toList());
        this.tailPolyline.getPoints().setAll(convertToListOfDouble(mappedTail));
        this.tailPolyline.setStrokeWidth(2.5);
        this.tailPolyline.setStroke(Colors.TAIL);
        this.addInGameArea(this.tailPolyline);
    }

    @Override
    public void updateLifeCounter(final int life) {
        if (prevLife != life) {
            prevLife = life;
            this.life1.setDisable(true);
            this.life2.setDisable(true);
            this.life3.setDisable(true);
            this.life4.setDisable(true);
            this.life5.setDisable(true);
            this.life6.setDisable(true);
            switch (life) {
                case 6: this.life1.setDisable(false);
                case 5: this.life2.setDisable(false);
                case 4: this.life3.setDisable(false);
                case 3: this.life4.setDisable(false);
                case 2: this.life5.setDisable(false);
                case 1: this.life6.setDisable(false);
            }
        }
    }

    @Override
    public void updatePercentage(final double percentage) {
        int perc = (int)(percentage*100);
        this.percentage.setText(String.valueOf(perc));
    }

    @Override
    public void setRound(final int round) {
        String roundStr = String.valueOf(round);
        this.numberRound.setText(roundStr);
    }

    @Override
    public void updateScoreText(final int score) {
        this.scoreText.setText(String.valueOf(score));
    }

    @Override
    public void setHighScoreText(final int highScore) {
        this.highScoreText.setText(String.valueOf(highScore));
    }

    @Override
    public void updateShieldTime(final double time) {
        this.shield.setText(String.valueOf((int)time/100));
    }

    /**
     * COnvert List of vectors V2D to a list with alternate x and y.
     * @param vectors List of vertex vector of player tail
     * @return List of double
     */
    private List<Double> convertToListOfDouble(final List<V2D> vectors) {
        return vectors.stream()
                .map(vec -> List.of(vec.getX(), vec.getY()))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    /**
     * Converts from model dimension into JavaFX {@link Dimension2D}.
     * @param v2d the model position
     * @return {@link Dimension2D} that correctly scales from the model position into {@link #gameArea}
     */
    public Dimension2D V2DtoDimension2D(final V2D v2d){
        return new Dimension2D(v2d.getX() * getGameArea().getWidth()/ MapImpl.MAXBORDERX,
                v2d.getY() * getGameArea().getHeight()/ MapImpl.MAXBORDERY);
    }

    /**
     * Scale passed position to the mapped position on screen.
     * @param modelV2D the model position
     * @return {@link V2D} mapped position
     */
    public V2D mapCoordinateToViewSize(final V2D modelV2D) {
        Dimension2D mapped = V2DtoDimension2D(modelV2D);
        return new V2D(mapped.getWidth(), mapped.getHeight());
    }

    /**
     * Converts from model radius into JavaFX {@link Dimension2D}.
     * @param radius the model {@link ShapedEntity#getRadius()}
     * @return {@link Dimension2D} that correctly scales from the model into {@link #gameArea}
     */
    public Dimension2D modelRadiusToDimension2D(final int radius){
        return new Dimension2D(radius * getGameArea().getWidth()/ MapImpl.MAXBORDERX,
                radius * getGameArea().getWidth()/ MapImpl.MAXBORDERX);
    }

    /**
     * Converts from model {@link ShapedEntity#getRadius()} into JavaFX {@link Dimension2D}.
     * @param entity the model {@link ShapedEntity#getRadius()}
     * @return {@link Dimension2D} that correctly scales from the model into {@link #gameArea}
     */
    public Dimension2D modelRadiusToDimension2D(final ShapedEntity entity){
        return new Dimension2D(entity.getRadius() * getGameArea().getWidth()/ MapImpl.MAXBORDERX,
                entity.getRadius() * getGameArea().getWidth()/ MapImpl.MAXBORDERX);
    }
}
