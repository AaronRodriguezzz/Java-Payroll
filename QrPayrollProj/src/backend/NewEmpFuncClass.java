package backend;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class NewEmpFuncClass {
	public String imagePath = null; //for inserting image to database
	
	public NewEmpFuncClass(){
		
	}
	
	public ImageIcon qrcodeFunction(String empID) {
		ImageIcon qrImg = null;
		try {
			String str = empID.trim();
			String path = System.getProperty("user.dir") + "/";
			String charSet = "UTF-8";
			Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
			hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
			generateQRcode(str,path+str+".png",charSet,hashMap,300,350);
			qrImg = new ImageIcon(str+".png");
		}catch(Exception e1) {
			JOptionPane.showMessageDialog(null, "QR CODE ERROR");
		}
		return qrImg; 
	}
	
	public void generateQRcode(String data, String path, String charset, Map map, int h, int w) throws WriterException, IOException {
		BitMatrix matrix = new MultiFormatWriter().encode(new String(data.getBytes(charset), charset), BarcodeFormat.QR_CODE, w, h);
	    MatrixToImageWriter.writeToFile(matrix, path.substring(path.lastIndexOf('.') + 1), new File(path));
	}
	
	public void cameraScanning(BufferedImage image) {
		do {
			try {
				BufferedImage img = image;
				LuminanceSource src = new BufferedImageLuminanceSource(img);
				BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(src));
				Result result = new MultiFormatReader().decode(bitmap);
				if(result.getText() != null) {
					JOptionPane.showMessageDialog(null, result.getText(),"INFORMATION", JOptionPane.INFORMATION_MESSAGE);
				}
			}catch(Exception e) {
				JOptionPane.showMessageDialog(null,"COMPLETE THE INFORMATION","INFORMATION",JOptionPane.INFORMATION_MESSAGE);
				e.printStackTrace();
			}
		}while(true);
	}
	
	public ImageIcon getProfileImage() {
		ImageIcon profileImage = null;
		Image pImageConvert = null;
		JFileChooser fChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("*.IMAGE","jpg","gif","png");
		fChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		fChooser.addChoosableFileFilter(filter);
		int result = fChooser.showSaveDialog(null);
		
		if(result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fChooser.getSelectedFile();
			imagePath = selectedFile.getAbsolutePath();
			profileImage = new ImageIcon(imagePath);
			pImageConvert=profileImage.getImage().getScaledInstance(250, 250, java.awt.Image.SCALE_AREA_AVERAGING);
			profileImage = new ImageIcon(pImageConvert);
		}else {
			JOptionPane.showMessageDialog(null, "NO FILE FOUND");
		}
		
		return profileImage;
	}


}
