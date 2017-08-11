package gui;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

public class MenuGUI {
	public static Font font;
	
	public MenuGUI(){
		
	}
	
	public static void createFonts(){
		try {
			Font baseFont = Font.createFont(Font.TRUETYPE_FONT, new File("PaD Font.otf"));
			font = baseFont.deriveFont(12f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		    ge.registerFont(font);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
	}
}
