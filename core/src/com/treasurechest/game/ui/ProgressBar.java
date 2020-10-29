package com.treasurechest.game.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Scaling;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class ProgressBar extends Group {
    public enum FillType{
        LEFT_TO_RIGHT, BOTTOM_TO_TOP,
        RIGHT_TO_LEFT, TOP_TO_BOTTOM;
    }

    public interface ProgressBarListener{
        void onPercentChanged(float newPercent);
    }

    private final Texture       bg;
    private final Texture       fill;
    private final TextureRegion regionFill;

    private Array<ProgressBarListener> listeners = new Array<>();
    private DecimalFormat decimalFormatter = new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.US));
    private FillType fillType = FillType.LEFT_TO_RIGHT;

    private float percent           = 0;
    private float smoothPercent     = 0;
    private float labelOffSetY      = .5f;
    private float labelOffSetX      = .5f;
    private float animationSpeed    = 3f;
    private boolean customText      = false;
    private boolean smoooth;
    private Label label;

    private Image bgImage;
    private Image fillImage;

    public ProgressBar(Texture bg, Texture fill) {
        this(bg, fill,null);
    }

    public ProgressBar(Texture bg, Texture fill, BitmapFont font) {
        this.bg = bg;
        this.fill = fill;

        regionFill = new TextureRegion(fill);

        initImages();
        initLabel(font);
    }

    @Override
    protected void sizeChanged() {
        super.sizeChanged();

        updateRegion();
        updateLabelPosition();
    }

    private void updateRegion() {
        float percent = Float.valueOf(decimalFormatter.format(smoooth?smoothPercent:getPercent()));

        int regionWidth = MathUtils.floor(getWidth());
        int regionHeight = MathUtils.floor(getHeight());
        float fillImageX = 0;
        float fillImageY = 0;

        switch (fillType){
            case LEFT_TO_RIGHT:
                regionWidth = MathUtils.floor(getWidth()* percent);
                regionFill.setRegion(0, 0, (int) (fill.getWidth() * percent), fill.getHeight());
                break;
            case BOTTOM_TO_TOP:
                regionHeight = MathUtils.floor(getHeight()* percent);
                regionFill.setRegion(0, (int)(fill.getHeight() * (1-percent)), fill.getWidth(), (int) (fill.getHeight() * percent));
                break;
            case RIGHT_TO_LEFT:
                regionWidth = MathUtils.floor(getWidth()* percent);
                fillImageX = getWidth()- getWidth()*percent;
                regionFill.setRegion((int)(fill.getWidth() *(1-percent)), 0, (int) (fill.getWidth() * percent), fill.getHeight());
                break;
            case TOP_TO_BOTTOM:
                regionHeight = MathUtils.floor(getHeight()* percent);
                fillImageY = getHeight() - getHeight()*percent;
                regionFill.setRegion(0, 0, fill.getWidth(), (int) (fill.getHeight() * percent));
                break;
        }

        fillImage.setSize(regionWidth,regionHeight);
        bgImage.setSize(getWidth(),getHeight());
        fillImage.setPosition(fillImageX,fillImageY);
    }

    private void updateLabelPosition() {
        if(label != null) {
            float x = getWidth() * labelOffSetX - label.getWidth()/2;
            float y = getHeight() * labelOffSetY - label.getHeight()/2;

            label.setPosition(x,y);
        }
    }

    private void initImages() {
        bgImage = new Image(bg);
        fillImage = new Image(regionFill);

        addActor(bgImage);
        addActor(fillImage);
    }

    private void initLabel(BitmapFont font) {
        if(font == null)
            return;

        Label.LabelStyle ls = new Label.LabelStyle(font,Color.BLACK);
        label = new Label(percent+" %",ls);
        label.setAlignment(Align.center);

        addActor(label);
    }

    private void calculateSmoothPercent() {
        if(percent > smoothPercent){
            smoothPercent+= animationSpeed * Gdx.graphics.getDeltaTime();
            if(smoothPercent > percent)
                smoothPercent = new Float(percent);
        }
        else{
            if(percent < smoothPercent){
                smoothPercent-= animationSpeed * Gdx.graphics.getDeltaTime();
                if(smoothPercent < percent)
                    smoothPercent = new Float(percent);
            }
        }
    }

    public void setFontColor(Color fontColor) {
        if(label == null)
            return;
        label.getStyle().fontColor = fontColor;
    }

    public void setFillType(FillType fillType) {
        this.fillType = fillType;
    }

    public void labelOffSetX(float offSetX) {
        labelOffSetX = offSetX;
        updateLabelPosition();
    }

    public void labelOffSetY(float offSetY) {
        labelOffSetY = offSetY;
        updateLabelPosition();
    }

    public float getPercent(){
        return percent;
    }

    public float getSmoothPercent() {
        return smoothPercent;
    }

    public void setPercent(float per){
        if(per > 1) per = 1;
        if(per < 0) per = 0;

        this.percent = per;

        percentChanged();
    }

    private void percentChanged() {
        calculateSmoothPercent();

        for(ProgressBarListener l: listeners)
            l.onPercentChanged(this.percent);

        if(label != null && !customText)
            label.setText((int)(percent*100)+"%");

        updateRegion();
    }

    public void setAnimationSpeed(float animationSpeed) {
        this.animationSpeed = animationSpeed;
    }

    public void setCustomText(String text) {
        label.setText(text);
        customText = true;
    }
    public void setTextColor(Color color){
        label.getStyle().fontColor = color;
    }

    public void addListener(ProgressBarListener listener){
        listeners.add(listener);
    }

    public void removeListener(ProgressBarListener listener) {
        listeners.removeValue(listener,false);
    }

    public void fillY(float height) {
        Vector2 size = Scaling.fillY.apply(bg.getWidth(),bg.getHeight(),0,height);
        setSize(size.x,size.y);
    }

    public void fillX(float width) {
        Vector2 size = Scaling.fillX.apply(bg.getWidth(),bg.getHeight(),width,0);
        setSize(size.x,size.y);
    }

    public void setSmoothPercent(boolean smooth) {
        this.smoooth = smooth;
    }

    public Image getBgImage() {
        return bgImage;
    }

    public Image getFillImage() {
        return fillImage;
    }

    public void sizeToFit(float width, float height) {
        boolean scaleToFitWidth = width != 0?true:false;
        Scaling scale = scaleToFitWidth? Scaling.fillX: Scaling.fillY;
        Vector2 size = scale.apply(bg.getWidth(),bg.getHeight(),width,height);
        setSize(size.x,size.y);
    }

    public void dispose(){
        fill.dispose();
        bg.dispose();
    }
}
