package meru.image;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import meru.sys.IOSystem;
import sun.awt.image.BufferedImageGraphicsConfig;

//import com.sun.image.codec.jpeg.JPEGCodec;
//import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ImageSystem {

  public static BufferedImage createCompatibleImage(BufferedImage image) {

    GraphicsConfiguration gc = BufferedImageGraphicsConfig.getConfig(image);

    BufferedImage result = gc.createCompatibleImage(image.getWidth(),
                                                    image.getHeight(),
                                                    Transparency.TRANSLUCENT);
    Graphics2D graphics = result.createGraphics();
    graphics.drawRenderedImage(image,
                               null);
    graphics.dispose();
    return result;
  }

  public static BufferedImage blur(BufferedImage image) {
    float ninth = 1.0f / 9.0f;
    float[] blurKernel = { ninth,
                          ninth,
                          ninth,
                          ninth,
                          ninth,
                          ninth,
                          ninth,
                          ninth,
                          ninth };

    Map<RenderingHints.Key, Object> hintsMap = new HashMap<RenderingHints.Key, Object>();

    hintsMap.put(RenderingHints.KEY_INTERPOLATION,
                 RenderingHints.VALUE_INTERPOLATION_BILINEAR);

    hintsMap.put(RenderingHints.KEY_RENDERING,
                 RenderingHints.VALUE_RENDER_QUALITY);

    hintsMap.put(RenderingHints.KEY_ANTIALIASING,
                 RenderingHints.VALUE_ANTIALIAS_ON);

    RenderingHints hints = new RenderingHints(hintsMap);
    BufferedImageOp op = new ConvolveOp(new Kernel(3,
                                                   3,
                                                   blurKernel),
                                        ConvolveOp.EDGE_NO_OP,
                                        hints);
    return op.filter(image,
                     null);
  }

  public static void resize(File srcImageFile,
                            File targetImageFile,
                            int width,
                            int height) throws IOException {
    BufferedImage image = ImageIO.read(srcImageFile);
//    image = createCompatibleImage(image);
//    image = resize(image,
//                   100,
//                   100);
//    image = blur(image);
    image = resize(image,
                   width,
                   height);

    FileOutputStream fileIOStream = null;

    try {

      fileIOStream = new FileOutputStream(targetImageFile);
      ImageIO.write(image, "jpg", fileIOStream);
      //JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(fileIOStream);
      //encoder.encode(image);
    } finally {
      IOSystem.close(fileIOStream);
    }

  }

  private static BufferedImage resize(BufferedImage image,
                                      int width,
                                      int height) {
    int type = image.getType() == 0 ? BufferedImage.TYPE_INT_ARGB
        : image.getType();
    BufferedImage resizedImage = new BufferedImage(width,
                                                   height,
                                                   type);
    Graphics2D graphics = resizedImage.createGraphics();
    graphics.setComposite(AlphaComposite.Src);

    graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                              RenderingHints.VALUE_INTERPOLATION_BILINEAR);

    graphics.setRenderingHint(RenderingHints.KEY_RENDERING,
                              RenderingHints.VALUE_RENDER_QUALITY);

    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                              RenderingHints.VALUE_ANTIALIAS_ON);
    
    graphics.drawImage(image,
                       0,
                       0,
                       width,
                       height,
                       null);
    graphics.dispose();
    return resizedImage;
  }

  public static void main(String[] args) throws IOException {
    resize(new File("c:\\temp\\picture.jpg"),
           new File("c:\\temp\\resized-picture.jpg"),
           100,
           150);
  }

}
