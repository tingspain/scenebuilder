package nodebox.pixie;

import nodebox.node.Context;
import nodebox.node.ImagePort;
import nodebox.node.Node;
import nodebox.node.Port;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PImage;

public abstract class PointFilter extends ImageNode {

    public final ImagePort pImage = new ImagePort(this, "inputImage", Port.Direction.INPUT);

    public PointFilter() {
        pImage.setDisplayName("image");
    }

    @Override
    public PImage cook(Context context, float time) {
        PImage in = pImage.get();
        if (in == null) return null;
        in.loadPixels();
        PImage out = context.getApplet().createImage(in.width, in.height, PConstants.RGB);
        out.loadPixels();
        int width = in.width;
        int height = in.height;
        int i, v;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                i = y * width + x;
                v = in.pixels[i];
                out.pixels[i] = filter(x, y, v);
            }
        }
        out.updatePixels();
        return out;
    }

    @Override
    public void draw(PGraphics g, Context context, float time) {
        g.image(pOutput.get(), 0, 0);
    }

    public abstract int filter(int x, int y, int v);

    public int alpha(int v) {
        return v >> 24 & 0xFF;
    }

    public int red(int v) {
        return v >> 16 & 0xFF;
    }

    public int green(int v) {
        return v >> 8 & 0xFF;
    }

    public int blue(int v) {
        return v & 0xFF;
    }

    public int toColorValue(int red, int green, int blue, int alpha) {
        red = red > 255 ? 255 : red < 0 ? 0 : red;
        green = green > 255 ? 255 : green < 0 ? 0 : green;
        blue = blue > 255 ? 255 : blue < 0 ? 0 : blue;
        alpha = alpha > 255 ? 255 : alpha < 0 ? 0 : alpha;
        return alpha << 24 | red << 16 | green << 8 | blue;
    }

}
