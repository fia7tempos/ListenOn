package net.musicrecommend.www.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImageUtil {
	public static final Logger logger = LoggerFactory.getLogger(ImageUtil.class);
	public String PATH_ALBUM_IMG = "C:/dev64/DC_workspace/ImageServer/WebContent/album";
	public String PATH_ARTIST_IMG = "C:/dev64/DC_workspace/ImageServer/WebContent/artist";
	public String PATH_SONG_IMG = "C:/dev64/DC_workspace/ImageServer/WebContent/song";
	
	public String imgDown(String imgUrl, String localPath){
		
		String fileExt = imgUrl.substring(imgUrl.lastIndexOf('.')+1,imgUrl.length());
		String fileName = imgUrl.substring(imgUrl.lastIndexOf('/')+1,imgUrl.length());
		
		logger.info("imgUrl" + imgUrl +",localPath" +localPath+",fileExt:"+fileExt+"fileName:"+fileName);
		
        try {
            URL url = new URL(imgUrl);
            BufferedImage img = ImageIO.read(url);
            File file=new File(localPath+"/"+fileName);
            ImageIO.write(img, fileExt, file);
        } catch (IOException e) {
         e.printStackTrace();
        }
		
        return null;       
        
	}

}
