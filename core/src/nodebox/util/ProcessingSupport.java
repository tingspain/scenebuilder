package nodebox.util;

import nodebox.node.ColorPort;
import nodebox.node.FloatPort;
import processing.core.PApplet;

import java.awt.*;
import java.util.Random;

public class ProcessingSupport {

    /**
     * Constrain a value between the given minimum and maximum.
     * @param v the value to clamp
     * @param min the minimum value
     * @param max the maximum value
     * @return a value between the given minimum and maximum.
     */
    public static float clamp(float v, float min, float max) {
        return Math.max(min, Math.min(max, v));
    }

    /**
     * Constrain a value between 0.0 and 1.0.
     * @param v the value to clamp
     * @return a value between 0.0 and 1.0.
     */
    public static float clamp(float v) {
        return Math.max(0f, Math.min(1f, v));
    }

    public static final float TO_RADIANS = 0.0174532925f;

    /**
     * Set the fill color. If the color is null, call noFill().
     *
     * @param p the processing context
     * @param c the color
     */
    public static void setFill(PApplet p, Color c) {
        if (c != null) {
            p.fill(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha());
        } else {
            p.noFill();
        }
    }

    /**
     * Set the fill color from a color port. If the port is null, call noFill().
     *
     * @param p    the processing context
     * @param port the fill color port
     */
    public static void setFill(PApplet p, ColorPort port) {
        Color fill = port != null ? port.get() : null;
        setFill(p, fill);
    }

    /**
     * Set the stroke color. If the color is null, call noFill().
     *
     * @param p the processing context
     * @param c the color
     */
    public static void setStroke(PApplet p, Color c) {
        if (c != null) {
            p.stroke(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha());
        } else {
            p.noStroke();
        }
    }

    /**
     * Set the stroke color from a color port. If the port is null, call noStroke().
     *
     * @param p    the processing context
     * @param port the stroke color port
     */
    public static void setStroke(PApplet p, ColorPort port) {
        Color stroke = port != null ? port.get() : null;
        setStroke(p, stroke);
    }

    /**
     * Set the drawing style as a combination of fill, stroke and stroke weight.
     * <p/>
     * This method supports null values for all arguments, disabling the style.
     * E.g. if the fill is null, noFill() will be called.
     *
     * @param p            the processing applet
     * @param fill         the fill color
     * @param stroke       the stroke color
     * @param strokeWeight the stroke weight
     */
    public static void setStyle(PApplet p, Color fill, Color stroke, float strokeWeight) {
        if (fill != null) {
            setFill(p, fill);
        } else {
            p.noFill();
        }
        if (stroke != null) {
            setStroke(p, stroke);
            p.strokeWeight(Math.max(strokeWeight, 0f));
        } else {
            p.noStroke();
        }
    }

    /**
     * Set the drawing style based on a number of common ports.
     * <p/>
     * This method support null values for all of the arguments, disabling the style.
     * E.g. if the fillPort is null, noFill() will be called.
     *
     * @param p                the processing applet
     * @param fillPort         the port that contains the fill color
     * @param strokePort       the port that contains the stroke color
     * @param strokeWeightPort the port that contains the stroke weight
     */
    public static void setStyle(PApplet p, ColorPort fillPort, ColorPort strokePort, FloatPort strokeWeightPort) {
        Color fill = fillPort != null ? fillPort.get() : null;
        Color stroke = strokePort != null ? strokePort.get() : null;
        float strokeWeight = strokeWeightPort != null ? strokeWeightPort.get() : 1f;
        setStyle(p, fill, stroke, strokeWeight);
    }

    public static float random(float min, float max, int seed) {
        Random random = new Random(seed * 10000);
        return min + random.nextFloat() * (max - min);
    }

}