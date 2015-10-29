import java.awt.Color;
import java.util.*;	
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.imageio.ImageIO;

public class PixelConvert {

	public static Color getClosestColor(Color color, List<Color> default_colors) {
		double min = Double.MAX_VALUE;
		double d = 0;
		Color return_color = new Color(0,0,0);
		for (int i = 0; i < default_colors.size(); i++) {
			d = getEuclidean(color, default_colors.get(i));
			if (d < min ) {
				return_color = default_colors.get(i);
				min = d;
			}
		}
		return return_color;
	}
	
	public static double getEuclidean(Color color, Color default_color) {
		int red = color.getRed();
		int green = color.getGreen();
		int blue = color.getBlue();
		int default_red = default_color.getRed();
		int default_green = default_color.getGreen();
		int default_blue = default_color.getBlue();
		double distance = Math.sqrt(Math.pow((red-default_red),2) + Math.pow((green-default_green),2) + Math.pow((blue-default_blue),2));
		return distance;
	}
	

	public static void main (String[] args) throws IOException {
		try {
			File file = new File("bob_dylan.jpg");
			BufferedImage img = ImageIO.read(file);
			
			List<Color> default_colors = new ArrayList<Color>();
			
			/*Color default_red = new Color(240,60,9);
			default_colors.add(default_red);	
			
			Color default_blue = new Color(98,179,235);
			default_colors.add(default_blue);
			
			Color default_brown = new Color(192,98,27);
			default_colors.add(default_brown);
			
			Color default_black = new Color(0,0,0);
			default_colors.add(default_black);
			
			Color default_white = new Color(255,255,255);
			default_colors.add(default_white);
			
			Color default_yellow = new Color(161,158,81);	
			default_colors.add(default_yellow); */
			
			Color default_red = new Color(224,224,224);
			default_colors.add(default_red);	
			
			Color default_blue = new Color(32,32,32);
			default_colors.add(default_blue);
			
			Color default_brown = new Color(150,150,150);
			default_colors.add(default_brown);
			
			Color default_black = new Color(0,0,0);
			default_colors.add(default_black);
			
			Color default_white = new Color(255,255,255);
			default_colors.add(default_white);
			
			Color default_yellow = new Color(21,21,21);	
			default_colors.add(default_yellow);
			
			int w = 0;
			int h = 0;
			
			for (int i = 0; i < img.getHeight(); i++) {
				for (int j = 0; j < img.getWidth(); j++) {
					Color color = new Color(img.getRGB(j, i));
					Color closest_color = new Color(0,0,0);
					closest_color = getClosestColor(color, default_colors);
					int rgb = closest_color.getRGB();
					img.setRGB(j,i, rgb);
					w++;
				}
				h++;
			}
			
			File outputfile = new File("bob_dylan_mod.jpg");
			ImageIO.write(img, "jpg", outputfile);
			System.out.println("Width: " + w + " Height: " + h);
			
		} catch (IOException e) {
			System.err.println("Caught IOException " + e.getMessage());
		}
	}
}